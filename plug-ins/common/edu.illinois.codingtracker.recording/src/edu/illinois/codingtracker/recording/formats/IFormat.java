/**
 * This file is licensed under the University of Illinois/NCSA Open Source License. See LICENSE.TXT for details.
 */
package edu.illinois.codingtracker.recording.formats;

import edu.illinois.codingtracker.operations.*;
import edu.illinois.codingtracker.operations.conflicteditors.OpenedConflictEditorOperation;
import edu.illinois.codingtracker.operations.conflicteditors.SavedConflictEditorOperation;
import edu.illinois.codingtracker.operations.junit.TestCaseFinishedOperation;
import edu.illinois.codingtracker.operations.junit.TestCaseStartedOperation;
import edu.illinois.codingtracker.operations.junit.TestSessionFinishedOperation;
import edu.illinois.codingtracker.operations.junit.TestSessionLaunchedOperation;
import edu.illinois.codingtracker.operations.junit.TestSessionStartedOperation;
import edu.illinois.codingtracker.operations.options.ProjectOptionsChangedOperation;
import edu.illinois.codingtracker.operations.options.WorkspaceOptionsChangedOperation;
import edu.illinois.codingtracker.operations.refactorings.FinishedRefactoringOperation;
import edu.illinois.codingtracker.operations.refactorings.NewStartedRefactoringOperation;
import edu.illinois.codingtracker.operations.starts.LaunchedApplicationOperation;

/**
 * @author Matías Pardo G.
 *
 */
public interface IFormat {
	
	public OperationTextChunk generateSerializationText();
	
	//ConflictedEditorOperation Block
	
	OperationTextChunk populateTextChunkConflictEditorOperation();
	
	void populateTextChunk(OpenedConflictEditorOperation openedConflictedEditorOperation, OperationTextChunk textChunk);
	
	void populateTextChunk(SavedConflictEditorOperation savedConflictedEditorOperation, OperationTextChunk textChunk);
	
	//FinishedRefactoringOperation Block
	
	void populateTextChunk(FinishedRefactoringOperation finishedRefactoringOperation, OperationTextChunk textChunk);
	
	//JUnitOperation Block
	
	OperationTextChunk populateTextChunkJUnitOperation();
	
	void populateTextChunk(TestCaseFinishedOperation testCaseFinishedOperation, OperationTextChunk textChunk);
	
	void populateTextChunk(TestCaseStartedOperation testCaseStartedOperation, OperationTextChunk textChunk);
	
	void populateTextChunk(TestSessionFinishedOperation testSessionFinishedOperation, OperationTextChunk textChunk);
	
	void populateTextChunk(TestSessionLaunchedOperation testSessionLaunchedOperation, OperationTextChunk textChunk);
	
	void populateTextChunk(TestSessionStartedOperation testSessionStartedOperation, OperationTextChunk textChunk);
	
	//LaunchedApplicationOperation Block
	
	void populateTextChunk(LaunchedApplicationOperation launchedApplicationOperation, OperationTextChunk textChunk);
	
	//NewStartedRefactoringOperation Block
	
	void populateTextChunk(NewStartedRefactoringOperation newStartedRefactoringOperation, OperationTextChunk textChunk);
	
	//OptionsChangedOperation Block
	
	OperationTextChunk populateOptionsChangedOperation();
	
	void populateTextChunk(ProjectOptionsChangedOperation projectOptionsChangedOperation, OperationTextChunk textChunk);
	
	void populateTextChunk(WorkspaceOptionsChangedOperation workspaceOptionsChangedOperation, OperationTextChunk textChunk);
	
	
	
	
}
