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
public class RedoneTextChangeOperation extends TextChangeOperation {

	public RedoneTextChangeOperation() {
		super();
	}

	public RedoneTextChangeOperation(DocumentEvent documentEvent, String replacedText) {
		super(documentEvent, replacedText);
	}

	@Override
	protected char getOperationSymbol() {
		return OperationSymbols.TEXT_CHANGE_REDONE_SYMBOL;
	}

	@Override
	public String getDescription() {
		return "Redone text change";
	}

	@Override
	protected void replaySpecificTextChange() throws ExecutionException {
		getCurrentDocumentUndoManager().redo();
	}
	
	@Override
	protected void populateXMLTextChunk(OperationTextChunk textChunk){
		textChunk.append("<RedoneTextChangeOperation>" + "\n");
		super.populateXMLTextChunk(textChunk);
		textChunk.append("\t" + "<timestamp>" + "\n");
		textChunk.append("\t" + getTime() + "\n");
		textChunk.append("\t" + "</timestamp>" + "\n");
		textChunk.append("</RedoneTextChangeOperation>" + "\n");
	}

}
