/**
 * This file is licensed under the University of Illinois/NCSA Open Source License. See LICENSE.TXT for details.
 */
package edu.illinois.codingtracker.operations.files.snapshoted;

import org.eclipse.core.resources.IFile;

import edu.illinois.codingtracker.operations.OperationSymbols;
import edu.illinois.codingtracker.operations.OperationTextChunk;

/**
 * 
 * @author Stas Negara
 * 
 */
public class SVNCommittedFileOperation extends CommittedFileOperation {

	public SVNCommittedFileOperation() {
		super();
	}

	public SVNCommittedFileOperation(IFile committedFile, String revision, String committedRevision) {
		super(committedFile, revision, committedRevision);
	}

	@Override
	public char getOperationSymbol() {
		return OperationSymbols.FILE_SVN_COMMITTED_SYMBOL;
	}

	@Override
	public String getDescription() {
		return "SVN committed file";
	}
	
	@Override
	protected void populateXMLTextChunk(OperationTextChunk textChunk){
		textChunk.concat("<SVNCommittedFileOperation>" + "\n");
		super.populateXMLTextChunk(textChunk);
		textChunk.concat("\t" + "<timestamp>");
		textChunk.concat("" + getTime());
		textChunk.concat("</timestamp>" + "\n");
		textChunk.concat("</SVNCommittedFileOperation>" + "\n");
	}
	
	@Override
	protected void populateCSVTextChunk(OperationTextChunk textChunk){
		textChunk.concat("SVNCommittedFileOperation , "+ getTime()+ " ,");
		textChunk.concat("\"[{");
		super.populateCSVTextChunk(textChunk);
		textChunk.concat("}]\" \n");
	}

}
