/**
 * 
 */
package edu.illinois.codingtracker.operations.parts;

import edu.illinois.codingtracker.operations.OperationLexer;
import edu.illinois.codingtracker.operations.OperationSymbols;
import edu.illinois.codingtracker.operations.OperationTextChunk;
import edu.illinois.codingtracker.operations.UserOperation;

/**
 * @author Juraj Kubelka, @author Catalina Espinoza
 * It records the view parts states.
 */
public class ViewPartOperation extends UserOperation implements IPartState {

	private String title;
	private String state;

	public ViewPartOperation(String title, String state) {
		this.title = title;
		this.state = state;
	}

	public ViewPartOperation() {
		super();
	}

	@Override
	public char getOperationSymbol() {
		return OperationSymbols.VIEW_PART_OPERATION_SYMBOL;
	}

	@Override
	public String getDescription() {
		return "View Part Operation, state: " + state;
	}

	@Override
	protected void populateTextChunk(OperationTextChunk textChunk) {
		textChunk.append(state);
		textChunk.append(title);
	}

	@Override
	protected void initializeFrom(OperationLexer operationLexer) {
		state = operationLexer.readString();
		title = operationLexer.readString();
	}

	@Override
	public void replay() throws Exception {
		
	}

	@Override
	protected void populateXMLTextChunk(OperationTextChunk textChunk) {		
		textChunk.concat("<ViewPartOperation>" + "\n");
		textChunk.concat("\t" + "<state>" + state + "</state>" +"\n");
		textChunk.concat("\t" + "<title>" + title +"</title>" +"\n");
		textChunk.concat("\t" + "<timestamp>" + getTime() + "</timestamp>" + "\n");
		textChunk.concat("</ViewPartOperation>" + "\n");
	}
	
	protected void populateCSVTextChunk(OperationTextChunk textChunk){
		textChunk.concat("ViewPartOperation , "+ getTime()+ " ,");
		textChunk.concat("\"[{State : "+ state +",");
		textChunk.concat("Title : "+ title + "}]\" \n");
	}

}
