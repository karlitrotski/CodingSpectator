/**
 * This file is licensed under the University of Illinois/NCSA Open Source License. See LICENSE.TXT for details.
 */
package edu.illinois.codingtracker.operations.focus;
/**
 * Class write symbol Q when the development environment 
 * gains focus 
 *  @author Teofilo_Chambilla_Aquino
 */
import org.eclipse.ui.IWorkbenchWindow;

import edu.illinois.codingtracker.operations.OperationLexer;
import edu.illinois.codingtracker.operations.OperationSymbols;
import edu.illinois.codingtracker.operations.OperationTextChunk;
import edu.illinois.codingtracker.operations.UserOperation;

//Esta clase esta repetida en el package operations.resources
public class DetectFocusGainsWorkbench extends UserOperation{
	
	private int win;
	
	public DetectFocusGainsWorkbench() {
		super();
	}
	
	public DetectFocusGainsWorkbench(IWorkbenchWindow window) {
		win = window.hashCode();
	}
		
	@Override
	public char getOperationSymbol() {
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
	protected void populateXMLTextChunk(OperationTextChunk textChunk) {
		textChunk.concat("<DetectFocusGainsWorkbench>"+"\n");
		textChunk.concat("\t" + "<timestamp>");
		textChunk.concat("" + getTime());
		textChunk.concat("</timestamp>" + "\n");
		textChunk.concat("\t" + "<Window>" + "\n");
		textChunk.concat("\t" + win + "\n");
		textChunk.concat("\t" + "</Window>" + "\n");
		textChunk.concat("</DetectFocusGainsWorkbench>"+"\n");
	}
	
	@Override
	protected void populateCSVTextChunk(OperationTextChunk textChunk){
		textChunk.concat("DetectFocusGainsWorkbench , "+ getTime()+ " ,");
		textChunk.concat("[{Window : " + win + "}]\n");
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
