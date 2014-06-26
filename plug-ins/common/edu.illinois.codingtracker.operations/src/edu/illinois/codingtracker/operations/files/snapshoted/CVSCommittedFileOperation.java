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
public class CVSCommittedFileOperation extends CommittedFileOperation {

	public CVSCommittedFileOperation() {
		super();
	}

	public CVSCommittedFileOperation(IFile committedFile, String revision, String committedRevision) {
		super(committedFile, revision, committedRevision);
	}

	@Override
	public char getOperationSymbol() {
		return OperationSymbols.FILE_CVS_COMMITTED_SYMBOL;
	}

	@Override
	public String getDescription() {
		return "CVS committed file";
	}
	
	@Override
	protected void populateXMLTextChunk(OperationTextChunk textChunk){
		textChunk.concat("<CVSCommittedFileOperation>" + "\n");
		super.populateXMLTextChunk(textChunk);
		textChunk.concat("\t" + "<timestamp>" + "\n");
		textChunk.concat("\t" + getTime() + "\n");
		textChunk.concat("\t" + "</timestamp>" + "\n");
		textChunk.concat("</CVSCommittedFileOperation>" + "\n");
	}

}
