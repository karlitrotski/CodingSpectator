/**
 * This file is licensed under the University of Illinois/NCSA Open Source License. See LICENSE.TXT for details.
 */
package edu.illinois.codingtracker.operations.refactorings;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.ltk.core.refactoring.RefactoringDescriptor;

import edu.illinois.codingtracker.operations.OperationSymbols;
import edu.illinois.codingtracker.operations.OperationTextChunk;

/**
 * This operation is no longer recorded.
 * 
 * {@see NewStartedRefactoringOperation, FinishedRefactoringOperation}.
 * 
 * @author Stas Negara
 * 
 */
public class RedoneRefactoringOperation extends RefactoringOperation {

	public RedoneRefactoringOperation() {
		super();
	}

	public RedoneRefactoringOperation(RefactoringDescriptor refactoringDescriptor) {
		super(refactoringDescriptor);
	}

	@Override
	public char getOperationSymbol() {
		return OperationSymbols.REFACTORING_REDONE_SYMBOL;
	}

	@Override
	public String getDescription() {
		return "Redone refactoring";
	}

	@Override
	public void replayRefactoring(RefactoringDescriptor refactoringDescriptor) throws CoreException {
		if (!unperformedRefactorings.contains(getTime())) {
			getRefactoringUndoManager().performRedo(null, null);
		}
	}
	
	@Override
	protected void populateXMLTextChunk(OperationTextChunk textChunk) {
		textChunk.concat("<RedoneRefactoringOperation>"+"\n");
		super.populateXMLTextChunk(textChunk);
		textChunk.concat("\t" + "<timestamp>");
		textChunk.concat("" + getTime());
		textChunk.concat("</timestamp>" + "\n");
		textChunk.concat("</RedoneRefactoringOperation>"+"\n");
		
	}

}
