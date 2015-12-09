/**
 * This file is licensed under the University of Illinois/NCSA Open Source License. See LICENSE.TXT for details.
 */
package edu.illinois.codingtracker.operations.resources;

import org.eclipse.core.resources.IResource;

import edu.illinois.codingtracker.operations.OperationLexer;
import edu.illinois.codingtracker.operations.OperationTextChunk;

/**
 * 
 * @author Stas Negara
 * 
 */
public abstract class UpdatedResourceOperation extends BreakableResourceOperation {

	protected int updateFlags;


	public UpdatedResourceOperation() {
		super();
	}

	public UpdatedResourceOperation(IResource resource, int updateFlags, boolean success) {
		super(resource, success);
		this.updateFlags= updateFlags;
	}

	@Override
	protected void populateTextChunk(OperationTextChunk textChunk) {
		super.populateTextChunk(textChunk);
		textChunk.append(updateFlags);
	}
	
	@Override
	protected void populateXMLTextChunk(OperationTextChunk textChunk){
		super.populateXMLTextChunk(textChunk);
		textChunk.concat("\t" + "<updateFlags>" + "\n");
		textChunk.concat("\t" + updateFlags + "\n");
		textChunk.concat("\t" + "</updateFlags>" + "\n");
	}
	
	@Override
	protected void populateCSVTextChunk(OperationTextChunk textChunk){
		super.populateCSVTextChunk(textChunk);
		textChunk.concat(",");
		textChunk.concat("updateFlags : "+ updateFlags);
	}

	@Override
	protected void initializeFrom(OperationLexer operationLexer) {
		super.initializeFrom(operationLexer);
		updateFlags= operationLexer.readInt();
	}

	@Override
	public String toString() {
		StringBuffer sb= new StringBuffer();
		sb.append("Update flags: " + updateFlags + "\n");
		sb.append(super.toString());
		return sb.toString();
	}

}
