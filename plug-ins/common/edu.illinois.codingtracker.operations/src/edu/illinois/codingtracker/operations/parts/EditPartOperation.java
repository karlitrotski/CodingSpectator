package edu.illinois.codingtracker.operations.parts;

import org.eclipse.core.resources.IFile;

import edu.illinois.codingtracker.operations.OperationLexer;
import edu.illinois.codingtracker.operations.OperationSymbols;
import edu.illinois.codingtracker.operations.OperationTextChunk;
import edu.illinois.codingtracker.operations.files.FileOperation;
/**
 * @see edu.illinois.codingtracker.listeners.PartListener.partActivated(IWorkBenchPart part)
 * */
public class EditPartOperation extends FileOperation implements IPartState {
	
	private String description;

	public EditPartOperation() {
		super();
	}

	public EditPartOperation(IFile file, String description) {
		super(file);
		this.description = description;
	}

	@Override
	protected char getOperationSymbol() {	
		return OperationSymbols.EDIT_PART_OPERATION_SYMBOL;
	}

	@Override
	public String getDescription() {
		return "Edit Part Operation, description: " + description;
	}

	@Override
	public void replay() throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	protected void populateTextChunk(OperationTextChunk textChunk) {
		textChunk.append(description);
		super.populateTextChunk(textChunk);
	}

	@Override
	protected void initializeFrom(OperationLexer operationLexer) {
		description = operationLexer.readString();
		super.initializeFrom(operationLexer);
	}

}
