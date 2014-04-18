package edu.illinois.codingtracker.operations.resources;
import edu.illinois.codingtracker.operations.OperationLexer;
import edu.illinois.codingtracker.operations.OperationSymbols;
import edu.illinois.codingtracker.operations.OperationTextChunk;
import edu.illinois.codingtracker.operations.UserOperation;

public class DetectFocusGainsWorkbench extends UserOperation{
	
	public DetectFocusGainsWorkbench() {
		super();
	}
		
	@Override
	protected char getOperationSymbol() {
		return OperationSymbols.APPLICATION_FOCUS_GAINS;
	}

	@Override
	public String getDescription() {
		return "Loose Focus";
	}
	@Override
	protected void populateTextChunk(OperationTextChunk textChunk) {
		// TODO Auto-generated method stub
		
	}
	@Override
	protected void initializeFrom(OperationLexer operationLexer) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void replay() throws Exception {
		// TODO Auto-generated method stub
		
	}
}
