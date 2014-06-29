package edu.illinois.codingtracker.operations.starts;

import edu.illinois.codingtracker.operations.OperationLexer;
import edu.illinois.codingtracker.operations.OperationSymbols;
import edu.illinois.codingtracker.operations.OperationTextChunk;

/**
 * 
 * @author Juraj Kubelka
 * @author Catalina Espinoza Inaipil - It stores when a launch is terminated and records exit values.
 * 
 */
public class TerminatedApplicationOperation extends ApplicationOperation {
	
	private int[] exitValues;

	public TerminatedApplicationOperation(String launchMode, String launchName, String application, String product, boolean useProduct, 
			int[] exitValues) {
		super(launchMode, launchName, application, product, useProduct);
		this.exitValues = exitValues;
	}
	
	@Override
	public char getOperationSymbol() {
		return OperationSymbols.APPLICATION_TERMINATED_SYMBOL;
	}

	@Override
	public String getDescription() {
		return "Terminated application";
	}
	
	@Override
	protected void populateTextChunk(OperationTextChunk textChunk) {
		super.populateTextChunk(textChunk);
		textChunk.append(useProduct);
	}

	@Override
	protected void populateXMLTextChunk(OperationTextChunk textChunk) {
		textChunk.concat("<TerminatedApplicationOperation>" + "\n");
		super.populateXMLTextChunk(textChunk);
		textChunk.concat("\t" + "<exitValues>" + "\n");
		textChunk.concat("\t" + exitValues + "\n");
		textChunk.concat("\t" + "</exitValues>" + "\n");		
		textChunk.concat("\t" + "<timestamp>" + "\n");
		textChunk.concat("\t" + getTime() + "\n");
		textChunk.concat("\t" + "</timestamp>" + "\n");
		textChunk.concat("</TerminatedApplicationOperation>" + "\n");
	}
	@Override
	public String toString() {
		StringBuffer sb= new StringBuffer();
		sb.append(super.toString());
		sb.append("Exit Values: [");
		for(int value: exitValues) {
			sb.append(" "+value);
		}
		sb.append("]" + "\n");
		return sb.toString();
	}
	@Override
	protected void initializeFrom(OperationLexer operationLexer) {
		super.initializeFrom(operationLexer);
		exitValues = operationLexer.readInts();
	}
}
