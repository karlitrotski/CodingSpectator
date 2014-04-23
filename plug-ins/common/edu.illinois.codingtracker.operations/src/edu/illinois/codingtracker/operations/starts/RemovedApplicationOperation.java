package edu.illinois.codingtracker.operations.starts;

import edu.illinois.codingtracker.operations.OperationLexer;
import edu.illinois.codingtracker.operations.OperationSymbols;
import edu.illinois.codingtracker.operations.OperationTextChunk;

public class RemovedApplicationOperation extends LaunchedApplicationOperation {

	private int[] exitValues;

	public RemovedApplicationOperation(String launchMode, String launchName, String application, String product, boolean useProduct, 
			int[] exitValues) {
		super(launchMode, launchName, application, product, useProduct);
		this.exitValues = exitValues;
	}
	
	@Override
	protected char getOperationSymbol() {
		return OperationSymbols.APPLICATION_REMOVED_SYMBOL;
	}

	@Override
	public String getDescription() {
		return "Removed application";
	}

	@Override
	protected void populateTextChunk(OperationTextChunk textChunk) {
		super.populateTextChunk(textChunk);
		// TODO: store array of int values
		textChunk.append(exitValues);
	}

	@Override
	protected void initializeFrom(OperationLexer operationLexer) {
		super.initializeFrom(operationLexer);
		exitValues = operationLexer.readIntArray();
	}

	@Override
	public void replay() throws Exception {
		// TODO Auto-generated method stub

	}

}
