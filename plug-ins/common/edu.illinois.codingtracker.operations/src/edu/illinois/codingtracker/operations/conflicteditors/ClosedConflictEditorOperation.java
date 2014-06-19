/**
 * This file is licensed under the University of Illinois/NCSA Open Source License. See LICENSE.TXT for details.
 */
package edu.illinois.codingtracker.operations.conflicteditors;

import org.eclipse.compare.internal.CompareEditor;

import edu.illinois.codingtracker.compare.helpers.EditorHelper;
import edu.illinois.codingtracker.helpers.Debugger;
import edu.illinois.codingtracker.operations.OperationSymbols;
import edu.illinois.codingtracker.operations.OperationTextChunk;
import edu.illinois.codingtracker.operations.OperationXMLTextChunk;

/**
 * 
 * @author Stas Negara
 * 
 */
@SuppressWarnings("restriction")
public class ClosedConflictEditorOperation extends ConflictEditorOperation {

	public ClosedConflictEditorOperation() {
		super();
	}

	public ClosedConflictEditorOperation(String editorID) {
		super(editorID);
	}

	@Override
	protected char getOperationSymbol() {
		return OperationSymbols.CONFLICT_EDITOR_CLOSED_SYMBOL;
	}
	
	@Override
	protected void populateXMLTextChunk(OperationXMLTextChunk textChunk){
		textChunk.concat("<ClosedConflictEditorOperation>" + "\n");
		super.populateXMLTextChunk(textChunk);
		textChunk.concat("\t" + "<timestamp>" + "\n");
		textChunk.concat("\t" + getTime() + "\n");
		textChunk.concat("\t" + "</timestamp>" + "\n");
		textChunk.concat("</ClosedConflictEditorOperation>" + "\n");
	}

	@Override
	public String getDescription() {
		return "Closed conflict editor";
	}

	@Override
	public void replay() {
		CompareEditor compareEditor= EditorHelper.getCompareEditor(editorID);
		if (compareEditor == null) {
			Debugger.debugWarning("Can not close non existing conflict editor:\n" + this);
		} else {
			EditorHelper.closeEditorSynchronously(compareEditor);
			EditorHelper.removeCompareEditor(editorID);
		}
	}

}
