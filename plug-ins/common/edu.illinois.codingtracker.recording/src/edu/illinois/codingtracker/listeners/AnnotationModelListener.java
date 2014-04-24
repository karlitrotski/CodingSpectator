
package edu.illinois.codingtracker.listeners;

import java.util.ArrayList;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.texteditor.IDocumentProvider;
import org.eclipse.ui.texteditor.ITextEditor;
import org.eclipse.jface.text.source.*;

import edu.illinois.codingtracker.helpers.AnnotationHelper;

/**
 * 
 * @author Joffre Yagual
 * 
 */
//@SuppressWarnings("restriction")
public class AnnotationModelListener extends BasicListener implements IAnnotationModelListenerExtension, IAnnotationModelListener {

	private static AnnotationModelListener instance = null;

	public static AnnotationModelListener getInstance() {
		if (instance == null) {
			instance = new AnnotationModelListener();
		}

		return instance;
	}

	private AnnotationModelListener() {
		super();
	}

	public static void register()
	{
		
		Display.getDefault().asyncExec(new Runnable() {

			@Override
			public void run() {
				boolean isAnnotationModelListenerRegistered= false;
				IWorkbenchWindow activeWorkbenchWindow= getActiveWorkbenchWindow();
				if (activeWorkbenchWindow == null)
					return;
				while (!isAnnotationModelListenerRegistered) {
					IWorkbenchPage activePage = activeWorkbenchWindow.getActivePage();
					IWorkbenchPart activePart = activePage.getActivePart();
					IAnnotationModel model = getAnnotationModel(activePart);
					if (model == null) {
					    ITextEditor textEditor = null;
					    if (activePart instanceof ITextEditor) {
					      textEditor = (ITextEditor) activePart;
					    }
					    if (textEditor != null) {
					      IDocumentProvider dp = textEditor.getDocumentProvider();
					      if (dp != null){
					    	  model = dp.getAnnotationModel(textEditor.getEditorInput());
					      	}
					      }
					    }
					isAnnotationModelListenerRegistered= true;
					model.addAnnotationModelListener(AnnotationModelListener.getInstance());
				}
				}
		});
	}
	
	public static IAnnotationModel getAnnotationModel(
			IWorkbenchPart activePart) {
		if (activePart == null) {
			return null;
		}
		IAnnotationModel viewer = (IAnnotationModel) activePart
				.getAdapter(IAnnotationModel.class);
		return viewer;
	}

	public void modelChanged(IAnnotationModel model){
		//Do nothing
	}
	
	public void modelChanged(AnnotationModelEvent model){
		Annotation [] added = model.getAddedAnnotations();
		ArrayList<Annotation> errorAdded = AnnotationHelper
				.getAnnotationMatchesbyType(added, "org.eclipse.jdt.ui.error");
		if (errorAdded.isEmpty())
			return;
		//Write code here for processing the event
	}
	
	

}
