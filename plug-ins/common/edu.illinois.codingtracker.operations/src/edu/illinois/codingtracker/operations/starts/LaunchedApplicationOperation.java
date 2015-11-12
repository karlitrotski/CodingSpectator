/**
 * This file is licensed under the University of Illinois/NCSA Open Source License. See LICENSE.TXT for details.
 */
package edu.illinois.codingtracker.operations.starts;

import edu.illinois.codingtracker.operations.OperationSymbols;
import edu.illinois.codingtracker.operations.OperationTextChunk;

/**
 * 
 * @author Stas Negara
 * 
 */
public class LaunchedApplicationOperation extends ApplicationOperation {

	public LaunchedApplicationOperation() {
		super();
	}

	public LaunchedApplicationOperation(String launchMode, String launchName, String application, String product, boolean useProduct) {
		super(launchMode, launchName, application, product, useProduct);
	}

	@Override
	public char getOperationSymbol() {
		return OperationSymbols.APPLICATION_LAUNCHED_SYMBOL;
	}

	@Override
	public String getDescription() {
		return "Launched application";
	}

	@Override
	protected void populateXMLTextChunk(OperationTextChunk textChunk){
		textChunk.concat("<LaunchedApplicationOperation>" + "\n");
		super.populateXMLTextChunk(textChunk);
		textChunk.concat("\t" + "<timestamp>" + "\n");
		textChunk.concat("\t" + getTime() + "\n");
		textChunk.concat("\t" + "</timestamp>" + "\n");
		textChunk.concat("</LaunchedApplicationOperation>" + "\n");
	}
	
	@Override
	protected void populateCSVTextChunk(OperationTextChunk textChunk){
		textChunk.concat("LaunchedApplicationOperation , "+ getTime()+ " ,");
		textChunk.concat("\"[{");
		super.populateCSVTextChunk(textChunk);
		textChunk.concat("}]\" \n");
	}

}
