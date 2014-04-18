package edu.illinois.codingtracker.listeners;

import org.eclipse.ui.IWindowListener;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.IWorkbenchWindow;

public class ListenerIWorkbench extends BasicListener {
	private static IWindowListener focusListener;
	public static void focusListener() {
		focusListener = new IWindowListener() {
			public void windowOpened(IWorkbenchWindow window) {
				//
				}
			public void windowClosed(IWorkbenchWindow window) {
				//
				}
		
		public void windowDeactivated(IWorkbenchWindow window)
		{
        	operationRecorder.recordLooseFocus();
         }
        public void windowActivated(IWorkbenchWindow window) 
        {
        	operationRecorder.recordGainsFocus();
        }
    };
    PlatformUI.getWorkbench().addWindowListener(focusListener);
	}
}
	