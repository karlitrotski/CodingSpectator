/**
 * This file is licensed under the University of Illinois/NCSA Open Source License. See LICENSE.TXT for details.
 */
package edu.illinois.codingtracker.operations.files;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;

import edu.illinois.codingtracker.operations.OperationSymbols;
import edu.illinois.codingtracker.operations.OperationTextChunk;

/**
 * This operation is no longer recorded.
 * 
 * @author Stas Negara
 * 
 */
public class RefactoredSavedFileOperation extends FileOperation {

	public RefactoredSavedFileOperation() {
		super();
	}

	public RefactoredSavedFileOperation(IFile refactoredSavedile) {
		super(refactoredSavedile);
	}

	@Override
	protected char getOperationSymbol() {
		return OperationSymbols.FILE_REFACTORED_SAVED_SYMBOL;
	}

	@Override
	public String getDescription() {
		return "Saved file while refactored";
	}

	@Override
	public void replay() throws CoreException {
		saveResourceInEditor();
	}
	
	@Override
	protected void populateXMLTextChunk(OperationTextChunk textChunk){
		textChunk.append("<RefactoredSavedFileOperation>" + "\n");
		super.populateXMLTextChunk(textChunk);
		textChunk.append("\t" + "<timestamp>" + "\n");
		textChunk.append("\t" + getTime() + "\n");
		textChunk.append("\t" + "</timestamp>" + "\n");
		textChunk.append("</RefactoredSavedFileOperation>" + "\n");
	}


}
