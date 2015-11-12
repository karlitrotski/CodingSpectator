package edu.illinois.codingtracker.operations.starts;

import edu.illinois.codingtracker.helpers.Configuration;
import edu.illinois.codingtracker.operations.OperationLexer;
import edu.illinois.codingtracker.operations.OperationTextChunk;
import edu.illinois.codingtracker.operations.UserOperation;

/**
 * @author Juraj Kubelka, @author Catalina Espinoza Inaipil 
 * extracted it from LaunchedApplicationOperation.
 */
public abstract class ApplicationOperation extends UserOperation {

	protected String launchMode;
	protected String launchName;
	protected String application;
	protected String product;
	protected boolean useProduct;

	public ApplicationOperation() {
		super();
	}

	public ApplicationOperation(String launchMode, String launchName,
			String application, String product, boolean useProduct) {
		this.launchMode= launchMode;
		this.launchName= launchName;
		this.application= application;
		this.product= product;
		this.useProduct= useProduct;
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
		textChunk.concat("\t" + "<LaunchMode>" + launchMode + "</LaunchMode>" + "\n");
		textChunk.concat("\t" + "<LaunchName>" + launchName + "</LaunchName>" + "\n");
		textChunk.concat("\t" + "<Application>" + application + "</Application>" + "\n");
		textChunk.concat("\t" + "<Product>" + product + "</Product>" + "\n");
		textChunk.concat("\t" + "<UseProduct>" + (useProduct ? 1 : 0) + "</UseProduct>" + "\n");
	}
	
	protected void populateCSVTextChunk(OperationTextChunk textChunk){
		textChunk.concat("LaunchMode : "+ launchMode + ",");
		textChunk.concat("LaunchName : "+ launchName + ",");
		textChunk.concat("Application : "+ application + ",");
		textChunk.concat("Product : "+ product + ",");
		textChunk.concat("UseProduct : "+ (useProduct ? 1 : 0));
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