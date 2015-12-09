/**
 * This file is licensed under the University of Illinois/NCSA Open Source License. See LICENSE.TXT for details.
 */
package edu.illinois.codingtracker.operations.files;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.ui.texteditor.ITextEditor;

import edu.illinois.codingtracker.helpers.Debugger;
import edu.illinois.codingtracker.operations.OperationSymbols;
import edu.illinois.codingtracker.operations.OperationTextChunk;
import edu.illinois.codingtracker.operations.resources.BreakableResourceOperation;

/**
 * Note: This is an exception in the class hierarchy, because SavedFileOperation should extends
 * FileOperation. But, it is needed to avoid duplicating the code required for breakable operations
 * (as well as creating artificial multiple inheritance in Java).
 * 
 * @author Stas Negara
 * 
 */
public class SavedFileOperation extends BreakableResourceOperation {

	public SavedFileOperation() {
		super();
	}

	public SavedFileOperation(IFile savedFile, boolean success) {
		super(savedFile, success);
	}

	@Override
	public char getOperationSymbol() {
		return OperationSymbols.FILE_SAVED_SYMBOL;
	}

	@Override
	public String getDescription() {
		return "Saved file";
	}

	@Override
	public void replayBreakableResourceOperation() throws CoreException {
		ITextEditor editor= saveResourceInEditor();
		if (editor == null) {
			Debugger.debugWarning("Ignored save of the non existent editor:\n" + this);
		}
	}
	
	@Override
	protected void populateXMLTextChunk(OperationTextChunk textChunk){
		textChunk.concat("<SavedFileOperation>" + "\n");
		super.populateXMLTextChunk(textChunk);
		textChunk.concat("\t" + "<timestamp>");
		textChunk.concat("" + getTime());
		textChunk.concat("</timestamp>" + "\n");
		textChunk.concat("</SavedFileOperation>" + "\n");
	}
	
	@Override
	protected void populateCSVTextChunk(OperationTextChunk textChunk){
		textChunk.concat("SavedFileOperation , "+ getTime()+ " ,");
		textChunk.concat("\"[{");
		super.populateCSVTextChunk(textChunk);
		textChunk.concat("}]\" \n");
	}

}
