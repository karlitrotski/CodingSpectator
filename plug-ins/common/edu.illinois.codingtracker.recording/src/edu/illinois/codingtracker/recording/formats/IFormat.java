/**
 * This file is licensed under the University of Illinois/NCSA Open Source License. See LICENSE.TXT for details.
 */
package edu.illinois.codingtracker.recording.formats;

import edu.illinois.codingtracker.operations.*;
import edu.illinois.codingtracker.operations.conflicteditors.OpenedConflictEditorOperation;
import edu.illinois.codingtracker.operations.conflicteditors.SavedConflictEditorOperation;
import edu.illinois.codingtracker.operations.refactorings.FinishedRefactoringOperation;

/**
 * @author Kera Notebook 2.0
 *
 */
public interface IFormat {
	
	public abstract OperationTextChunk generateSerializationText();
	
	abstract void populateTextChunk(OpenedConflictEditorOperation openedConflictedEditorOperation, OperationTextChunk textChunk);
	
	abstract void populateTextChunk(SavedConflictEditorOperation savedConflictedEditorOperation, OperationTextChunk textChunk);
	
	abstract void populateTextChunk(FinishedRefactoringOperation finishedRefactoringOperation, OperationTextChunk textChunk);
	
	abstract void populateTextChunk(OpenedConflictEditorOperation openedConflictedEditorOperation, OperationTextChunk textChunk);
}
