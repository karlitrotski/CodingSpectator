/**
 * This file is licensed under the University of Illinois/NCSA Open Source License. See LICENSE.TXT for details.
 */
package edu.illinois.codingtracker.operations.files;

import org.eclipse.core.resources.IFile;

import edu.illinois.codingtracker.helpers.Configuration;
import edu.illinois.codingtracker.operations.OperationLexer;
import edu.illinois.codingtracker.operations.OperationSymbols;
import edu.illinois.codingtracker.operations.OperationTextChunk;

/**
 * Note that code related to fields revision and committedRevision is duplicated from
 * CommittedFileOperation to avoid playing with mixes (and Java disallows multiple inheritance).
 * 
 * @author Stas Negara
 * 
 */
public class UpdatedFileOperation extends FileOperation {

	private String revision;

	private String committedRevision;


	public UpdatedFileOperation() {
		super();
	}

	public UpdatedFileOperation(IFile updatedFile, String revision, String committedRevision) {
		super(updatedFile);
		this.revision= revision;
		this.committedRevision= committedRevision;
	}

	@Override
	protected char getOperationSymbol() {
		return OperationSymbols.FILE_UPDATED_SYMBOL;
	}

	@Override
	public String getDescription() {
		return "Updated file";
	}

	@Override
	protected void populateTextChunk(OperationTextChunk textChunk) {
		super.populateTextChunk(textChunk);
		textChunk.append(revision);
		textChunk.append(committedRevision);
	}
	
	@Override
	protected void populateXMLTextChunk(OperationTextChunk textChunk){
		textChunk.concat("<UpdatedFileOperation>" + "\n");
		super.populateXMLTextChunk(textChunk);
		textChunk.concat("\t" + "<Revision>");
		textChunk.concat("" + revision);
		textChunk.concat("</Revision>" + "\n");
		textChunk.concat("\t" + "<CommittedRevision>");
		textChunk.concat("" + committedRevision);
		textChunk.concat("</CommittedRevision>" + "\n");
		textChunk.concat("</UpdatedFileOperation>" + "\n");
	}

	@Override
	protected void initializeFrom(OperationLexer operationLexer) {
		super.initializeFrom(operationLexer);
		if (!Configuration.isOldFormat) {
			revision= operationLexer.readString();
			committedRevision= operationLexer.readString();
		} else {
			revision= "";
			committedRevision= "";
		}
	}

	@Override
	public void replay() {
		externallyModifiedResources.add(resourcePath);
	}

	@Override
	public String toString() {
		StringBuffer sb= new StringBuffer();
		sb.append("Revision " + revision + "\n");
		sb.append("Committed revision " + committedRevision + "\n");
		sb.append(super.toString());
		return sb.toString();
	}

}
