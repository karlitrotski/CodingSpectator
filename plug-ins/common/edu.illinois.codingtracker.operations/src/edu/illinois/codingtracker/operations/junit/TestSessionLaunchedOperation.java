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
 */
public class TestSessionLaunchedOperation extends JUnitOperation {

	String launchedProjectName;

	public TestSessionLaunchedOperation() {
		super();
	}

	public TestSessionLaunchedOperation(String testRunName, String launchedProjectName) {
		super(testRunName);
		this.launchedProjectName= launchedProjectName;
	}

	@Override
	protected char getOperationSymbol() {
		return OperationSymbols.TEST_SESSION_LAUNCHED_SYMBOL;
	}

	@Override
	public String getDescription() {
		return "Launched test session";
	}

	@Override
	protected void populateTextChunk(OperationTextChunk textChunk) {
		super.populateTextChunk(textChunk);
		textChunk.append(launchedProjectName);
	}
	
	@Override
	protected void populateXMLTextChunk(OperationTextChunk textChunk){
		textChunk.append("<TestSessionLaunchedOperation>" + "\n");
		super.populateXMLTextChunk(textChunk);
		textChunk.append("\t" + "<Launched_Project_Name>" + "\n");
		textChunk.append("\t" + launchedProjectName + "\n");
		textChunk.append("\t" + "</Launched_Project_Name>" + "\n");
		textChunk.append("\t" + "<timestamp>" + "\n");
		textChunk.append("\t" + getTime() + "\n");
		textChunk.append("\t" + "</timestamp>" + "\n");
		textChunk.append("</TestSessionLaunchedOperation>" + "\n");
	}

	@Override
	protected void initializeFrom(OperationLexer operationLexer) {
		super.initializeFrom(operationLexer);
		launchedProjectName= operationLexer.readString();
	}

	@Override
	public String toString() {
		StringBuffer sb= new StringBuffer();
		sb.append("Launched project name: " + launchedProjectName + "\n");
		sb.append(super.toString());
		return sb.toString();
	}

}
