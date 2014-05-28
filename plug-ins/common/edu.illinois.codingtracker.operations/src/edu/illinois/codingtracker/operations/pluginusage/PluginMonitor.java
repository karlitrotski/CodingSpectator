package edu.illinois.codingtracker.operations.pluginusage;

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

/**
 * Class that detects when a plug-in state changes to active or when 
 * it stops being active.
 * This Class uses X symbol from OperationSymbols.
 * 
 * @author Carlos_Dettoni
 * @author Sebastian_Sanchez
 */
public class PluginMonitor extends UserOperation {
	
	StringBuffer text;
	
	public PluginMonitor() {
		super();
	}

	@Override
	protected char getOperationSymbol() {
		return OperationSymbols.PLUGINS_USAGE;
	}

	@Override
	public String getDescription() {
		return "Plugin Changes Monitor";
	}

	@Override
	protected void populateTextChunk(OperationTextChunk textChunk) {
		
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