/**
 * This file is licensed under the University of Illinois/NCSA Open Source License. See LICENSE.TXT for details.
 */
package edu.illinois.codingtracker.operations.textchanges;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.text.DocumentEvent;

import edu.illinois.codingtracker.operations.OperationSymbols;
import edu.illinois.codingtracker.operations.OperationTextChunk;

/**
 * 
 * @author Stas Negara
 * 
 */
public class UndoneConflictEditorTextChangeOperation extends ConflictEditorTextChangeOperation {

	public UndoneConflictEditorTextChangeOperation() {
		super();
	}

	public UndoneConflictEditorTextChangeOperation(String editorID, DocumentEvent documentEvent, String replacedText) {
		super(editorID, documentEvent, replacedText);
	}

	@Override
	protected char getOperationSymbol() {
		return OperationSymbols.CONFLICT_EDITOR_TEXT_CHANGE_UNDONE_SYMBOL;
	}

	@Override
	public String getDescription() {
		return "Undone conflict editor text change";
	}

	@Override
	protected void replaySpecificTextChange() throws ExecutionException {
		getCurrentDocumentUndoManager().undo();
	}
	
	
	@Override
	protected void populateXMLTextChunk(OperationTextChunk textChunk){
		textChunk.concat("<UndoneConflictEditorTextChangeOperation>" + "\n");
		super.populateXMLTextChunk(textChunk);
		textChunk.concat("\t" + "<timestamp>");
		textChunk.concat("" + getTime());
		textChunk.concat("</timestamp>" + "\n");
		textChunk.concat("</UndoneConflictEditorTextChangeOperation>" + "\n");
	}

}
