/**
 * This file is licensed under the University of Illinois/NCSA Open Source License. See LICENSE.TXT for details.
 */
package edu.illinois.codingtracker.operations.junit;

import edu.illinois.codingtracker.operations.OperationSymbols;
import edu.illinois.codingtracker.operations.OperationTextChunk;

/**
 * 
 * @author Stas Negara
 * 
 */
public class TestSessionFinishedOperation extends JUnitOperation {

	public TestSessionFinishedOperation() {
		super();
	}

	public TestSessionFinishedOperation(String testRunName) {
		super(testRunName);
	}

	@Override
	protected char getOperationSymbol() {
		return OperationSymbols.TEST_SESSION_FINISHED_SYMBOL;
	}

	@Override
	public String getDescription() {
		return "Finished test session";
	}
	
	@Override
	protected void populateXMLTextChunk(OperationTextChunk textChunk){
		textChunk.concat("<TestSessionFinishedOperation>" + "\n");
		super.populateXMLTextChunk(textChunk);
		textChunk.concat("\t" + "<timestamp>");
		textChunk.concat("" + getTime());
		textChunk.concat("</timestamp>" + "\n");
		textChunk.concat("</TestSessionFinishedOperation>" + "\n");
	}

}
