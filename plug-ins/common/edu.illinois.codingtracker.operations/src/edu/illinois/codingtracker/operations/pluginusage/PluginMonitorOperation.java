package edu.illinois.codingtracker.operations.pluginusage;

import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceDescription;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;

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
public class PluginMonitorOperation extends UserOperation {
	
	StringBuffer text;
	
	public PluginMonitorOperation() {
		super();
	}

	public PluginMonitorOperation(StringBuffer text) {
		this.text = text;
	}

	@Override
	public char getOperationSymbol() {
		return OperationSymbols.PLUGINS_USAGE;
	}

	@Override
	public String getDescription() {
		return "Plugin Changes Monitor";
	}

	@Override
	protected void populateTextChunk(OperationTextChunk textChunk) {
		textChunk.append(text);
	}
	
	@Override
	protected void populateXMLTextChunk(OperationTextChunk textChunk) {
		textChunk.concat("<PluginMonitorOperation>" + "\n");
		textChunk.concat("\t" + "<text>" + text + "</text>" + "\n");	
		textChunk.concat("\t" + "<timestamp>" + getTime() + "</timestamp>" + "\n");
		textChunk.concat("</PluginMonitorOperation>" + "\n");
	}
	
	protected void populateCSVTextChunk(OperationTextChunk textChunk){
		textChunk.concat("PluginMonitorOperation , "+ getTime()+ " ,");
		textChunk.concat("[{Text : "+ text +"}]\n");
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
