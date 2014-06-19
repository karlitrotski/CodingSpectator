/**
 * This file is licensed under the University of Illinois/NCSA Open Source License. See LICENSE.TXT for details.
 */
package edu.illinois.codingtracker.operations.files.snapshoted;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;

import edu.illinois.codingtracker.helpers.ResourceHelper;
import edu.illinois.codingtracker.operations.OperationLexer;
import edu.illinois.codingtracker.operations.OperationTextChunk;
import edu.illinois.codingtracker.operations.files.FileOperation;
import edu.illinois.codingtracker.operations.OperationXMLTextChunk;

/**
 * 
 * @author Stas Negara
 * 
 */
public abstract class SnapshotedFileOperation extends FileOperation {

	protected String fileContent;

	public SnapshotedFileOperation() {
		super();
	}

	public SnapshotedFileOperation(IFile snapshotedFile) {
		super(snapshotedFile);
		fileContent= ResourceHelper.readFileContent(snapshotedFile);
	}

	public SnapshotedFileOperation(IFile snapshotedFile, String charsetName) {
		super(snapshotedFile);
		fileContent= ResourceHelper.readFileContent(snapshotedFile, charsetName);
	}

	@Override
	protected void populateTextChunk(OperationTextChunk textChunk) {
		super.populateTextChunk(textChunk);
		textChunk.append(fileContent);
	}
	
	@Override
	protected void populateXMLTextChunk(OperationXMLTextChunk textChunk){
		super.populateXMLTextChunk(textChunk);
		textChunk.concat("\t" + "<FileContent>" + "\n");
		textChunk.concat("\t" + fileContent + "\n");
		textChunk.concat("\t" + "</FileContent>" + "\n");
	}


	@Override
	protected void initializeFrom(OperationLexer operationLexer) {
		super.initializeFrom(operationLexer);
		fileContent= operationLexer.readString();
	}

	@Override
	public void replay() throws CoreException {
		createCompilationUnit(fileContent);
		externallyModifiedResources.remove(resourcePath);
	}

	@Override
	public String toString() {
		StringBuffer sb= new StringBuffer();
		sb.append("File content: " + fileContent + "\n");
		sb.append(super.toString());
		return sb.toString();
	}

}
