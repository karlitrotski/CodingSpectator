/**
 * This file is licensed under the University of Illinois/NCSA Open Source License. See LICENSE.TXT for details.
 */
package edu.illinois.codingtracker.tests.analyzers.ast.transformation;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NavigableMap;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import edu.illinois.codingtracker.tests.analyzers.ast.transformation.RemainingItemsComparator.ItemPairStatus;


/**
 * 
 * @author Stas Negara
 * 
 */
public class UnknownTransformationMiner {

	private static final Map<Integer, Transaction> transactions= new HashMap<Integer, Transaction>();

	//It is TreeMap to be able to call tailMap on it.
	private static final TreeMap<Item, Set<Integer>> inputItemTransactions= new TreeMap<Item, Set<Integer>>();

	private static final Map<TreeSet<Item>, Set<Integer>> resultItemSetTransactions= new HashMap<TreeSet<Item>, Set<Integer>>();

	private static final Map<Long, Set<TreeSet<Item>>> hashedResultItemSets= new HashMap<Long, Set<TreeSet<Item>>>();

	//TODO: This is used to cache itemset frequencies to avoid recomputation. 
	//Make sure that this does cause too much memory overhead for mining big data.
	private static final Map<TreeSet<Item>, Integer> itemSetFrequencies= new HashMap<TreeSet<Item>, Integer>();

	private static int blockNumber= 1;


	public static Set<Integer> getResultItemSetTransactions(TreeSet<Item> itemSet) {
		return resultItemSetTransactions.get(itemSet);
	}

	public static Set<Integer> getInputItemTransactions(Item item) {
		return inputItemTransactions.get(item);
	}

	public static void mine() {
		long startTime= System.currentTimeMillis();

		solve(new TreeSet<Item>(), inputItemTransactions);

		System.out.println("Mining time: " + (System.currentTimeMillis() - startTime));
	}

	private static void solve(TreeSet<Item> itemSet, NavigableMap<Item, Set<Integer>> remainingItems) {
		RemainingItemsComparator remainingItemsComparator= new RemainingItemsComparator(itemSet);
		TreeMap<Item, Set<Integer>> localRemainingItems= new TreeMap<Item, Set<Integer>>(remainingItemsComparator);
		localRemainingItems.putAll(remainingItems);
		while (!localRemainingItems.isEmpty()) {
			Item currentItem= localRemainingItems.pollFirstEntry().getKey();
			if (remainingItemsComparator.isFrequent(currentItem)) {
				TreeMap<Item, Set<Integer>> newRemainingItems= new TreeMap<Item, Set<Integer>>(localRemainingItems);
				TreeSet<Item> newItemSet= fuseWithSiblings(currentItem, remainingItemsComparator, localRemainingItems, newRemainingItems);
				Set<Integer> commonTransactionIDs= remainingItemsComparator.getCommonTransactionIDs(currentItem);
				if (!isSubsumed(newItemSet, commonTransactionIDs)) {
					resultItemSetTransactions.put(newItemSet, commonTransactionIDs);
					addToHashedResultItemSets(newItemSet, commonTransactionIDs);
					solve(newItemSet, newRemainingItems);
				}
			}
		}
	}

	private static TreeSet<Item> fuseWithSiblings(Item currentItem, RemainingItemsComparator remainingItemsComparator, TreeMap<Item, Set<Integer>> localRemainingItems,
														TreeMap<Item, Set<Integer>> newRemainingItems) {
		TreeSet<Item> newItemSet= new TreeSet<Item>(remainingItemsComparator.getBaseItemSet());
		newItemSet.add(currentItem);
		Iterator<Entry<Item, Set<Integer>>> siblingsIterator= localRemainingItems.entrySet().iterator();
		while (siblingsIterator.hasNext()) {
			Item siblingItem= siblingsIterator.next().getKey();
			ItemPairStatus itemPairStatus= remainingItemsComparator.compareItems(currentItem, siblingItem);
			if (itemPairStatus == ItemPairStatus.SECOND_PREVAILS || itemPairStatus == ItemPairStatus.EQUIVALENT) {
				newItemSet.add(siblingItem);
				newRemainingItems.remove(siblingItem);
				if (itemPairStatus == ItemPairStatus.EQUIVALENT) {
					siblingsIterator.remove();
				}
			}
		}
		return newItemSet;
	}

	private static boolean isSubsumed(TreeSet<Item> checkedItemSet, Set<Integer> transactionIDs) {
		Set<TreeSet<Item>> itemSets= hashedResultItemSets.get(getHashForTransactions(transactionIDs));
		if (itemSets == null) {
			return false;
		}
		for (TreeSet<Item> itemSet : itemSets) {
			if (itemSet.containsAll(checkedItemSet)) {
				Set<Integer> itemSetTransactionIDs= resultItemSetTransactions.get(itemSet);
				if (itemSetTransactionIDs.equals(transactionIDs) &&
						getFrequency(itemSet, itemSetTransactionIDs) == getFrequency(checkedItemSet, transactionIDs)) {
					return true;
				}
			}
		}
		return false;
	}

	private static void addToHashedResultItemSets(TreeSet<Item> itemSet, Set<Integer> transactionIDs) {
		Long hash= getHashForTransactions(transactionIDs);
		Set<TreeSet<Item>> itemSets= hashedResultItemSets.get(hash);
		if (itemSets == null) {
			itemSets= new HashSet<TreeSet<Item>>();
			hashedResultItemSets.put(hash, itemSets);
		}
		itemSets.add(itemSet);
	}

	private static Long getHashForTransactions(Set<Integer> transactionIDs) {
		long hash= 0;
		for (Integer transactionID : transactionIDs) {
			hash+= transactionID;
		}
		return hash;
	}

	public static void addItemToTransactions(TreeMap<Long, Item> items, boolean isFirstBlock, boolean isLastBlock) {
		if (items.isEmpty()) {
			throw new RuntimeException("A block should contain at least one item.");
		}
		for (Entry<Long, Item> entry : items.entrySet()) {
			long itemID= entry.getKey();
			Item item= entry.getValue();
			Set<Integer> itemTransactions= inputItemTransactions.get(item);
			if (itemTransactions == null) {
				itemTransactions= new TreeSet<Integer>();
				inputItemTransactions.put(item, itemTransactions);
			}
			if (isFirstBlock || !isLastBlock) {
				itemTransactions.add(blockNumber);
				addItemInstanceToTransation(blockNumber, itemID, item);
			}
			if (!isFirstBlock) { //If not the first block, add to the preceding transaction as well.
				itemTransactions.add(blockNumber - 1);
				addItemInstanceToTransation(blockNumber - 1, itemID, item);
			}
		}
		if (isFirstBlock || !isLastBlock) {
			blockNumber++;
		}
	}

	private static void addItemInstanceToTransation(int transactionID, long itemID, Item item) {
		Transaction transaction= transactions.get(transactionID);
		if (transaction == null) {
			transaction= new Transaction(transactionID);
			transactions.put(transactionID, transaction);
		}
		transaction.addItemInstance(item, itemID);
	}

	/**
	 * Should be called only after all results are computed.
	 * 
	 * @param itemSet
	 * @return
	 */
	public static int getFrequency(TreeSet<Item> itemSet) {
		Set<Integer> transactionIDs= resultItemSetTransactions.get(itemSet);
		if (transactionIDs == null) {
			return 0;
		}
		return getFrequency(itemSet, transactionIDs);
	}

	static int getFrequency(TreeSet<Item> itemSet, Set<Integer> transactionIDs) {
		if (transactionIDs.isEmpty()) {
			return 0;
		}
		Integer cachedFrequency= itemSetFrequencies.get(itemSet);
		if (cachedFrequency == null) {
			int frequency= 0;
			Iterator<Integer> transactionIDIterator= transactionIDs.iterator();
			Transaction precedingTransaction= transactions.get(transactionIDIterator.next());
			while (transactionIDIterator.hasNext()) {
				Transaction subsequentTransaction= transactions.get(transactionIDIterator.next());
				precedingTransaction.removeDuplicatedInstances(subsequentTransaction, itemSet);
				frequency+= precedingTransaction.getFrequency(itemSet);
				precedingTransaction= subsequentTransaction;
			}
			frequency+= precedingTransaction.getFrequency(itemSet);
			cachedFrequency= frequency;
			//Create a copy since the itemset might get modified externally.
			TreeSet<Item> copyItemSet= new TreeSet<Item>(itemSet);
			itemSetFrequencies.put(copyItemSet, cachedFrequency);
		}
		return cachedFrequency;
	}

	public static void resetState() {
		transactions.clear();
		inputItemTransactions.clear();
		resultItemSetTransactions.clear();
		hashedResultItemSets.clear();
		itemSetFrequencies.clear();
		blockNumber= 1;
	}

	public static void printState() {
		for (Entry<TreeSet<Item>, Set<Integer>> entry : resultItemSetTransactions.entrySet()) {
			TreeSet<Item> itemSet= entry.getKey();
			System.out.println("Frequency of item set " + itemSet + " is " + getFrequency(itemSet) + ":");
			for (int transactionNumber : entry.getValue()) {
				System.out.print("Instances for transaction " + transactionNumber + ": ");
				transactions.get(transactionNumber).printItemSetInstances(itemSet);
			}
		}
	}

}