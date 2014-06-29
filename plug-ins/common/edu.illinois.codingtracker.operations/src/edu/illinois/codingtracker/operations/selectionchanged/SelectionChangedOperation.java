package edu.illinois.codingtracker.operations.selectionchanged;

import edu.illinois.codingtracker.operations.OperationLexer;
import edu.illinois.codingtracker.operations.OperationSymbols;
import edu.illinois.codingtracker.operations.OperationTextChunk;
import edu.illinois.codingtracker.operations.UserOperation;

/**
 * @author Juraj Kubelka, @author Catalina Espinoza Inaipil
 * It records the programmer's selection.
 * */

public class SelectionChangedOperation extends UserOperation {

	private String sourceName;
	private String[] selections;

	public SelectionChangedOperation(String sourceName, String[] messages) {
		this.sourceName = sourceName;
		this.selections = messages;
	}

	public SelectionChangedOperation() {
		super();
	}

	@Override
	public char getOperationSymbol() {
		return OperationSymbols.SELECTION_CHANGED_OPERATION_SYMBOL;
	}

	@Override
	public String getDescription() {
		return "Selection Changed Operation";
	}

	@Override
	protected void populateTextChunk(OperationTextChunk textChunk) {
		textChunk.append(sourceName);
		textChunk.append(selections);
	}

	@Override
	protected void populateXMLTextChunk(OperationTextChunk textChunk) {
		textChunk.concat("<SelectionChangedOperation>" + "\n");
		textChunk.concat("\t" + "<source>" + sourceName + "</source>" +"\n");
		textChunk.concat("<selections>");
		for(String selection: selections){
			textChunk.concat("\t" + "<selection>" + selection + "</selection>" +"\n");
		}
		textChunk.concat("</selections>");
		textChunk.concat("\t" + "<timestamp>" + getTime() + "</timestamp>" + "\n");
		textChunk.concat("</SelectionChangedOperation>" + "\n");
	}

	@Override
	protected void initializeFrom(OperationLexer operationLexer) {
		sourceName = operationLexer.readString();
		selections = operationLexer.readStrings();
	}

	@Override
	public void replay() throws Exception {
		//do nothing
	}

	

}
