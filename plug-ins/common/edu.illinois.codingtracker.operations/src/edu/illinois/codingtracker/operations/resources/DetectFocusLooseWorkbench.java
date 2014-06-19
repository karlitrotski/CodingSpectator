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
import edu.illinois.codingtracker.operations.OperationXMLTextChunk;

//Esta clase esta repetida en el package operations.focus
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
	protected void populateXMLTextChunk(OperationXMLTextChunk textChunk) {
		textChunk.concat("<DetectFocusLooseWorkbench>"+"\n");
		textChunk.concat("\t" + "<timestamp>" + "\n");
		textChunk.concat("\t" + getTime() + "\n");
		textChunk.concat("\t" + "</timestamp>" + "\n");	
		textChunk.concat("</DetectFocusLooseWorkbench>"+"\n");
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
