/**
 * This file is licensed under the University of Illinois/NCSA Open Source License. See LICENSE.TXT for details.
 */
package edu.illinois.codingtracker.operations.junit;

import edu.illinois.codingtracker.operations.OperationLexer;
import edu.illinois.codingtracker.operations.OperationSymbols;
import edu.illinois.codingtracker.operations.OperationTextChunk;

/**
 * 
 * @author Stas Negara
 * 
 * @author Juraj Kubelka
 * @author Catalina Espinoza Inaipil - we added progress state and trace to be recorded.
 */
public class TestCaseFinishedOperation extends JUnitOperation {

	private String result;
	private String progressState;
	private String trace;

	public TestCaseFinishedOperation() {
		super();
	}
	public TestCaseFinishedOperation(String testRunName, String result, String progressState, String trace) {
		super(testRunName);
		this.result= result;
		this.progressState = progressState;
		this.trace = trace;
	}

	@Override
	public char getOperationSymbol() {
		return OperationSymbols.TEST_CASE_FINISHED_SYMBOL;
	}

	@Override
	public String getDescription() {
		return "Finished test case";
	}

	@Override
	protected void populateTextChunk(OperationTextChunk textChunk) {
		super.populateTextChunk(textChunk);
		textChunk.append(result);
		textChunk.append(progressState);
		textChunk.append(trace);
	}
	
	@Override
	protected void populateXMLTextChunk(OperationTextChunk textChunk){
		textChunk.concat("<TestCaseFinishedOperation>" + "\n");
		super.populateXMLTextChunk(textChunk);
		textChunk.concat("\t" + "<Result>");
		textChunk.concat("" + result);
		textChunk.concat("</Result>" + "\n");
		textChunk.concat("\t" + "<timestamp>");
		textChunk.concat("" + getTime());
		textChunk.concat("</timestamp>" + "\n");
		textChunk.concat("</TestCaseFinishedOperation>" + "\n");
	}

	@Override
	protected void initializeFrom(OperationLexer operationLexer) {
		super.initializeFrom(operationLexer);
		result= operationLexer.readString();
		progressState = operationLexer.readString();
		trace = operationLexer.readString();
	}

	@Override
	public String toString() {
		StringBuffer sb= new StringBuffer();
		sb.append("Result: " + result + ", " + progressState + "\n");
		sb.append(super.toString());
		return sb.toString();
	}

}
