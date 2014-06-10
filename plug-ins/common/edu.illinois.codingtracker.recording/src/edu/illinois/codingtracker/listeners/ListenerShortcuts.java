package edu.illinois.codingtracker.listeners;
import java.util.Collection;

import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IExecutionListener;
import org.eclipse.core.commands.NotHandledException;
import org.eclipse.core.commands.common.NotDefinedException;
import org.eclipse.core.expressions.IEvaluationContext;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.bindings.keys.KeySequence;
import org.eclipse.jface.bindings.keys.KeyStroke;
import org.eclipse.jface.bindings.keys.SWTKeySupport;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.commands.ICommandService;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.internal.WWinPluginAction;


/**
 * class ListenerISortctus for record Shortctus, and command name a Execution
 * Related to issue karlitrotski/CodingSpectator/#14
 * @author Teofilo_Chambilla_Aquino
 */

public class ListenerShortcuts extends BasicListener implements IExecutionListener {
 
	
	public void notHandled(String commandId, NotHandledException exception) {}
	public void postExecuteFailure(String commandId, ExecutionException exception) {}
	public void postExecuteSuccess(String commandId, Object returnValue) {}
		 
	public void preExecute(String commandId, ExecutionEvent event ) {
	    Command command = event.getCommand();
		Object Trigger= event.getTrigger(); 
	 	if(Trigger instanceof Event) 
		{	
			try 
		     { 
				Event TriggerEvent = (Event)Trigger;
				
				if((TriggerEvent.keyCode>0))
				{
				   int accelerator = SWTKeySupport.convertEventToUnmodifiedAccelerator(TriggerEvent);
	                KeyStroke keyStroke = SWTKeySupport.convertAcceleratorToKeyStroke(accelerator);
	                KeySequence sequence = KeySequence.getInstance(keyStroke);
	                if(!sequence.isEmpty()) 
	                {  
	                 operationRecorder.recordShortcutsCommandName(command.getName(),sequence.toString());
	                }
	            }
				}
           
				catch (NotDefinedException e) {
				e.printStackTrace();
			}
						
		}
		 
	}
	public static void register() 
	{ 
	  Display.getDefault().asyncExec(new Runnable() 
		{
		    @Override
		    public void run() 
		    {
		    	boolean isWindowListenerRegistered= false;
		    	while (!isWindowListenerRegistered) 
		    	{
		          IWorkbenchWindow activeWindow= getActiveWorkbenchWindow();
		           if (activeWindow != null) 
		           {
		        	   ICommandService CommandService = (ICommandService) PlatformUI.getWorkbench()
		        			   		.getAdapter(ICommandService.class);
		        	   if (CommandService != null) 
		        	   	{
		        	   	CommandService.addExecutionListener(new ListenerShortcuts());
		        	    
		       
		        	   	isWindowListenerRegistered= true;
		        	   	}
					}
		 		}
		    }
		});
	}
}



