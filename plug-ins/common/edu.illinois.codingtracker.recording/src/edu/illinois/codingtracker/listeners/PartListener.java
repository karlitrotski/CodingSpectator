/**
 * This file is licensed under the University of Illinois/NCSA Open Source License. See LICENSE.TXT for details.
 */
package edu.illinois.codingtracker.listeners;

import java.util.ArrayList;
import java.util.Iterator;

import org.eclipse.compare.internal.CompareEditor;
import org.eclipse.core.resources.IFile;
import org.eclipse.jdt.internal.ui.viewsupport.StatusBarUpdater;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IPartListener2;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchPartReference;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.texteditor.AbstractDecoratedTextEditor;

import edu.illinois.codingtracker.compare.helpers.EditorHelper;
import edu.illinois.codingtracker.operations.parts.IPartState;

/**
 * 
 * @author Stas Negara
 * @author Mohsen Vakilian - Extracted this class from CodeChangeTracker and added the method
 *         {@link #getActivePage()}.
 * @author Juraj Kubelka
 * @author Catalina Espinoza Inaipil - we have extended it by listening to all part state changes
 *         and by listening to selection changes inside view parts.
 * 
 */
@SuppressWarnings("restriction")
public class PartListener extends BasicListener implements IPartListener2, ISelectionChangedListener {
	
	/** 
	 * It is used for formating a string which represents selected elements in part views. 
	 * 
	 * @author Juraj Kubelka
	 * @author Catalina Espinoza Inaipil
	 *
	 */
	public class SelectionChangeFormater extends StatusBarUpdater {

		public SelectionChangeFormater() {
			super(null);
		}

		public String [] formatMessages(ISelection sel) {
			if (sel instanceof IStructuredSelection && !sel.isEmpty()) {
				IStructuredSelection selection= (IStructuredSelection) sel;
				String[] messages = new String[selection.size()];
				Iterator <Object> selections = selection.iterator();
				int count = 0;	
				while(selections.hasNext()){
					Object element = selections.next();
					messages[count] = this.formatMessage(new StructuredSelection(element));
					count++;
				}
				return messages;
			}
			return new String[0];
		}
	}
	
	private SelectionChangeFormater formater = new SelectionChangeFormater();

	private static IWorkbenchPage getActivePage() {
		IWorkbenchWindow activeWorkbenchWindow= BasicListener.getActiveWorkbenchWindow();
		if (activeWorkbenchWindow == null) {
			return null;
		}
		IWorkbenchPage activePage= activeWorkbenchWindow.getActivePage();
		return activePage;
	}

	public static void register() {
		Display.getDefault().asyncExec(new Runnable() {

			@Override
			public void run() {
				//TODO: Is it too heavy-weight? Did not notice any additional lag even on a slow machine.  
				boolean isPartListenerRegistered= false;
				while (!isPartListenerRegistered) {
					IWorkbenchPage activePage= getActivePage();
					if (activePage != null) {
						PartListener partListener = new PartListener();
						activePage.addPartListener(partListener);
						isPartListenerRegistered= true;
						for (IWorkbenchPartReference partRef: activePage.getViewReferences()) {
							IWorkbenchPart part = partRef.getPart(true);
							if (part != null) {
								partListener.addSelectionChangedListener(part);
							}
						}
					}
				}
			}
		});
	}

	public void addSelectionChangedListener(IWorkbenchPart part) {
		part.getSite().getSelectionProvider().addSelectionChangedListener(this);
	}
	
	public void removeSelectionChangedListener(IWorkbenchPart part) {
		part.getSite().getSelectionProvider().removeSelectionChangedListener(this);
	}

	private IFile getFileOfWorkbenchPart(IWorkbenchPart part) {
		IFile closedFile= null;
		if (part instanceof CompareEditor) {
			closedFile= EditorHelper.getEditedJavaFile((CompareEditor)part);
		} else if (part instanceof AbstractDecoratedTextEditor) {
			closedFile= EditorHelper.getEditedJavaFile((AbstractDecoratedTextEditor)part);
		}
		return closedFile;
	}

	private void closeConflictEditor(CompareEditor compareEditor) {
		openConflictEditors.remove(compareEditor);
		operationRecorder.recordClosedConflictEditor(EditorHelper.getConflictEditorID(compareEditor));
	}

	private void closeRegularEditor(IWorkbenchPart part, IFile closedFile) {
		//Check that this is the last editor of this file that is closed
		IWorkbenchPage activePage= getActivePage();
		if (activePage != null) {
			IEditorReference[] editorReferences= activePage.getEditorReferences();
			for (IEditorReference editorReference : editorReferences) {
				IEditorPart editor= editorReference.getEditor(false);
				if (editor != part && !EditorHelper.isConflictEditor(editor)) {
					IFile file= null;
					if (editor instanceof CompareEditor) {
						file= EditorHelper.getEditedJavaFile((CompareEditor)editor);
					} else if (editor instanceof AbstractDecoratedTextEditor) {
						file= EditorHelper.getEditedJavaFile((AbstractDecoratedTextEditor)editor);
					}
					if (closedFile.equals(file)) {
						return; // file is not really closed as it is opened in another editor
					}
				}
			}
		}
		operationRecorder.recordClosedFile(closedFile);
	}

	@Override
	public void partActivated(IWorkbenchPartReference partRef) {
		IWorkbenchPart part= partRef.getPart(true);
		if(part!=null) {
			if(part instanceof IEditorPart){
				IFile activatedFile = getFileOfWorkbenchPart(part);
				operationRecorder.recordActivatedFile(activatedFile);
			} else if (part instanceof IViewPart) {
				operationRecorder.recordViewPart(part.getTitle(), IPartState.ACTIVATED);
			}
		}	
	}

	@Override
	public void partBroughtToTop(IWorkbenchPartReference partRef) {
		// do nothing
	}

	public void partClosed(IWorkbenchPart part) {
		IFile closedFile= getFileOfWorkbenchPart(part);
		if (EditorHelper.isConflictEditor(part)) {
			closeConflictEditor((CompareEditor)part);
		} else if (closedFile != null) {
			closeRegularEditor(part, closedFile);
		}
	}

	@Override
	public void partClosed(IWorkbenchPartReference partRef) {
		IWorkbenchPart part= partRef.getPart(true);
		if(part!=null) {
			if(part instanceof IEditorPart){
				partClosed(part);
			} else if (part instanceof IViewPart) {
				operationRecorder.recordViewPart(part.getTitle(), IPartState.CLOSED);
				removeSelectionChangedListener(part);
			}
		}
	}

	@Override
	public void partDeactivated(IWorkbenchPartReference partRef) {
		// do nothing
	}

	@Override
	public void partOpened(IWorkbenchPartReference partRef) {
		IWorkbenchPart part= partRef.getPart(true);
		if(part!=null) {
			if(part instanceof IEditorPart){
				IFile openedFile = getFileOfWorkbenchPart(part);
				operationRecorder.recordOpenedFile(openedFile);
			} else if (part instanceof IViewPart) {
				operationRecorder.recordViewPart(part.getTitle(), IPartState.OPENED);
				addSelectionChangedListener(part);
			}
		}
	}

	@Override
	public void partHidden(IWorkbenchPartReference partRef) {
		IWorkbenchPart part= partRef.getPart(true);
		if(part!=null) {
			if(part instanceof IEditorPart){
				IFile hiddenFile = getFileOfWorkbenchPart(part);
				operationRecorder.recordHiddenFile(hiddenFile);
			} else if (part instanceof IViewPart) {
				operationRecorder.recordViewPart(part.getTitle(), IPartState.HIDDEN);
			}
		}
	}

	@Override
	public void partVisible(IWorkbenchPartReference partRef) {
		IWorkbenchPart part= partRef.getPart(true);
		if(part!=null) {
			if(part instanceof IEditorPart){
				IFile visibleFile = getFileOfWorkbenchPart(part);
				operationRecorder.recordVisibleFile(visibleFile);
			} else if (part instanceof IViewPart) {
				operationRecorder.recordViewPart(part.getTitle(), IPartState.VISIBLE);
			}
		}
	}

	@Override
	public void partInputChanged(IWorkbenchPartReference partRef) {
		// do nothing
	}

	@Override
	public void selectionChanged(SelectionChangedEvent event) {
		String [] messages= formater.formatMessages(event.getSelection());
		Object source= event.getSource();
		String sourceName= source.getClass().toString();
		operationRecorder.recordSelectionChanged(sourceName,messages);
	}
	
}
