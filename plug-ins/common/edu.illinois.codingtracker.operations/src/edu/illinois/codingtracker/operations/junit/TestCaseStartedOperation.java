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
public class TestCaseStartedOperation extends JUnitOperation {

	private String testClassName;

	private String testMethodName;

	public TestCaseStartedOperation() {
		super();
	}

	public TestCaseStartedOperation(String testRunName, String testClassName, String testMethodName) {
		super(testRunName);
		this.testClassName= testClassName;
		this.testMethodName= testMethodName;
	}

	@Override
	protected char getOperationSymbol() {
		return OperationSymbols.TEST_CASE_STARTED_SYMBOL;
	}

	@Override
	public String getDescription() {
		return "Started test case";
	}

	@Override
	protected void populateTextChunk(OperationTextChunk textChunk) {
		super.populateTextChunk(textChunk);
		textChunk.append(testClassName);
		textChunk.append(testMethodName);
	}
	
	@Override
	protected void populateXMLTextChunk(OperationTextChunk textChunk){
		textChunk.append("<TestCaseStartedOperation>" + "\n");
		super.populateXMLTextChunk(textChunk);
		textChunk.append("\t" + "<Test_class_name>" + "\n");
		textChunk.append("\t" + testClassName + "\n");
		textChunk.append("\t" + "</Test_class_name>" + "\n");
		textChunk.append("\t" + "<Test_method_name>" + "\n");
		textChunk.append("\t" + testMethodName + "\n");
		textChunk.append("\t" + "</Test_method_name>" + "\n");
		textChunk.append("\t" + "<timestamp>" + "\n");
		textChunk.append("\t" + getTime() + "\n");
		textChunk.append("\t" + "</timestamp>" + "\n");
		textChunk.append("</TestCaseStartedOperation>" + "\n");
	}

	@Override
	protected void initializeFrom(OperationLexer operationLexer) {
		super.initializeFrom(operationLexer);
		testClassName= operationLexer.readString();
		testMethodName= operationLexer.readString();
	}

	@Override
	public String toString() {
		StringBuffer sb= new StringBuffer();
		sb.append("Test class name: " + testClassName + "\n");
		sb.append("Test method name: " + testMethodName + "\n");
		sb.append(super.toString());
		return sb.toString();
	}

}
