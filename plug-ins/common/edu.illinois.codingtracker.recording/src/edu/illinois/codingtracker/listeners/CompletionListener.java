/**
 * This file is licensed under the University of Illinois/NCSA Open Source License. See LICENSE.TXT for details.
 */
package edu.illinois.codingtracker.listeners;

import org.eclipse.jdt.internal.ui.text.correction.IStatusLineProposal;
import org.eclipse.jdt.internal.ui.text.correction.JavaCorrectionAssistant;
import org.eclipse.jdt.internal.ui.text.correction.JavaCorrectionProcessor;
import org.eclipse.jdt.ui.text.java.IQuickAssistProcessor;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITextHover;
import org.eclipse.jface.text.ITextHoverExtension2;
import org.eclipse.jface.text.DefaultTextHover;
import org.eclipse.jface.text.ITextOperationTarget;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.ITextViewerExtension2;
import org.eclipse.jface.text.ITextHoverExtension2;
import org.eclipse.jface.text.contentassist.ContentAssistEvent;
import org.eclipse.jface.text.contentassist.ContentAssistant;
import org.eclipse.jface.text.contentassist.ICompletionListener;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.text.contentassist.ICompletionProposalExtension3;
import org.eclipse.jface.text.contentassist.IContentAssistProcessor;
import org.eclipse.jface.text.contentassist.IContentAssistant;
import org.eclipse.jface.text.contentassist.IContentAssistantExtension2;
import org.eclipse.jface.text.quickassist.QuickAssistAssistant;
import org.eclipse.jface.text.source.ContentAssistantFacade;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.ISourceViewerExtension2;
import org.eclipse.jface.text.source.ISourceViewerExtension3;
import org.eclipse.jface.text.source.ISourceViewerExtension4;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.texteditor.AbstractTextEditor;
import org.eclipse.ui.texteditor.ITextEditor;
import org.eclipse.jface.text.contentassist.IContentAssistantExtension;



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
		
	@SuppressWarnings("deprecation")
	public void assistSessionStarted(ContentAssistEvent event) {
		//Write code here for processing the event

		operationRecorder.recordNewCompletionQuickfix(event);

	}
	
	public void assistSessionEnded(ContentAssistEvent event) {
		//operationRecorder.recordNewQuickfixUsage(event);
	}

	public void selectionChanged(ICompletionProposal proposal,
			boolean smartToggle) {
	}

}
