/**
 * This file is licensed under the University of Illinois/NCSA Open Source License. See LICENSE.TXT for details.
 */
package edu.illinois.codingtracker.recording.formats;

import edu.illinois.codingtracker.operations.*;
import edu.illinois.codingtracker.operations.conflicteditors.ClosedConflictEditorOperation;
import edu.illinois.codingtracker.operations.conflicteditors.OpenedConflictEditorOperation;
import edu.illinois.codingtracker.operations.conflicteditors.SavedConflictEditorOperation;
import edu.illinois.codingtracker.operations.files.ClosedFileOperation;
import edu.illinois.codingtracker.operations.files.EditedFileOperation;
import edu.illinois.codingtracker.operations.files.EditedUnsychronizedFileOperation;
import edu.illinois.codingtracker.operations.files.RefactoredSavedFileOperation;
import edu.illinois.codingtracker.operations.files.SavedFileOperation;
import edu.illinois.codingtracker.operations.files.UpdatedFileOperation;
import edu.illinois.codingtracker.operations.files.snapshoted.CVSCommittedFileOperation;
import edu.illinois.codingtracker.operations.files.snapshoted.CVSInitiallyCommittedFileOperation;
import edu.illinois.codingtracker.operations.files.snapshoted.NewFileOperation;
import edu.illinois.codingtracker.operations.files.snapshoted.RefreshedFileOperation;
import edu.illinois.codingtracker.operations.files.snapshoted.SVNCommittedFileOperation;
import edu.illinois.codingtracker.operations.files.snapshoted.SVNInitiallyCommittedFileOperation;
import edu.illinois.codingtracker.operations.junit.TestCaseFinishedOperation;
import edu.illinois.codingtracker.operations.junit.TestCaseStartedOperation;
import edu.illinois.codingtracker.operations.junit.TestSessionFinishedOperation;
import edu.illinois.codingtracker.operations.junit.TestSessionLaunchedOperation;
import edu.illinois.codingtracker.operations.junit.TestSessionStartedOperation;
import edu.illinois.codingtracker.operations.options.ProjectOptionsChangedOperation;
import edu.illinois.codingtracker.operations.options.WorkspaceOptionsChangedOperation;
import edu.illinois.codingtracker.operations.refactorings.FinishedRefactoringOperation;
import edu.illinois.codingtracker.operations.refactorings.NewStartedRefactoringOperation;
import edu.illinois.codingtracker.operations.refactorings.PerformedRefactoringOperation;
import edu.illinois.codingtracker.operations.refactorings.RedoneRefactoringOperation;
import edu.illinois.codingtracker.operations.refactorings.UndoneRefactoringOperation;
import edu.illinois.codingtracker.operations.references.ReferencingProjectsChangedOperation;
import edu.illinois.codingtracker.operations.resources.CopiedResourceOperation;
import edu.illinois.codingtracker.operations.resources.CreatedResourceOperation;
import edu.illinois.codingtracker.operations.resources.DeletedResourceOperation;
import edu.illinois.codingtracker.operations.resources.ExternallyModifiedResourceOperation;
import edu.illinois.codingtracker.operations.resources.MovedResourceOperation;
import edu.illinois.codingtracker.operations.starts.LaunchedApplicationOperation;
import edu.illinois.codingtracker.operations.starts.StartedEclipseOperation;
import edu.illinois.codingtracker.operations.starts.StartedRefactoringOperation;
import edu.illinois.codingtracker.operations.textchanges.PerformedConflictEditorTextChangeOperation;
import edu.illinois.codingtracker.operations.textchanges.PerformedTextChangeOperation;
import edu.illinois.codingtracker.operations.textchanges.RedoneConflictEditorTextChangeOperation;
import edu.illinois.codingtracker.operations.textchanges.RedoneTextChangeOperation;
import edu.illinois.codingtracker.operations.textchanges.UndoneConflictEditorTextChangeOperation;
import edu.illinois.codingtracker.operations.textchanges.UndoneTextChangeOperation;

/**
 * @author Matias Pardo G.
 *
 */
public interface IFormat {
	
	public OperationTextChunk generateSerializationText();
	
	//ConflictedEditorOperation Block
	
	OperationTextChunk populateTextChunkConflictEditorOperation();
	
	void populateTextChunk(ClosedConflictEditorOperation openedConflictedEditorOperation, OperationTextChunk textChunk);
	
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
	
	//RefactoringOperation Block
	
	OperationTextChunk populateRefactoringOperation();
	
	void populateTextChunk(PerformedRefactoringOperation performedRefactoringOperation, OperationTextChunk textChunk);
	
	void populateTextChunk(RedoneRefactoringOperation redoneRefactoringOperation, OperationTextChunk textChunk);
	
	void populateTextChunk(UndoneRefactoringOperation undoneRefactoringOperation, OperationTextChunk textChunk);
	
	//ReferencingProjectsChangedOperation Block
	
	void populateTextChunk(ReferencingProjectsChangedOperation referencingProjectsChangedOperation, OperationTextChunk textChunk);
	
	//ResourceOperation Block
	
	OperationTextChunk populateResourceOperation();
	
	void populateTextChunk(ExternallyModifiedResourceOperation externallyModifiedResourceOperation, OperationTextChunk textChunk);
	
	//BreakableResourceOperation Sub-Block of ResourceOperation Block
	
	OperationTextChunk populateBreakableResourceOperation();
	
	void populateTextChunk(SavedFileOperation savedFileOperation, OperationTextChunk textChunk);
	
	//UpdatedResourceOperation Sub-Block of BreakableResourceOperation Sub-Block
	
	OperationTextChunk populateUpdatedResourceOperation();
	
	void populateTextChunk(CreatedResourceOperation createdResourceOperation, OperationTextChunk textChunk);
	
	void populateTextChunk(DeletedResourceOperation deletedResourceOperation, OperationTextChunk textChunk);
	
	//ReorganizedResourceOperation Sub-Block of UpdatedResourceOperation Sub-Block
	
	OperationTextChunk populateReorganizedResourceOperation();
	
	void populateTextChunk(CopiedResourceOperation copiedResourceOperation, OperationTextChunk textChunk);
	
	void populateTextChunk(MovedResourceOperation movedResourceOperation, OperationTextChunk textChunk);
	
	//FileOperation Sub-Block of ResourceOperation
	
	OperationTextChunk populateFileOperation();
	
	void populateTextChunk(ClosedFileOperation closedFileOperation, OperationTextChunk textChunk);
	
	void populateTextChunk(EditedFileOperation editedFileOperation, OperationTextChunk textChunk);
	
	void populateTextChunk(EditedUnsychronizedFileOperation editedUnsychronizedFileOperation, OperationTextChunk textChunk);
	
	void populateTextChunk(RefactoredSavedFileOperation refactoredSavedFileOperation, OperationTextChunk textChunk);
	
	void populateTextChunk(UpdatedFileOperation updatedFileOperation, OperationTextChunk textChunk);
	
	//SnapshotedFileOperation Sub-Block of FileOperation Sub-Block
	
	OperationTextChunk populateSnapshotedFileOperation();
	
	void populateTextChunk(NewFileOperation newFileOperation, OperationTextChunk textChunk);
	
	void populateTextChunk(RefreshedFileOperation refreshedFileOperation, OperationTextChunk textChunk);
	
	//CommittedFileOperation Sub-Block of SnapshotedFileOperation Sub-Block
	
	OperationTextChunk populateCommitedFileOperation();
	
	void populateTextChunk(CVSCommittedFileOperation cVSCommittedFileOperation, OperationTextChunk textChunk);
	
	void populateTextChunk(CVSInitiallyCommittedFileOperation cVSInitiallyCommittedFileOperation, OperationTextChunk textChunk);
	
	void populateTextChunk(SVNCommittedFileOperation sVNCommittedFileOperation, OperationTextChunk textChunk);
	
	void populateTextChunk(SVNInitiallyCommittedFileOperation sVNInitiallyCommittedFileOperation, OperationTextChunk textChunk);
	
	//StartedEclipseOperation Block
	
	void populateTextChunk(StartedEclipseOperation startedEclipseOperation, OperationTextChunk textChunk);
	
	//StartedRefactoringOperation Block
	
	void populateTextChunk(StartedRefactoringOperation startedRefactoringOperation, OperationTextChunk textChunk);
	
	//TextChangeOperation Block
	
	OperationTextChunk populateTextChangeOperation();
	
	void populateTextChunk(PerformedTextChangeOperation performedTextChangeOperation, OperationTextChunk textChunk);
	
	void populateTextChunk(RedoneTextChangeOperation redoneTextChangeOperation, OperationTextChunk textChunk);
	
	void populateTextChunk(UndoneTextChangeOperation undoneTextChangeOperation, OperationTextChunk textChunk);
	
	//ConflictEditorTextChangeOperation Sub-Block of TextChangeOperation Block
	
	OperationTextChunk populateConflictEditorTextChangeOperation();
	
	void populateTextChunk(PerformedConflictEditorTextChangeOperation performedConflictEditorTextChangeOperation, OperationTextChunk textChunk);
	
	void populateTextChunk(RedoneConflictEditorTextChangeOperation redoneConflictEditorTextChangeOperation, OperationTextChunk textChunk);
	
	void populateTextChunk(UndoneConflictEditorTextChangeOperation undoneConflictEditorTextChangeOperation, OperationTextChunk textChunk);
	
}
