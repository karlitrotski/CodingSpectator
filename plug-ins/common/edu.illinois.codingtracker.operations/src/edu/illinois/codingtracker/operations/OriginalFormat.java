/**
 * This file is licensed under the University of Illinois/NCSA Open Source License. See LICENSE.TXT for details.
 */
package edu.illinois.codingtracker.operations;

import java.util.Date;

import org.eclipse.core.runtime.AssertionFailedException;
import org.eclipse.ui.IEditorPart;

import edu.illinois.codingtracker.helpers.Configuration;
import edu.illinois.codingtracker.helpers.Debugger;
import edu.illinois.codingtracker.operations.OperationLexer;
import edu.illinois.codingtracker.operations.OperationTextChunk;
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
public class OriginalFormat implements IFormat {

	/* (non-Javadoc)
	 * @see edu.illinois.codingtracker.operations.IFormat#generateSerializationText()
	 */
	@Override
	public OperationTextChunk generateSerializationText(UserOperation userOperation) {
		OperationTextChunk textChunk= new OperationTextChunk(userOperation.getOperationSymbol());
		userOperation.populateTextChunk(this, textChunk);
		textChunk.append(userOperation.getTime());
		Debugger.debugTextChunk(userOperation.getDescription() + ": ", textChunk);
		return textChunk;
	}

	/* (non-Javadoc)
	 * @see edu.illinois.codingtracker.operations.IFormat#populateTextChunkConflictEditorOperation()
	 */
	@Override
	public OperationTextChunk populateTextChunkConflictEditorOperation() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see edu.illinois.codingtracker.operations.IFormat#populateTextChunk(edu.illinois.codingtracker.operations.conflicteditors.ClosedConflictEditorOperation, edu.illinois.codingtracker.operations.OperationTextChunk)
	 */
	@Override
	public void populateTextChunk(
			ClosedConflictEditorOperation openedConflictedEditorOperation,
			OperationTextChunk textChunk) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see edu.illinois.codingtracker.operations.IFormat#populateTextChunk(edu.illinois.codingtracker.operations.conflicteditors.OpenedConflictEditorOperation, edu.illinois.codingtracker.operations.OperationTextChunk)
	 */
	@Override
	public void populateTextChunk(
			OpenedConflictEditorOperation openedConflictedEditorOperation,
			OperationTextChunk textChunk) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see edu.illinois.codingtracker.operations.IFormat#populateTextChunk(edu.illinois.codingtracker.operations.conflicteditors.SavedConflictEditorOperation, edu.illinois.codingtracker.operations.OperationTextChunk)
	 */
	@Override
	public void populateTextChunk(
			SavedConflictEditorOperation savedConflictedEditorOperation,
			OperationTextChunk textChunk) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see edu.illinois.codingtracker.operations.IFormat#populateTextChunk(edu.illinois.codingtracker.operations.refactorings.FinishedRefactoringOperation, edu.illinois.codingtracker.operations.OperationTextChunk)
	 */
	@Override
	public void populateTextChunk(
			FinishedRefactoringOperation finishedRefactoringOperation,
			OperationTextChunk textChunk) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see edu.illinois.codingtracker.operations.IFormat#populateTextChunkJUnitOperation()
	 */
	@Override
	public OperationTextChunk populateTextChunkJUnitOperation() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see edu.illinois.codingtracker.operations.IFormat#populateTextChunk(edu.illinois.codingtracker.operations.junit.TestCaseFinishedOperation, edu.illinois.codingtracker.operations.OperationTextChunk)
	 */
	@Override
	public void populateTextChunk(
			TestCaseFinishedOperation testCaseFinishedOperation,
			OperationTextChunk textChunk) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see edu.illinois.codingtracker.operations.IFormat#populateTextChunk(edu.illinois.codingtracker.operations.junit.TestCaseStartedOperation, edu.illinois.codingtracker.operations.OperationTextChunk)
	 */
	@Override
	public void populateTextChunk(
			TestCaseStartedOperation testCaseStartedOperation,
			OperationTextChunk textChunk) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see edu.illinois.codingtracker.operations.IFormat#populateTextChunk(edu.illinois.codingtracker.operations.junit.TestSessionFinishedOperation, edu.illinois.codingtracker.operations.OperationTextChunk)
	 */
	@Override
	public void populateTextChunk(
			TestSessionFinishedOperation testSessionFinishedOperation,
			OperationTextChunk textChunk) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see edu.illinois.codingtracker.operations.IFormat#populateTextChunk(edu.illinois.codingtracker.operations.junit.TestSessionLaunchedOperation, edu.illinois.codingtracker.operations.OperationTextChunk)
	 */
	@Override
	public void populateTextChunk(
			TestSessionLaunchedOperation testSessionLaunchedOperation,
			OperationTextChunk textChunk) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see edu.illinois.codingtracker.operations.IFormat#populateTextChunk(edu.illinois.codingtracker.operations.junit.TestSessionStartedOperation, edu.illinois.codingtracker.operations.OperationTextChunk)
	 */
	@Override
	public void populateTextChunk(
			TestSessionStartedOperation testSessionStartedOperation,
			OperationTextChunk textChunk) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see edu.illinois.codingtracker.operations.IFormat#populateTextChunk(edu.illinois.codingtracker.operations.starts.LaunchedApplicationOperation, edu.illinois.codingtracker.operations.OperationTextChunk)
	 */
	@Override
	public void populateTextChunk(
			LaunchedApplicationOperation launchedApplicationOperation,
			OperationTextChunk textChunk) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see edu.illinois.codingtracker.operations.IFormat#populateTextChunk(edu.illinois.codingtracker.operations.refactorings.NewStartedRefactoringOperation, edu.illinois.codingtracker.operations.OperationTextChunk)
	 */
	@Override
	public void populateTextChunk(
			NewStartedRefactoringOperation newStartedRefactoringOperation,
			OperationTextChunk textChunk) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see edu.illinois.codingtracker.operations.IFormat#populateOptionsChangedOperation()
	 */
	@Override
	public OperationTextChunk populateOptionsChangedOperation() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see edu.illinois.codingtracker.operations.IFormat#populateTextChunk(edu.illinois.codingtracker.operations.options.ProjectOptionsChangedOperation, edu.illinois.codingtracker.operations.OperationTextChunk)
	 */
	@Override
	public void populateTextChunk(
			ProjectOptionsChangedOperation projectOptionsChangedOperation,
			OperationTextChunk textChunk) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see edu.illinois.codingtracker.operations.IFormat#populateTextChunk(edu.illinois.codingtracker.operations.options.WorkspaceOptionsChangedOperation, edu.illinois.codingtracker.operations.OperationTextChunk)
	 */
	@Override
	public void populateTextChunk(
			WorkspaceOptionsChangedOperation workspaceOptionsChangedOperation,
			OperationTextChunk textChunk) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see edu.illinois.codingtracker.operations.IFormat#populateRefactoringOperation()
	 */
	@Override
	public OperationTextChunk populateRefactoringOperation() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see edu.illinois.codingtracker.operations.IFormat#populateTextChunk(edu.illinois.codingtracker.operations.refactorings.PerformedRefactoringOperation, edu.illinois.codingtracker.operations.OperationTextChunk)
	 */
	@Override
	public void populateTextChunk(
			PerformedRefactoringOperation performedRefactoringOperation,
			OperationTextChunk textChunk) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see edu.illinois.codingtracker.operations.IFormat#populateTextChunk(edu.illinois.codingtracker.operations.refactorings.RedoneRefactoringOperation, edu.illinois.codingtracker.operations.OperationTextChunk)
	 */
	@Override
	public void populateTextChunk(
			RedoneRefactoringOperation redoneRefactoringOperation,
			OperationTextChunk textChunk) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see edu.illinois.codingtracker.operations.IFormat#populateTextChunk(edu.illinois.codingtracker.operations.refactorings.UndoneRefactoringOperation, edu.illinois.codingtracker.operations.OperationTextChunk)
	 */
	@Override
	public void populateTextChunk(
			UndoneRefactoringOperation undoneRefactoringOperation,
			OperationTextChunk textChunk) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see edu.illinois.codingtracker.operations.IFormat#populateTextChunk(edu.illinois.codingtracker.operations.references.ReferencingProjectsChangedOperation, edu.illinois.codingtracker.operations.OperationTextChunk)
	 */
	@Override
	public void populateTextChunk(
			ReferencingProjectsChangedOperation referencingProjectsChangedOperation,
			OperationTextChunk textChunk) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see edu.illinois.codingtracker.operations.IFormat#populateResourceOperation()
	 */
	@Override
	public OperationTextChunk populateResourceOperation() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see edu.illinois.codingtracker.operations.IFormat#populateTextChunk(edu.illinois.codingtracker.operations.resources.ExternallyModifiedResourceOperation, edu.illinois.codingtracker.operations.OperationTextChunk)
	 */
	@Override
	public void populateTextChunk(
			ExternallyModifiedResourceOperation externallyModifiedResourceOperation,
			OperationTextChunk textChunk) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see edu.illinois.codingtracker.operations.IFormat#populateBreakableResourceOperation()
	 */
	@Override
	public OperationTextChunk populateBreakableResourceOperation() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see edu.illinois.codingtracker.operations.IFormat#populateTextChunk(edu.illinois.codingtracker.operations.files.SavedFileOperation, edu.illinois.codingtracker.operations.OperationTextChunk)
	 */
	@Override
	public void populateTextChunk(SavedFileOperation savedFileOperation,
			OperationTextChunk textChunk) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see edu.illinois.codingtracker.operations.IFormat#populateUpdatedResourceOperation()
	 */
	@Override
	public OperationTextChunk populateUpdatedResourceOperation() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see edu.illinois.codingtracker.operations.IFormat#populateTextChunk(edu.illinois.codingtracker.operations.resources.CreatedResourceOperation, edu.illinois.codingtracker.operations.OperationTextChunk)
	 */
	@Override
	public void populateTextChunk(
			CreatedResourceOperation createdResourceOperation,
			OperationTextChunk textChunk) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see edu.illinois.codingtracker.operations.IFormat#populateTextChunk(edu.illinois.codingtracker.operations.resources.DeletedResourceOperation, edu.illinois.codingtracker.operations.OperationTextChunk)
	 */
	@Override
	public void populateTextChunk(
			DeletedResourceOperation deletedResourceOperation,
			OperationTextChunk textChunk) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see edu.illinois.codingtracker.operations.IFormat#populateReorganizedResourceOperation()
	 */
	@Override
	public OperationTextChunk populateReorganizedResourceOperation() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see edu.illinois.codingtracker.operations.IFormat#populateTextChunk(edu.illinois.codingtracker.operations.resources.CopiedResourceOperation, edu.illinois.codingtracker.operations.OperationTextChunk)
	 */
	@Override
	public void populateTextChunk(
			CopiedResourceOperation copiedResourceOperation,
			OperationTextChunk textChunk) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see edu.illinois.codingtracker.operations.IFormat#populateTextChunk(edu.illinois.codingtracker.operations.resources.MovedResourceOperation, edu.illinois.codingtracker.operations.OperationTextChunk)
	 */
	@Override
	public void populateTextChunk(
			MovedResourceOperation movedResourceOperation,
			OperationTextChunk textChunk) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see edu.illinois.codingtracker.operations.IFormat#populateFileOperation()
	 */
	@Override
	public OperationTextChunk populateFileOperation() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see edu.illinois.codingtracker.operations.IFormat#populateTextChunk(edu.illinois.codingtracker.operations.files.ClosedFileOperation, edu.illinois.codingtracker.operations.OperationTextChunk)
	 */
	@Override
	public void populateTextChunk(ClosedFileOperation closedFileOperation,
			OperationTextChunk textChunk) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see edu.illinois.codingtracker.operations.IFormat#populateTextChunk(edu.illinois.codingtracker.operations.files.EditedFileOperation, edu.illinois.codingtracker.operations.OperationTextChunk)
	 */
	@Override
	public void populateTextChunk(EditedFileOperation editedFileOperation,
			OperationTextChunk textChunk) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see edu.illinois.codingtracker.operations.IFormat#populateTextChunk(edu.illinois.codingtracker.operations.files.EditedUnsychronizedFileOperation, edu.illinois.codingtracker.operations.OperationTextChunk)
	 */
	@Override
	public void populateTextChunk(
			EditedUnsychronizedFileOperation editedUnsychronizedFileOperation,
			OperationTextChunk textChunk) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see edu.illinois.codingtracker.operations.IFormat#populateTextChunk(edu.illinois.codingtracker.operations.files.RefactoredSavedFileOperation, edu.illinois.codingtracker.operations.OperationTextChunk)
	 */
	@Override
	public void populateTextChunk(
			RefactoredSavedFileOperation refactoredSavedFileOperation,
			OperationTextChunk textChunk) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see edu.illinois.codingtracker.operations.IFormat#populateTextChunk(edu.illinois.codingtracker.operations.files.UpdatedFileOperation, edu.illinois.codingtracker.operations.OperationTextChunk)
	 */
	@Override
	public void populateTextChunk(UpdatedFileOperation updatedFileOperation,
			OperationTextChunk textChunk) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see edu.illinois.codingtracker.operations.IFormat#populateSnapshotedFileOperation()
	 */
	@Override
	public OperationTextChunk populateSnapshotedFileOperation() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see edu.illinois.codingtracker.operations.IFormat#populateTextChunk(edu.illinois.codingtracker.operations.files.snapshoted.NewFileOperation, edu.illinois.codingtracker.operations.OperationTextChunk)
	 */
	@Override
	public void populateTextChunk(NewFileOperation newFileOperation,
			OperationTextChunk textChunk) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see edu.illinois.codingtracker.operations.IFormat#populateTextChunk(edu.illinois.codingtracker.operations.files.snapshoted.RefreshedFileOperation, edu.illinois.codingtracker.operations.OperationTextChunk)
	 */
	@Override
	public void populateTextChunk(
			RefreshedFileOperation refreshedFileOperation,
			OperationTextChunk textChunk) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see edu.illinois.codingtracker.operations.IFormat#populateCommitedFileOperation()
	 */
	@Override
	public OperationTextChunk populateCommitedFileOperation() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see edu.illinois.codingtracker.operations.IFormat#populateTextChunk(edu.illinois.codingtracker.operations.files.snapshoted.CVSCommittedFileOperation, edu.illinois.codingtracker.operations.OperationTextChunk)
	 */
	@Override
	public void populateTextChunk(
			CVSCommittedFileOperation cVSCommittedFileOperation,
			OperationTextChunk textChunk) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see edu.illinois.codingtracker.operations.IFormat#populateTextChunk(edu.illinois.codingtracker.operations.files.snapshoted.CVSInitiallyCommittedFileOperation, edu.illinois.codingtracker.operations.OperationTextChunk)
	 */
	@Override
	public void populateTextChunk(
			CVSInitiallyCommittedFileOperation cVSInitiallyCommittedFileOperation,
			OperationTextChunk textChunk) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see edu.illinois.codingtracker.operations.IFormat#populateTextChunk(edu.illinois.codingtracker.operations.files.snapshoted.SVNCommittedFileOperation, edu.illinois.codingtracker.operations.OperationTextChunk)
	 */
	@Override
	public void populateTextChunk(
			SVNCommittedFileOperation sVNCommittedFileOperation,
			OperationTextChunk textChunk) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see edu.illinois.codingtracker.operations.IFormat#populateTextChunk(edu.illinois.codingtracker.operations.files.snapshoted.SVNInitiallyCommittedFileOperation, edu.illinois.codingtracker.operations.OperationTextChunk)
	 */
	@Override
	public void populateTextChunk(
			SVNInitiallyCommittedFileOperation sVNInitiallyCommittedFileOperation,
			OperationTextChunk textChunk) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see edu.illinois.codingtracker.operations.IFormat#populateTextChunk(edu.illinois.codingtracker.operations.starts.StartedEclipseOperation, edu.illinois.codingtracker.operations.OperationTextChunk)
	 */
	@Override
	public void populateTextChunk(
			StartedEclipseOperation startedEclipseOperation,
			OperationTextChunk textChunk) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see edu.illinois.codingtracker.operations.IFormat#populateTextChunk(edu.illinois.codingtracker.operations.starts.StartedRefactoringOperation, edu.illinois.codingtracker.operations.OperationTextChunk)
	 */
	@Override
	public void populateTextChunk(
			StartedRefactoringOperation startedRefactoringOperation,
			OperationTextChunk textChunk) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see edu.illinois.codingtracker.operations.IFormat#populateTextChangeOperation()
	 */
	@Override
	public OperationTextChunk populateTextChangeOperation() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see edu.illinois.codingtracker.operations.IFormat#populateTextChunk(edu.illinois.codingtracker.operations.textchanges.PerformedTextChangeOperation, edu.illinois.codingtracker.operations.OperationTextChunk)
	 */
	@Override
	public void populateTextChunk(
			PerformedTextChangeOperation performedTextChangeOperation,
			OperationTextChunk textChunk) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see edu.illinois.codingtracker.operations.IFormat#populateTextChunk(edu.illinois.codingtracker.operations.textchanges.RedoneTextChangeOperation, edu.illinois.codingtracker.operations.OperationTextChunk)
	 */
	@Override
	public void populateTextChunk(
			RedoneTextChangeOperation redoneTextChangeOperation,
			OperationTextChunk textChunk) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see edu.illinois.codingtracker.operations.IFormat#populateTextChunk(edu.illinois.codingtracker.operations.textchanges.UndoneTextChangeOperation, edu.illinois.codingtracker.operations.OperationTextChunk)
	 */
	@Override
	public void populateTextChunk(
			UndoneTextChangeOperation undoneTextChangeOperation,
			OperationTextChunk textChunk) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see edu.illinois.codingtracker.operations.IFormat#populateConflictEditorTextChangeOperation()
	 */
	@Override
	public OperationTextChunk populateConflictEditorTextChangeOperation() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see edu.illinois.codingtracker.operations.IFormat#populateTextChunk(edu.illinois.codingtracker.operations.textchanges.PerformedConflictEditorTextChangeOperation, edu.illinois.codingtracker.operations.OperationTextChunk)
	 */
	@Override
	public void populateTextChunk(
			PerformedConflictEditorTextChangeOperation performedConflictEditorTextChangeOperation,
			OperationTextChunk textChunk) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see edu.illinois.codingtracker.operations.IFormat#populateTextChunk(edu.illinois.codingtracker.operations.textchanges.RedoneConflictEditorTextChangeOperation, edu.illinois.codingtracker.operations.OperationTextChunk)
	 */
	@Override
	public void populateTextChunk(
			RedoneConflictEditorTextChangeOperation redoneConflictEditorTextChangeOperation,
			OperationTextChunk textChunk) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see edu.illinois.codingtracker.operations.IFormat#populateTextChunk(edu.illinois.codingtracker.operations.textchanges.UndoneConflictEditorTextChangeOperation, edu.illinois.codingtracker.operations.OperationTextChunk)
	 */
	@Override
	public void populateTextChunk(
			UndoneConflictEditorTextChangeOperation undoneConflictEditorTextChangeOperation,
			OperationTextChunk textChunk) {
		// TODO Auto-generated method stub
		
	}
	
	
}
