/**
 * This file is licensed under the University of Illinois/NCSA Open Source License. See LICENSE.TXT for details.
 */
package edu.illinois.codingtracker.operations.starts;

import edu.illinois.codingtracker.helpers.Configuration;
import edu.illinois.codingtracker.operations.OperationLexer;
import edu.illinois.codingtracker.operations.OperationSymbols;
import edu.illinois.codingtracker.operations.OperationTextChunk;
import edu.illinois.codingtracker.operations.UserOperation;

/**
 * 
 * @author Stas Negara
 * 
 */
public class LaunchedApplicationOperation extends UserOperation {

	private String launchMode;

	private String launchName;

	private String application;

	private String product;

	private boolean useProduct;

	public LaunchedApplicationOperation() {
		super();
	}

	public LaunchedApplicationOperation(String launchMode, String launchName, String application, String product, boolean useProduct) {
		super();
		this.launchMode= launchMode;
		this.launchName= launchName;
		this.application= application;
		this.product= product;
		this.useProduct= useProduct;
	}

	@Override
	protected char getOperationSymbol() {
		return OperationSymbols.APPLICATION_LAUNCHED_SYMBOL;
	}

	@Override
	public String getDescription() {
		return "Launched application";
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
	protected void populateXMLTextChunk(OperationTextChunk textChunk){
		textChunk.concat("<LaunchedApplicationOperation>" + "\n");
		textChunk.concat("\t" + "<Launch_mode>");
		textChunk.concat("" + launchMode);
		textChunk.concat("</Launch_mode>" + "\n");
		textChunk.concat("\t" + "<Launch_Name>");
		textChunk.concat("" + launchName);
		textChunk.concat("</Launch_Name>" + "\n");
		textChunk.concat("\t" + "<Application>");
		textChunk.concat("" + application);
		textChunk.concat("</Application>" + "\n");
		textChunk.concat("\t" + "<Product>");
		textChunk.concat("" + product);
		textChunk.concat("</Product>" + "\n");
		textChunk.concat("\t" + "<UseProduct>");
		textChunk.concat("" + useProduct );
		textChunk.concat("</UseProduct>" + "\n");
		textChunk.concat("\t" + "<timestamp>");
		textChunk.concat("" + getTime());
		textChunk.concat("</timestamp>" + "\n");
		textChunk.concat("</LaunchedApplicationOperation>" + "\n");
	}

	@Override
	protected void initializeFrom(OperationLexer operationLexer) {
		launchMode= operationLexer.readString();
		launchName= operationLexer.readString();
		application= operationLexer.readString();
		product= operationLexer.readString();
		if (!Configuration.isOldFormat) {
			useProduct= operationLexer.readBoolean();
		} else {
			useProduct= Boolean.valueOf(operationLexer.readString());
		}
	}

	@Override
	public void replay() throws Exception {
		//do nothing
	}

	@Override
	public String toString() {
		StringBuffer sb= new StringBuffer();
		sb.append("Launch mode: " + launchMode + "\n");
		sb.append("Launch name: " + launchName + "\n");
		sb.append("Application: " + application + "\n");
		sb.append("Product: " + product + "\n");
		sb.append("UseProduct: " + useProduct + "\n");
		sb.append(super.toString());
		return sb.toString();
	}

}
