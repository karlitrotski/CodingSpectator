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
 *
 */
public class ViewPartOperation extends UserOperation implements IPartState {

	private String title;
	private String state;

	public ViewPartOperation(String title, String state) {
		this.title = title;
		this.state = state;
	}

	/* (non-Javadoc)
	 * @see edu.illinois.codingtracker.operations.UserOperation#getOperationSymbol()
	 */
	@Override
	protected char getOperationSymbol() {
		return OperationSymbols.VIEW_PART_OPERATION_SYMBOL;
	}

	/* (non-Javadoc)
	 * @see edu.illinois.codingtracker.operations.UserOperation#getDescription()
	 */
	@Override
	public String getDescription() {
		return "View Part Operation, state: " + state;
	}

	/* (non-Javadoc)
	 * @see edu.illinois.codingtracker.operations.UserOperation#populateTextChunk(edu.illinois.codingtracker.operations.OperationTextChunk)
	 */
	@Override
	protected void populateTextChunk(OperationTextChunk textChunk) {
		textChunk.append(state);
		textChunk.append(title);
	}

	/* (non-Javadoc)
	 * @see edu.illinois.codingtracker.operations.UserOperation#initializeFrom(edu.illinois.codingtracker.operations.OperationLexer)
	 */
	@Override
	protected void initializeFrom(OperationLexer operationLexer) {
		state = operationLexer.readString();
		title = operationLexer.readString();
	}

	/* (non-Javadoc)
	 * @see edu.illinois.codingtracker.operations.UserOperation#replay()
	 */
	@Override
	public void replay() throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	protected void populateXMLTextChunk(OperationTextChunk textChunk) {		
		textChunk.concat("<ViewPartOperation>" + "\n");
		textChunk.concat("\t" + "<state>" + state + "</state>" +"\n");
		textChunk.concat("\t" + "<title>" + title +"</title>" +"\n");
		textChunk.concat("\t" + "<timestamp>" + getTime() + "</timestamp>" + "\n");
		textChunk.concat("</ViewPartOperation>" + "\n");
	}

}
