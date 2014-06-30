/**
 * This file is licensed under the University of Illinois/NCSA Open Source License. See LICENSE.TXT for details.
 */
package edu.illinois.codingtracker.listeners;

import javax.sound.sampled.Control;

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
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.texteditor.AbstractTextEditor;
import org.eclipse.ui.texteditor.ITextEditor;
import org.eclipse.jface.text.contentassist.IContentAssistantExtension;
import org.eclipse.swt.events.*;


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

		IWorkbenchWindow activeWindow= getActiveWorkbenchWindow();
		IWorkbenchPage activePage= activeWindow.getActivePage();
		IEditorPart editor = activePage.getActiveEditor();
		ISourceViewerExtension3 viewer = CompletionListener.getSourceViewerExtension3(editor);
		ISourceViewerExtension4 viewer0 = (ISourceViewerExtension4) viewer;
		ITextViewer tviewer = (ITextViewer)viewer;
		ITextViewerExtension2 tviewerext = (ITextViewerExtension2)viewer;
		ITextHover defaultHover = new DefaultTextHover((ISourceViewer)viewer);
		IRegion hr = defaultHover.getHoverRegion(tviewer, viewer.getQuickAssistInvocationContext().getOffset());
		//ITextHoverExtension2 dhext = (ITextHoverExtension2)defaultHover;
		String s = defaultHover.getHoverInfo(tviewer, hr);
		if (tviewerext.getCurrentTextHover() != null) {
			int j = 0;
			j++;		
			
		}
		//ITextHover ito = tviewerext.getCurrentTextHover();
		String s1 = tviewer.getTextWidget().getSelectionText();
		ICompletionProposal [] icp = viewer.getQuickAssistAssistant().getQuickAssistProcessor().computeQuickAssistProposals(viewer.getQuickAssistInvocationContext());
		int offset = viewer.getQuickAssistInvocationContext().getOffset();
		//tviewer.getTextWidget().getToolTipText();
		if (viewer.getCurrentAnnotationHover() != null) {
			//String texto = viewer.getCurrentAnnotationHover().getHoverInfo((ISourceViewer)viewer, viewer.getQuickAssistInvocationContext().getOffset());
		}
	}
	
	public void assistSessionEnded(ContentAssistEvent event) {
		//operationRecorder.recordNewQuickfixUsage(event);
	}

	public void selectionChanged(ICompletionProposal proposal,
			boolean smartToggle) {
		IWorkbenchWindow activeWindow= getActiveWorkbenchWindow();
		IWorkbenchPage activePage= activeWindow.getActivePage();
		IEditorPart editor = activePage.getActiveEditor();
		ISourceViewerExtension3 viewer = CompletionListener.getSourceViewerExtension3(editor);
		ITextViewer tviewer = (ITextViewer)viewer;
		Shell sh = tviewer.getTextWidget().getShell();
		Shell [] shs = sh.getShells();
		Table tb = null;
		for (int i= 0; i< shs.length; i++) {
			org.eclipse.swt.widgets.Control [] shChildren = shs[i].getChildren();
			for (int j= 0; j< shChildren.length; j++) {
				if (shChildren[j] instanceof Table) {
					tb = (Table)shChildren[j];
					break;
				}
			}
		}
		if (tb != null) {
			org.eclipse.swt.events.SelectionListener listener2= new org.eclipse.swt.events.SelectionListener() {
				private String itemText;
				public void widgetSelected(SelectionEvent e) {
					if(e.item instanceof TableItem) {
						TableItem ti = (TableItem)e.item;
						itemText = ti.getText();
					}
				}
				public void widgetDefaultSelected(SelectionEvent e) {
					String result = itemText;
					System.out.println(result);
					operationRecorder.recordNewQuickfixUsage(result);
				}
			};
			
			tb.addSelectionListener(listener2);
		}
	}


}
