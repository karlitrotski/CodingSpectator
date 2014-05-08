package edu.illinois.codingtracker.operations.starts;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;


import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceDescription;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IBundleGroup;
import org.eclipse.core.runtime.IBundleGroupProvider;
import org.eclipse.core.runtime.Platform;
import org.osgi.framework.Bundle;

import edu.illinois.codingtracker.compare.helpers.EditorHelper;
import edu.illinois.codingtracker.operations.OperationLexer;
import edu.illinois.codingtracker.operations.OperationSymbols;
import edu.illinois.codingtracker.operations.OperationTextChunk;
import edu.illinois.codingtracker.operations.UserOperation;

public class PluginsList extends UserOperation {
	
	StringBuffer text;
	
	public PluginsList() {
		super();
	}

	@Override
	protected char getOperationSymbol() {
		return OperationSymbols.PLUGINS_SYMBOL;
	}

	@Override
	public String getDescription() {
		return "List of installed plugins";
	}

	@Override
	protected void populateTextChunk(OperationTextChunk textChunk) {
		populatePluginsList();
		textChunk.append(text);
	}

	private void populatePluginsList() {
		text = new StringBuffer();
		IBundleGroupProvider[] providers = Platform.getBundleGroupProviders();
		TreeMap<String, List<String>> bundlesMap = new TreeMap<String, List<String>>(String.CASE_INSENSITIVE_ORDER);
		if (providers != null) {
		    for (int i = 0; i < providers.length; i++) {
		        IBundleGroup[] bundleGroups = providers[i].getBundleGroups();
		        for (int j = 0; j < bundleGroups.length; j++) {
		            Bundle[] bundles = bundleGroups[j] == null ? new Bundle[0] : bundleGroups[j]
		                    .getBundles();
		            List <String> valueList = bundlesMap.get(bundleGroups[j].getName()) == null ?
		            		new ArrayList<String>() : bundlesMap.get(bundleGroups[j].getName());
		            for (int k = 0; k < bundles.length; k++) {
		            	valueList.add(bundles[k].getSymbolicName());
		            }
		            bundlesMap.put(bundleGroups[j].getName(), valueList);
		        }
		    }
		    for (String str : bundlesMap.keySet()) {
		    	List <String> valueList = bundlesMap.get(str);
		    	Collections.sort(valueList, String.CASE_INSENSITIVE_ORDER);
		    	text.append(" Bundle:: " + str + "," + valueList.toString());		    	
		    }
		    		    
		}		
		
	}

	@Override
	protected void initializeFrom(OperationLexer operationLexer) {
		text = new StringBuffer(operationLexer.readString());	
	}

	@Override
	public void replay() throws CoreException {
		isReplayedRefactoring= false;
		//Close all editors (in case the previous Eclipse session ended abnormally, and thus close editor operations were not recorded).
		EditorHelper.closeAllEditors();
		//disable auto build
		IWorkspace workspace= ResourcesPlugin.getWorkspace();
		IWorkspaceDescription workspaceDesription= workspace.getDescription();
		workspaceDesription.setAutoBuilding(false);
		workspace.setDescription(workspaceDesription);
	}

	@Override
	public boolean isTestReplayRecorded() {
		return false;
	}

}
