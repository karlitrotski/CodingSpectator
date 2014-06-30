package edu.illinois.codingtracker.operations.resources;
/**
 * @author Teofilo_Chambilla_Aquino
 * Class write symbol R when the development environment 
 * Loose focus (user uses another application) 
 */
import org.eclipse.ui.IWorkbenchWindow;

import edu.illinois.codingtracker.operations.OperationLexer;
import edu.illinois.codingtracker.operations.OperationSymbols;
import edu.illinois.codingtracker.operations.OperationTextChunk;
import edu.illinois.codingtracker.operations.UserOperation;

public class DetectFocusLooseWorkbench extends UserOperation{

	
	public DetectFocusLooseWorkbench() {
		super();
	}
//FaltaImplementar
	public DetectFocusLooseWorkbench(IWorkbenchWindow window) {
	super();
	}

	@Override
	protected char getOperationSymbol() {
		return OperationSymbols.APPLICATION_FOCUS_LOOSE;
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