package edu.illinois.codingtracker.tests;

import static org.junit.Assert.*;

import java.lang.reflect.Field;
import java.util.ArrayList;

import org.junit.Test;

import edu.illinois.codingtracker.operations.OperationDeserializer;
import edu.illinois.codingtracker.operations.OperationSymbols;
import edu.illinois.codingtracker.operations.UserOperation;

public class OperationSymbolsTest {

	@Test
	public void testNotEmpty() {
		ArrayList<Character> symbols = getSymbolValues();
		assertTrue(symbols.size() > 0);
	}

	@Test
	public void testNotDuplicatedSymbols() {
		ArrayList<Character> symbols = getSymbolValues();
		ArrayList<Character> checked = new ArrayList<Character>();
		for (Character symbol: symbols) {
			if (checked.contains(symbol))
				fail("Duplicated symbol " + symbol);
			else
				checked.add(symbol);
		}
	}

	@Test
	public void testOperationDeserializer() {
		ArrayList<Character> symbols = getSymbolValues();
		for (Character symbol: symbols) {
			UserOperation operation = OperationDeserializer.createEmptyUserOperation(symbol);
			char operationSymbol = operation.getOperationSymbol();
			assertTrue("Expected symbol: " + symbol.charValue() + ", but was: " + operationSymbol + ", from user operation " + operation.toString()
					, symbol.charValue() == operationSymbol);
		}
	}

	public ArrayList<Character> getSymbolValues() {
		ArrayList<Character> values = new ArrayList<Character>();
		Field[] fields = OperationSymbols.class.getDeclaredFields();
		for (Field field: fields) {
			Class<?> type = field.getType();
			if (type == char.class) {
				char value;
				try {
					value = field.getChar(OperationSymbols.class);
					values.add(value);
				} catch (IllegalArgumentException e) {
					fail("Fail getting field " + field.toString() + ", error: " + e.toString());
				} catch (IllegalAccessException e) {
					fail("Fail getting field " + field.toString() + ", error: " + e.toString());
				}				
			}
		}
		return values;
	}

}
