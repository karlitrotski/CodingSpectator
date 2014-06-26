package edu.illinois.codingtracker.operations.starts;

import edu.illinois.codingtracker.operations.OperationLexer;
import edu.illinois.codingtracker.operations.OperationSymbols;
import edu.illinois.codingtracker.operations.OperationTextChunk;

public class TerminatedApplicationOperation extends LaunchedApplicationOperation {
	
	//TODO: Esta clase esta mal implementada, hace un mal uso de la jerarquíade UserOperation. 
	// Se modifico para que la escritura por XML funcionara, pero hay que revisar.
	
	private String launchMode;

	private String launchName;

	private String application;

	private String product;

	private boolean useProduct;

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
		textChunk.append(launchMode);
		textChunk.append(launchName);
		textChunk.append(application);
		textChunk.append(product);
		textChunk.append(useProduct);
	}

	@Override
	protected void populateXMLTextChunk(OperationTextChunk textChunk) {
		textChunk.concat("<TerminatedApplicationOperation>" + "\n");
		textChunk.concat("\t" + "<Launch_mode>" + "\n");
		textChunk.concat("\t" + launchMode + "\n");
		textChunk.concat("\t" + "</Launch_mode>" + "\n");
		textChunk.concat("\t" + "<Launch_Name>" + "\n");
		textChunk.concat("\t" + launchName + "\n");
		textChunk.concat("\t" + "</Launch_Name>" + "\n");
		textChunk.concat("\t" + "<Application>" + "\n");
		textChunk.concat("\t" + application + "\n");
		textChunk.concat("\t" + "</Application>" + "\n");
		textChunk.concat("\t" + "<Product>" + "\n");
		textChunk.concat("\t" + product + "\n");
		textChunk.concat("\t" + "</Product>" + "\n");
		textChunk.concat("\t" + "<UseProduct>" + "\n");
		textChunk.concat("\t" + useProduct + "\n");
		textChunk.concat("\t" + "</UseProduct>" + "\n");
		textChunk.concat("\t" + "<exitValues>" + "\n");
		textChunk.concat("\t" + exitValues + "\n");
		textChunk.concat("\t" + "</exitValues>" + "\n");		
		textChunk.concat("\t" + "<timestamp>" + "\n");
		textChunk.concat("\t" + getTime() + "\n");
		textChunk.concat("\t" + "</timestamp>" + "\n");
		textChunk.concat("</TerminatedApplicationOperation>" + "\n");
	}

	@Override
	protected void initializeFrom(OperationLexer operationLexer) {
		super.initializeFrom(operationLexer);
		exitValues = operationLexer.readInts();
	}
}
