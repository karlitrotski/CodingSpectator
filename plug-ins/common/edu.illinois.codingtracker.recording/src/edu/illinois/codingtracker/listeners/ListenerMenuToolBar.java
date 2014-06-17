package edu.illinois.codingtracker.listeners;
/**
 * @author Teofilo_Chambilla_Aquino
 * Listener for Detect Usage of menu, context menu, and icons 
 * Related to issue karlitrotski/CodingSpectator/#33
 */
 
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.CoolBar;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Item;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IExecutionListener;
import org.eclipse.core.commands.NotHandledException;
import org.eclipse.core.commands.common.NotDefinedException;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.CoolBarManager;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.ui.menus.IMenuService;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;
import org.eclipse.jface.action.IToolBarManager;
 
import org.eclipse.jface.action.ToolBarManager; 
import org.eclipse.jface.window.ApplicationWindow;
 

import org.eclipse.ui.IEditorPart; 
import org.eclipse.ui.IPluginContribution; 
import org.eclipse.ui.IViewPart; 
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart; 
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI; 
import org.eclipse.ui.commands.ICommandService;
 

import edu.illinois.codingtracker.operations.OperationSymbols;
public class ListenerMenuToolBar extends BasicListener implements IExecutionListener {
	public static void register() {
		Display.getDefault().asyncExec(new Runnable() {
			@Override
			public void run() {
				 boolean isListenerRegistereds= false;
				 IWorkbenchWindow activeWorkbenchWindow= BasicListener.getActiveWorkbenchWindow();
				while (!isListenerRegistereds) { 
					 IWorkbenchPage activePage= activeWorkbenchWindow.getActivePage();
					if (activePage != null) {
						IMenuService service = (IMenuService) PlatformUI.getWorkbench().getAdapter(IMenuService.class);
					
						  ICommandService CommandService = (ICommandService) PlatformUI.getWorkbench()
			        			   		.getAdapter(ICommandService.class);
			        	   if (CommandService != null) 
			        	   	{
			        	   	CommandService.addExecutionListener(new ListenerMenuToolBar());
			        	   	isListenerRegistereds= true;
			        	   	}
						}
				}
			}
		});
	}
	@Override
	public void notHandled(String commandId, NotHandledException exception) {}
	@Override
	public void postExecuteFailure(String commandId, ExecutionException exception) {}
	@Override
	public void postExecuteSuccess(String commandId, Object returnValue) { }
 	private static boolean getInMenubar(MenuItem menuItem)
	{
		Menu parent = menuItem.getParent();
		MenuItem parentItem = parent.getParentItem();

		if (parentItem != null)
		{
			return getInMenubar(parentItem);
		}

		Shell theShell = parent.getShell();
		return parent == theShell.getMenuBar();
	}
	private static boolean isOnWorkbenchToolbar(ToolItem toolItem)
	{
		ToolBar toolBar = toolItem.getParent();
		Shell shell = toolBar.getShell();
		Object data = shell.getData();
		if (data instanceof ApplicationWindow)
		{
			ApplicationWindow window = (ApplicationWindow) data;
			ToolBarManager mng = window.getToolBarManager();
			if (mng != null)
			{
				if (mng.getControl() != null && mng.getControl() == toolBar)
					return true;
			}
			CoolBarManager cmng = window.getCoolBarManager();
			if (cmng != null)
			{
				CoolBar cbar = cmng.getControl();
				Composite parent = toolBar.getParent();
				while (parent != null)
				{
					if (parent == cbar)
						return true;
					parent = parent.getParent();
				}
			}
		}

		

		return false;
	}
	public static String removeChar(String input, char toRemove)
	{
		StringBuffer buf = new StringBuffer(input.length());

		int last = 0;
		for (int pos = input.indexOf(toRemove); pos != -1; pos = input.indexOf(
				toRemove, last))
		{
			buf.append(input.substring(last, pos));
			last = pos + 1;
		}

		buf.append(input.substring(last, input.length()));

		return buf.toString();
	}
	private static String getActionId(IContributionItem contrib)
	{
		String id = null;
		String IdWizard=null;
		if (contrib instanceof IPluginContribution)
		{
			id = ((IPluginContribution) contrib).getLocalId();
		}
		id = id == null ? contrib.getId() : id;
		if (id != null)
		{
			IdWizard=("contribid/" + id); 
		}
		else
		{
			if (contrib instanceof ActionContributionItem)
			{
				ActionContributionItem actionItem = (ActionContributionItem) contrib;
				id = actionItem.getAction().getText();
				if (id != null)
				{
					IdWizard=("actionid/" + id); 
				}
				else
				{
					IAction action = actionItem.getAction();
					id = action.getActionDefinitionId();
					if (id != null)
					{
						IdWizard=("defid/" + id); 
					}
					else
					{
						IdWizard=("actionclass/" + action.getClass().getName()); 
					}
				}
			}
			else
			{
				IdWizard=("contribclass/" + contrib.getClass().getName()); 
			}
		}

		return IdWizard;
	}
	public static String MenuItemIndex(MenuItem menuItem, String menuInx)
	{if (menuItem == null)
			return menuInx;

		if (menuInx.length() > 0)
			menuInx = "|" + menuInx;
		menuInx = menuItem.getParent().indexOf(menuItem) + menuInx;
		return MenuItemIndex(menuItem.getParent().getParentItem(), menuInx);
	}
	public static String ToolItemIndex(ToolItem ToolItem, String ToolInx)
	{if (ToolItem == null)
			return ToolInx;

		if (ToolInx.length() > 0)
			ToolInx = "|" + ToolInx;
		ToolInx = ToolItem.getParent().indexOf(ToolItem) + ToolInx;
		return ToolInx;
		//return ToolItemIndex(ToolItem.getParent().g.getParentItem(), ToolInx);
	}
	public static String getActionId(Widget widget)
	{   
		String ItemIndex =null;	
	    Object data = widget.getData();
		if (data != null && (data instanceof IContributionItem))
		{
			String Id = getActionId((IContributionItem) data);
			if (!Id.toString().equals(OperationSymbols.EMPTY_STRING))
			{
				if (widget instanceof MenuItem)
				{
					ItemIndex = MenuItemIndex((MenuItem) widget, "");
				}
				if (widget instanceof ToolItem)
				{
					ItemIndex = ToolItemIndex((ToolItem) widget, "");
				}
			}
		}
		else
		{
			ItemIndex=("readablename/" + ((widget instanceof MenuItem) ? getDisplayName((MenuItem) widget) : getDisplayName((ToolItem) widget))); 
		}

		return ItemIndex;
	}
 	public static String getItemMenuToolId(Item item)
	{	if (item instanceof ToolItem)
		{
			return getActionId((ToolItem) item);
		}
		else if (item instanceof MenuItem)
		{
			return getActionId((MenuItem) item);
		}
		else if (item instanceof IContributionItem)
		{
			return getActionId((IContributionItem) item);
		}
		return null;
	}
	private static String getDisplayName(Menu menu)
	{
		MenuItem parentItem = menu.getParentItem();
		if (parentItem == null)
		{
			return OperationSymbols.EMPTY_STRING;
		}
		else
			{
			return getDisplayName(parentItem);
			}
		}
	private static String getDisplayName(ToolItem toolItem)
	{
		String name = toolItem.getText();

		if (name != null && !name.equals(OperationSymbols.EMPTY_STRING))
		{
			return name;
		}

		name = toolItem.getToolTipText();

		if (name != null)
		{
			return name;
		}

		return "unknown"; 
	}
	private static String getDisplayName(MenuItem menuItem)
	{
		if (menuItem.getParent() == null || menuItem.getParent().getParentItem() == null)
		{
			return removeChar(menuItem.getText(), '&');
		}
		else
			{
			return getDisplayName(menuItem.getParent()) + "/" + removeChar(menuItem.getText(), '&');
			}
		}
	public static void getNameMenuToolBar(Widget widget,Command command) 
	{	Control control = widget.getDisplay().getFocusControl(); 
		String ItemIndex = null;
		String NameSite=getNameSite(control);
		if (widget instanceof MenuItem)
		{
			MenuItem menuItem = (MenuItem) widget;
			ItemIndex = getItemMenuToolId(menuItem);
			IViewPart View = null;
			IEditorPart editor;
			if (getInMenubar(menuItem))
			{
				try {
					operationRecorder.recordUsingMenuToolIcons(
							OperationSymbols.SHELL_VALUE,
							OperationSymbols.MENUBAR_VALUE
							,ItemIndex,getDisplayName(menuItem),
							command.getId(),
							command.getName());
				} catch (NotDefinedException e) {
					e.printStackTrace();
				} 
		 			
			} 
			else if ((View = getViewParts(control)) != null)
			{   
				IToolBarManager toolbarManager = View.getViewSite().getActionBars().getToolBarManager();
				if (toolbarManager != null|| (toolbarManager instanceof ToolBarManager))
				{
					
					try {
						operationRecorder.recordUsingMenuToolIcons(NameSite,
								OperationSymbols.CONTEXTMENU_VALUE
								,ItemIndex,getDisplayName(menuItem),command.getId(),command.getName());
						
					} catch (NotDefinedException e) {
						e.printStackTrace();
					} 
				}
			}
			else if ((editor = getEditorParts(control)) != null)
			{   
				IToolBarManager toolbarManager = editor.getEditorSite().getActionBars().getToolBarManager();
				if (toolbarManager != null|| (toolbarManager instanceof ToolBarManager))
				{
				try {
						operationRecorder.recordUsingMenuToolIcons(NameSite,
								OperationSymbols.CONTEXTMENU_VALUE
								,ItemIndex,getDisplayName(menuItem),command.getId(),command.getName());
						
					} catch (NotDefinedException e) {
						e.printStackTrace();
					}
				}
			}
		 
			 
		}
		else if (widget instanceof ToolItem)
		{
			ToolItem toolItem = (ToolItem) widget;
			ItemIndex = getItemMenuToolId(toolItem);
			IViewPart ViewTool = null;
			IEditorPart EditorTool;
			if (isOnWorkbenchToolbar(toolItem))
			{
				try {
					operationRecorder.recordUsingMenuToolIcons(
							NameSite,
							OperationSymbols.TOOLBAR_VALUE
							,ItemIndex,getDisplayName(toolItem),
							command.getId(),
							command.getName());
				} catch (NotDefinedException e) {
					e.printStackTrace();
				} 
		 			
			
			}
			else if ((ViewTool = getViewParts(control)) != null)
			{
				try {
					operationRecorder.recordUsingMenuToolIcons(
							NameSite,
							OperationSymbols.TOOLBAR_VALUE
							,ItemIndex,getDisplayName(toolItem),
							command.getId(),
							command.getName());
				} catch (NotDefinedException e) {
					e.printStackTrace();
				} 
			}
			else if ((EditorTool = getEditorParts(control)) != null)
			{
				try {
					operationRecorder.recordUsingMenuToolIcons(
							NameSite,
							OperationSymbols.TOOLBAR_VALUE
							,ItemIndex,getDisplayName(toolItem),
							command.getId(),
							command.getName());
				} catch (NotDefinedException e) {
					e.printStackTrace();
				} 
			}
		}
		else if (widget instanceof Menu)
		{
			Menu menu = (Menu) widget;
			ItemIndex = getActionId((Menu) widget);
			try {
				operationRecorder.recordUsingMenuToolIcons(
						NameSite,
						OperationSymbols.MENUBAR_VALUE
						,ItemIndex,getDisplayName(menu),
						command.getId(),
						command.getName());
			} catch (NotDefinedException e) {
				e.printStackTrace();
			} 
		}
		
	}
	private static IViewPart getViewParts(Control control)
	{ IViewPart viewPart= null;
		Shell shell = control.getShell();
		Object data = shell.getData();
	    if (data instanceof IWorkbenchWindow)
		{	IWorkbenchWindow window = (IWorkbenchWindow) data;
			IWorkbenchPage page = window.getActivePage();
			IWorkbenchPart part = page.getActivePart();
			if (part == null)
				return null;
			if (part instanceof IViewPart)
			{
				viewPart=(IViewPart)part;
				return viewPart;
			}
		}
		return null;
	}
	private static IEditorPart getEditorParts(Control control)
	{ IEditorPart editorPart= null;
		Shell shell = control.getShell();
		Object data = shell.getData();
	    if (data instanceof IWorkbenchWindow)
		{	IWorkbenchWindow window = (IWorkbenchWindow) data;
			IWorkbenchPage page = window.getActivePage();
			IWorkbenchPart part = page.getActivePart();
			if (part == null)
				return null;
			if (part instanceof IEditorPart)
			{
				editorPart=(IEditorPart)part;
				return editorPart;
			}
		}
		return null;
	}
	private static String getNameSite(Control control) {
		Shell shell = control.getShell();
		Object data = shell.getData();
	    if (data instanceof IWorkbenchWindow)
		{	IWorkbenchWindow window = (IWorkbenchWindow) data;
			IWorkbenchPage page = window.getActivePage();
			IWorkbenchPart part = page.getActivePart();
			if (part == null)
				return null;
			IWorkbenchPartSite site = part.getSite();
			return site.getRegisteredName(); 
		}
		return null;
	}
	@Override
	public void preExecute(String commandId, ExecutionEvent event) {
		Command commandd = event.getCommand();
		Object Trigger= event.getTrigger(); 
		Event TriggerEvent = (Event)Trigger; 
		Widget widget =TriggerEvent.widget;
		getNameMenuToolBar(widget,commandd);
	 }
 	}
 
 