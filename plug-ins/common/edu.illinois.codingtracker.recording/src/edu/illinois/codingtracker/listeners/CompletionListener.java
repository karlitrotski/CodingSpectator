/**
 * This file is licensed under the University of Illinois/NCSA Open Source License. See LICENSE.TXT for details.
 */
package edu.illinois.codingtracker.listeners;

import org.eclipse.jface.text.ITextOperationTarget;
import org.eclipse.jface.text.contentassist.ContentAssistEvent;
import org.eclipse.jface.text.contentassist.ICompletionListener;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.ISourceViewerExtension3;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;


/**
 * 
 * @author Joffre Yagual
 * 
 */
//@SuppressWarnings("restriction")
public class CompletionListener extends BasicListener implements ICompletionListener {

	private static CompletionListener instance = null;

	public static CompletionListener getInstance() {
		if (instance == null) {
			instance = new CompletionListener();
		}

		return instance;
	}

	private CompletionListener() {
		super();
	}

	public static void register()
	{
		
		Display.getDefault().asyncExec(new Runnable() {

			@Override
			public void run() {
				boolean isCompletionListenerRegistered= false;
				while (!isCompletionListenerRegistered) {
					IWorkbenchWindow activeWindow= getActiveWorkbenchWindow();
					
					if (activeWindow != null) {
						IWorkbenchPage activePage= activeWindow.getActivePage();
						IEditorPart editor = activePage.getActiveEditor();
						ISourceViewerExtension3 sourceViewerExtension3 = 
								CompletionListener.getSourceViewerExtension3(editor);
						if (sourceViewerExtension3 != null) {
							if (sourceViewerExtension3.getQuickAssistAssistant() != null) {
								sourceViewerExtension3.getQuickAssistAssistant()
										.addCompletionListener(CompletionListener.getInstance());
							}
						isCompletionListenerRegistered= true;
					}
				}
			}
			}
		});

	}
	
	public static ISourceViewerExtension3 getSourceViewerExtension3(
			IEditorPart editor) {
		if (editor == null) {
			return null;
		}

		ISourceViewer viewer = (ISourceViewer) editor
				.getAdapter(ITextOperationTarget.class);
		if (viewer instanceof ISourceViewerExtension3) {
			return (ISourceViewerExtension3) viewer;
		}

		return null;
	}
		
	public void assistSessionStarted(ContentAssistEvent event) {	
		operationRecorder.recordNewCompletionQuickfix(event);
	}

	public void assistSessionEnded(ContentAssistEvent event) {
		//operationRecorder.recordNewQuickfixUsage(event);
	}

	public void selectionChanged(ICompletionProposal proposal,
			boolean smartToggle) {

	}

}
