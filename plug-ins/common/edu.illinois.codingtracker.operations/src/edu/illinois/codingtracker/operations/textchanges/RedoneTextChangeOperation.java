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
	public char getOperationSymbol() {
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
		textChunk.concat("<RedoneTextChangeOperation>" + "\n");
		super.populateXMLTextChunk(textChunk);
		textChunk.concat("\t" + "<timestamp>");
		textChunk.concat("" + getTime());
		textChunk.concat("</timestamp>" + "\n");
		textChunk.concat("</RedoneTextChangeOperation>" + "\n");
	}
	
	@Override
	protected void populateCSVTextChunk(OperationTextChunk textChunk){
		textChunk.concat("RedoneTextChangeOperation , "+ getTime()+ " ,");
		textChunk.concat("\"[{");
		super.populateCSVTextChunk(textChunk);
		textChunk.concat("}]\" \n");
	}

}
