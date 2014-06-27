/**
 * This file is licensed under the University of Illinois/NCSA Open Source License. See LICENSE.TXT for details.
 */
package edu.illinois.codingtracker.operations;

import java.util.LinkedList;
import java.util.List;

import cl.uchile.codingtracker.operations.annotations.AnnotationErrorOperation;
import cl.uchile.codingtracker.operations.completions.CompletionQuickfixOperation;
import cl.uchile.codingtracker.operations.completions.QuickfixUsageOperation;

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
import edu.illinois.codingtracker.operations.focus.DetectFocusGainsWorkbench;
import edu.illinois.codingtracker.operations.focus.DetectFocusLooseWorkbench;
import edu.illinois.codingtracker.operations.junit.TestCaseFinishedOperation;
import edu.illinois.codingtracker.operations.junit.TestCaseStartedOperation;
import edu.illinois.codingtracker.operations.junit.TestSessionFinishedOperation;
import edu.illinois.codingtracker.operations.junit.TestSessionLaunchedOperation;
import edu.illinois.codingtracker.operations.junit.TestSessionStartedOperation;
import edu.illinois.codingtracker.operations.options.ProjectOptionsChangedOperation;
import edu.illinois.codingtracker.operations.options.WorkspaceOptionsChangedOperation;
import edu.illinois.codingtracker.operations.parts.EditPartOperation;
import edu.illinois.codingtracker.operations.parts.ViewPartOperation;
import edu.illinois.codingtracker.operations.pluginusage.PluginMonitorOperation;
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
import edu.illinois.codingtracker.operations.shortcuts.ShortCutCommandName;
import edu.illinois.codingtracker.operations.starts.LaunchedApplicationOperation;
import edu.illinois.codingtracker.operations.starts.PluginsList;
import edu.illinois.codingtracker.operations.starts.StartedEclipseOperation;
import edu.illinois.codingtracker.operations.starts.StartedRefactoringOperation;
import edu.illinois.codingtracker.operations.starts.TerminatedApplicationOperation;
import edu.illinois.codingtracker.operations.textchanges.PerformedConflictEditorTextChangeOperation;
import edu.illinois.codingtracker.operations.textchanges.PerformedTextChangeOperation;
import edu.illinois.codingtracker.operations.textchanges.RedoneConflictEditorTextChangeOperation;
import edu.illinois.codingtracker.operations.textchanges.RedoneTextChangeOperation;
import edu.illinois.codingtracker.operations.textchanges.UndoneConflictEditorTextChangeOperation;
import edu.illinois.codingtracker.operations.textchanges.UndoneTextChangeOperation;
import edu.illinois.codingtracker.operations.selectionchanged.SelectionChangedOperation;
//TODO: Decide on where this class should be and how it should be used
/**
 * 
 * @author Stas Negara
 * 
 */
public class OperationDeserializer {

	public static List<UserOperation> getUserOperations(String operationsRecord) {
		List<UserOperation> userOperations= new LinkedList<UserOperation>();
		OperationLexer operationLexer= new OperationLexer(operationsRecord);
		while (operationLexer.hasNextOperation()) {
			operationLexer.startNewOperation();
			UserOperation userOperation= createEmptyUserOperation(operationLexer.getCurrentOperationSymbol());
			userOperation.deserialize(operationLexer);
			userOperations.add(userOperation);
		}
		return userOperations;
	}

	public static UserOperation createEmptyUserOperation(char operationSymbol) {
		UserOperation userOperation;
		//Falta instancias el metodo gain y loose
		switch (operationSymbol) {
			case OperationSymbols.PLUGINS_USAGE:
				userOperation= new PluginMonitorOperation();
			break;
			case OperationSymbols.PLUGINS_SYMBOL:
				userOperation= new PluginsList();
			break;
			case OperationSymbols.APPLICATION_FOCUS_GAINS:
				userOperation= new DetectFocusGainsWorkbench();
			break;
			case OperationSymbols.APPLICATION_FOCUS_LOOSE:
				userOperation= new DetectFocusLooseWorkbench();
			break;
			
			case OperationSymbols.ECLIPSE_STARTED_SYMBOL:
				userOperation= new StartedEclipseOperation();
				break;
			case OperationSymbols.REFACTORING_FINISHED_SYMBOL:
				userOperation= new FinishedRefactoringOperation();
				break;
			case OperationSymbols.NEW_REFACTORING_STARTED_SYMBOL:
				userOperation= new NewStartedRefactoringOperation();
				break;
			case OperationSymbols.REFACTORING_STARTED_SYMBOL:
				userOperation= new StartedRefactoringOperation();
				break;
			case OperationSymbols.REFACTORING_PERFORMED_SYMBOL:
				userOperation= new PerformedRefactoringOperation();
				break;
			case OperationSymbols.REFACTORING_UNDONE_SYMBOL:
				userOperation= new UndoneRefactoringOperation();
				break;
			case OperationSymbols.REFACTORING_REDONE_SYMBOL:
				userOperation= new RedoneRefactoringOperation();
				break;
			case OperationSymbols.CONFLICT_EDITOR_OPENED_SYMBOL:
				userOperation= new OpenedConflictEditorOperation();
				break;
			case OperationSymbols.CONFLICT_EDITOR_CLOSED_SYMBOL:
				userOperation= new ClosedConflictEditorOperation();
				break;
			case OperationSymbols.CONFLICT_EDITOR_SAVED_SYMBOL:
				userOperation= new SavedConflictEditorOperation();
				break;
			case OperationSymbols.RESOURCE_CREATED_SYMBOL:
				userOperation= new CreatedResourceOperation();
				break;
			case OperationSymbols.RESOURCE_MOVED_SYMBOL:
				userOperation= new MovedResourceOperation();
				break;
			case OperationSymbols.RESOURCE_COPIED_SYMBOL:
				userOperation= new CopiedResourceOperation();
				break;
			case OperationSymbols.RESOURCE_DELETED_SYMBOL:
				userOperation= new DeletedResourceOperation();
				break;
			case OperationSymbols.FILE_CLOSED_SYMBOL:
				userOperation= new ClosedFileOperation();
				break;
			case OperationSymbols.FILE_SAVED_SYMBOL:
				userOperation= new SavedFileOperation();
				break;
			case OperationSymbols.RESOURCE_EXTERNALLY_MODIFIED_SYMBOL:
				userOperation= new ExternallyModifiedResourceOperation();
				break;
			case OperationSymbols.FILE_UPDATED_SYMBOL:
				userOperation= new UpdatedFileOperation();
				break;
			case OperationSymbols.FILE_SVN_INITIALLY_COMMITTED_SYMBOL:
				userOperation= new SVNInitiallyCommittedFileOperation();
				break;
			case OperationSymbols.FILE_CVS_INITIALLY_COMMITTED_SYMBOL:
				userOperation= new CVSInitiallyCommittedFileOperation();
				break;
			case OperationSymbols.FILE_SVN_COMMITTED_SYMBOL:
				userOperation= new SVNCommittedFileOperation();
				break;
			case OperationSymbols.FILE_CVS_COMMITTED_SYMBOL:
				userOperation= new CVSCommittedFileOperation();
				break;
			case OperationSymbols.FILE_REFACTORED_SAVED_SYMBOL:
				userOperation= new RefactoredSavedFileOperation();
				break;
			case OperationSymbols.FILE_NEW_SYMBOL:
				userOperation= new NewFileOperation();
				break;
			case OperationSymbols.FILE_REFRESHED_SYMBOL:
				userOperation= new RefreshedFileOperation();
				break;
			case OperationSymbols.FILE_EDITED_SYMBOL:
				userOperation= new EditedFileOperation();
				break;
			case OperationSymbols.FILE_EDITED_UNSYNCHRONIZED_SYMBOL:
				userOperation= new EditedUnsychronizedFileOperation();
				break;
			case OperationSymbols.TEXT_CHANGE_PERFORMED_SYMBOL:
				userOperation= new PerformedTextChangeOperation();
				break;
			case OperationSymbols.TEXT_CHANGE_UNDONE_SYMBOL:
				userOperation= new UndoneTextChangeOperation();
				break;
			case OperationSymbols.TEXT_CHANGE_REDONE_SYMBOL:
				userOperation= new RedoneTextChangeOperation();
				break;
			case OperationSymbols.CONFLICT_EDITOR_TEXT_CHANGE_PERFORMED_SYMBOL:
				userOperation= new PerformedConflictEditorTextChangeOperation();
				break;
			case OperationSymbols.CONFLICT_EDITOR_TEXT_CHANGE_UNDONE_SYMBOL:
				userOperation= new UndoneConflictEditorTextChangeOperation();
				break;
			case OperationSymbols.CONFLICT_EDITOR_TEXT_CHANGE_REDONE_SYMBOL:
				userOperation= new RedoneConflictEditorTextChangeOperation();
				break;
			case OperationSymbols.TEST_SESSION_LAUNCHED_SYMBOL:
				userOperation= new TestSessionLaunchedOperation();
				break;
			case OperationSymbols.TEST_SESSION_STARTED_SYMBOL:
				userOperation= new TestSessionStartedOperation();
				break;
			case OperationSymbols.TEST_SESSION_FINISHED_SYMBOL:
				userOperation= new TestSessionFinishedOperation();
				break;
			case OperationSymbols.TEST_CASE_STARTED_SYMBOL:
				userOperation= new TestCaseStartedOperation();
				break;
			case OperationSymbols.TEST_CASE_FINISHED_SYMBOL:
				userOperation= new TestCaseFinishedOperation();
				break;
			case OperationSymbols.APPLICATION_LAUNCHED_SYMBOL:
				userOperation= new LaunchedApplicationOperation();
				break;
			case OperationSymbols.WORKSPACE_OPTIONS_CHANGED_SYMBOL:
				userOperation= new WorkspaceOptionsChangedOperation();
				break;
			case OperationSymbols.PROJECT_OPTIONS_CHANGED_SYMBOL:
				userOperation= new ProjectOptionsChangedOperation();
				break;
			case OperationSymbols.REFERENCING_PROJECTS_CHANGED_SYMBOL:
				userOperation= new ReferencingProjectsChangedOperation();
				break;

				
			case OperationSymbols.APPLICATION_SHORTCUTS:
				userOperation= new ShortCutCommandName(null, null);
				break;
			case OperationSymbols.APPLICATION_TERMINATED_SYMBOL:
				userOperation= new TerminatedApplicationOperation(null, null, null, null, false, null);
				break;
			case OperationSymbols.QUICKFIX_INVOCATION_SYMBOL:
				userOperation= new CompletionQuickfixOperation(null);
				break;
			case OperationSymbols.QUICKFIX_USAGE_SYMBOL:
				userOperation= new QuickfixUsageOperation();
				break;
			case OperationSymbols.CODE_ERROR_SYMBOL:
				userOperation= new AnnotationErrorOperation(0);
				break;
			case OperationSymbols.EDIT_PART_OPERATION_SYMBOL:
				userOperation= new EditPartOperation();
				break;
			case OperationSymbols.VIEW_PART_OPERATION_SYMBOL:
				userOperation= new ViewPartOperation(null, null);
				break;
			case OperationSymbols.SELECTION_CHANGED_OPERATION_SYMBOL:
				userOperation= new SelectionChangedOperation(null, null);
				break;
			
			default:
				throw new RuntimeException("Unsupported operation symbol: " + operationSymbol);
		}
		return userOperation;
	}

}
