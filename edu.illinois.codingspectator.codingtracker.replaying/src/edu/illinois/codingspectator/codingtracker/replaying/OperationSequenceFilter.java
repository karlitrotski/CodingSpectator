/**
 * This file is licensed under the University of Illinois/NCSA Open Source License. See LICENSE.TXT for details.
 */
package edu.illinois.codingspectator.codingtracker.replaying;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

import edu.illinois.codingspectator.codingtracker.helpers.ViewerHelper;
import edu.illinois.codingspectator.codingtracker.operations.files.RefactoredSavedFileOperation;
import edu.illinois.codingspectator.codingtracker.operations.files.SnapshotedFileOperation;
import edu.illinois.codingspectator.codingtracker.operations.refactorings.RefactoringOperation;
import edu.illinois.codingspectator.codingtracker.operations.starts.StartedRefactoringOperation;
import edu.illinois.codingspectator.codingtracker.operations.textchanges.TextChangeOperation;

/**
 * 
 * @author Stas Negara
 * 
 */
public class OperationSequenceFilter extends ViewerFilter {

	private enum FilteredOperations {
		TEXT_CHANGES, REFACTORINGS, SNAPSHOTS, OTHERS
	}

	private final OperationSequenceView operationSequenceView;

	private boolean showTextChanges= true;

	private boolean showRefactorings= true;

	private boolean showSnapshots= true;

	private boolean showOthers= true;

	public OperationSequenceFilter(OperationSequenceView operationSequenceView) {
		this.operationSequenceView= operationSequenceView;
	}

	@Override
	public boolean select(Viewer viewer, Object parentElement, Object element) {
		return isShown(element);
	}

	boolean isShown(Object element) {
		if (element instanceof TextChangeOperation) {
			return showTextChanges;
		}
		if (element instanceof RefactoringOperation || element instanceof RefactoredSavedFileOperation
				|| element instanceof StartedRefactoringOperation) {
			return showRefactorings;
		}
		if (element instanceof SnapshotedFileOperation) {
			return showSnapshots;
		}
		return showOthers;
	}

	void addToolBarActions() {
		IToolBarManager toolBarManager= operationSequenceView.getToolBarManager();
		toolBarManager.add(createFilterAction("Text Changes", "Display text change operations", FilteredOperations.TEXT_CHANGES));
		toolBarManager.add(createFilterAction("Refactorings", "Display refactoring operations", FilteredOperations.REFACTORINGS));
		toolBarManager.add(createFilterAction("Snapshots", "Display snapshot-producing operations", FilteredOperations.SNAPSHOTS));
		toolBarManager.add(createFilterAction("Others", "Display all other operations", FilteredOperations.OTHERS));
	}

	private IAction createFilterAction(String actionText, String actionToolTipText, final FilteredOperations filteredOperations) {
		IAction action= new Action() {
			@Override
			public void run() {
				toggleFilteredOperations(filteredOperations);
				operationSequenceView.refreshTableViewer();
			}
		};
		ViewerHelper.initAction(action, actionText, actionToolTipText, true, true, true);
		return action;
	}

	private void toggleFilteredOperations(FilteredOperations filteredOperations) {
		switch (filteredOperations) {
			case TEXT_CHANGES:
				showTextChanges= !showTextChanges;
				break;
			case REFACTORINGS:
				showRefactorings= !showRefactorings;
				break;
			case SNAPSHOTS:
				showSnapshots= !showSnapshots;
				break;
			case OTHERS:
				showOthers= !showOthers;
				break;
			default:
				throw new RuntimeException("Unsupported filtered operations: " + filteredOperations);
		}
	}

}