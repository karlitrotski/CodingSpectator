package edu.illinois.codingtracker.operations.parts;

import org.eclipse.core.resources.IFile;

import edu.illinois.codingtracker.operations.OperationSymbols;
import edu.illinois.codingtracker.operations.files.FileOperation;
/**
 * @see edu.illinois.codingtracker.listeners.PartListener.partActivated(IWorkBenchPart part)
 * */
public class PartActivatedOperation extends FileOperation {
	
	public PartActivatedOperation() {
		super();
	}

	public PartActivatedOperation(IFile activatedFile) {
		super(activatedFile);
	}
	@Override
	protected char getOperationSymbol() {	
		return OperationSymbols.PART_ACTIVATED_SYMBOL;
	}

	@Override
	public String getDescription() {
		return "Part activated";
	}

	@Override
	public void replay() throws Exception {
		// TODO Auto-generated method stub

	}

}
