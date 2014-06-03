/**
 * This file is licensed under the University of Illinois/NCSA Open Source License. See LICENSE.TXT for details.
 */
package edu.illinois.codingtracker.operations.textchanges;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.DocumentEvent;

import edu.illinois.codingtracker.operations.OperationSymbols;
import edu.illinois.codingtracker.operations.OperationTextChunk;

/**
 * 
 * @author Stas Negara
 * 
 */
public class PerformedTextChangeOperation extends TextChangeOperation {

	public PerformedTextChangeOperation() {
		super();
	}

	public PerformedTextChangeOperation(DocumentEvent documentEvent, String replacedText) {
		super(documentEvent, replacedText);
	}

	@Override
	protected char getOperationSymbol() {
		return OperationSymbols.TEXT_CHANGE_PERFORMED_SYMBOL;
	}

	@Override
	public String getDescription() {
		return "Performed text change";
	}

	@Override
	protected void replaySpecificTextChange() throws BadLocationException {
		currentDocument.replace(offset, length, newText);
	}
	
	@Override
	protected void populateXMLTextChunk(OperationTextChunk textChunk){
		textChunk.append("<PerformedTextChangeOperation>" + "\n");
		super.populateXMLTextChunk(textChunk);
		textChunk.append("\t" + "<timestamp>" + "\n");
		textChunk.append("\t" + getTime() + "\n");
		textChunk.append("\t" + "</timestamp>" + "\n");
		textChunk.append("</PerformedTextChangeOperation>" + "\n");
	}

}
