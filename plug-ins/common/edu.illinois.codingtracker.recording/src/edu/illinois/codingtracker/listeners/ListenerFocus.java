/**
 * This file is licensed under the University of Illinois/NCSA Open Source License. See LICENSE.TXT for details.
 */
package edu.illinois.codingtracker.listeners;
/**
 * @author Teofilo_Chambilla_Aquino
 * Listener for Detect when the development environment 
 * looses or gains focus (user uses another application) 
 */
import org.eclipse.ui.IWindowListener;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.IWorkbenchWindow;

public class ListenerFocus extends BasicListener {
	private static IWindowListener focusListener;
	public static void register() {
		focusListener = new IWindowListener() {
			public void windowOpened(IWorkbenchWindow window) 
			{	
				
			}
			public void windowClosed(IWorkbenchWindow window) 
			{	
				
			}
		
			public void windowDeactivated(IWorkbenchWindow window)
			{
				operationRecorder.recordLooseFocus(window);
			}
			public void windowActivated(IWorkbenchWindow window) 
			{
				operationRecorder.recordGainsFocus(window);
			}
    };
    PlatformUI.getWorkbench().addWindowListener(focusListener);
	}
}
	