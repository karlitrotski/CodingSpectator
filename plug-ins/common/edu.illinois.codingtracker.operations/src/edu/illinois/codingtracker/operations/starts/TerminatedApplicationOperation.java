package edu.illinois.codingtracker.operations.starts;

import edu.illinois.codingtracker.operations.OperationLexer;
import edu.illinois.codingtracker.operations.OperationSymbols;
import edu.illinois.codingtracker.operations.OperationTextChunk;

public class TerminatedApplicationOperation extends LaunchedApplicationOperation {

	private int[] exitValues;

	public TerminatedApplicationOperation(String launchMode, String launchName, String application, String product, boolean useProduct, 
			int[] exitValues) {
		super(launchMode, launchName, application, product, useProduct);
		this.exitValues = exitValues;
	}
	
	@Override
	protected char getOperationSymbol() {
		return OperationSymbols.APPLICATION_TERMINATED_SYMBOL;
	}

	@Override
	public String getDescription() {
		return "Terminated application";
	}

	@Override
	protected void populateTextChunk(OperationTextChunk textChunk) {
		super.populateTextChunk(textChunk);
		textChunk.append(exitValues);
	}

	@Override
	protected void initializeFrom(OperationLexer operationLexer) {
		super.initializeFrom(operationLexer);
		exitValues = operationLexer.readIntArray();
	}
}
