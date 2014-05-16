package edu.illinois.codingtracker.operations.parts;

import org.eclipse.core.resources.IFile;

import edu.illinois.codingtracker.operations.OperationLexer;
import edu.illinois.codingtracker.operations.OperationSymbols;
import edu.illinois.codingtracker.operations.OperationTextChunk;
import edu.illinois.codingtracker.operations.files.FileOperation;
/**
 * @see edu.illinois.codingtracker.listeners.PartListener.partActivated(IWorkBenchPart part)
 * */
public class PartOperation extends FileOperation {
	
	public static final String ACTIVATED = "ACTIVATED";
	private String description;

	public PartOperation() {
		super();
	}

	public PartOperation(IFile file, String description) {
		super(file);
		this.description = description;
	}

	@Override
	protected char getOperationSymbol() {	
		return OperationSymbols.PART_OPERATION_SYMBOL;
	}

	@Override
	public String getDescription() {
		return "Part Operation, description: " + description;
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
