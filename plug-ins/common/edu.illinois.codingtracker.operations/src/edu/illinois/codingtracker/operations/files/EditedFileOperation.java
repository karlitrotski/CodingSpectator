/**
 * This file is licensed under the University of Illinois/NCSA Open Source License. See LICENSE.TXT for details.
 */
package edu.illinois.codingtracker.operations.files;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;

import edu.illinois.codingtracker.compare.helpers.EditorHelper;
import edu.illinois.codingtracker.operations.OperationSymbols;
import edu.illinois.codingtracker.operations.OperationTextChunk;

/**
 * 
 * @author Stas Negara
 * 
 */
public class EditedFileOperation extends FileOperation {

	public EditedFileOperation() {
		super();
	}

	public EditedFileOperation(IFile editedFile) {
		super(editedFile);
	}

	@Override
	protected char getOperationSymbol() {
		return OperationSymbols.FILE_EDITED_SYMBOL;
	}

	@Override
	public String getDescription() {
		return "Edited file";
	}

	@Override
	public void replay() throws CoreException {
		currentEditor= EditorHelper.openEditor(resourcePath);
	}
	
	@Override
	protected void populateXMLTextChunk(OperationTextChunk textChunk){
		textChunk.concat("<EditedFileOperation>" + "\n");
		super.populateXMLTextChunk(textChunk);
		textChunk.concat("\t" + "<timestamp>" + "\n");
		textChunk.concat("\t" + getTime() + "\n");
		textChunk.concat("\t" + "</timestamp>" + "\n");
		textChunk.concat("</EditedFileOperation>" + "\n");
	}


}
