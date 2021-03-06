/**
 * This file is licensed under the University of Illinois/NCSA Open Source License. See LICENSE.TXT for details.
 */
package edu.illinois.codingtracker.operations;

/**
 * 
 * @author Stas Negara
 * 
 */
public abstract class OperationSymbols {

	//Used symbols: 26 + 17, remaining symbols: Y Z
	//18-04-2014 Add Symbol Q and R for Application_Loose_focus/Gains
	/** Values for get Names Menu, ToolBar, Icons*/
	public static final String EMPTY_STRING = "";
	public static final String VIEW_VALUE = "View";
	public static final String EDITOR_VALUE = "Editor";
	public static final String SHELL_VALUE = "Window";
	public static final String MENUBAR_VALUE = "MenuBar";
	public static final String CONTEXTMENU_VALUE = "ContextMenu"; 
	public static final String TOOLBAR_VALUE = "Toolbar";
	/*****/	
	public static final char APPLICATION_SHORTCUTS= 'W';
	public static final char APPLICATION_FOCUS_GAINS= 'Q';

	public static final char APPLICATION_FOCUS_LOOSE= 'R';
	
	public static final char ECLIPSE_STARTED_SYMBOL= 'l';

	public static final char NEW_REFACTORING_STARTED_SYMBOL= 'J';

	public static final char REFACTORING_FINISHED_SYMBOL= 'K';

	public static final char REFACTORING_STARTED_SYMBOL= 'b';

	public static final char REFACTORING_PERFORMED_SYMBOL= 'p';

	public static final char REFACTORING_UNDONE_SYMBOL= 'u';

	public static final char REFACTORING_REDONE_SYMBOL= 'r';

	public static final char CONFLICT_EDITOR_OPENED_SYMBOL= 'g';

	public static final char CONFLICT_EDITOR_CLOSED_SYMBOL= 'q';

	public static final char CONFLICT_EDITOR_SAVED_SYMBOL= 'z';

	public static final char RESOURCE_CREATED_SYMBOL= 'P';

	public static final char RESOURCE_MOVED_SYMBOL= 'L';

	public static final char RESOURCE_COPIED_SYMBOL= 'M';

	public static final char RESOURCE_DELETED_SYMBOL= 'N';

	public static final char FILE_CLOSED_SYMBOL= 'c';

	public static final char FILE_SAVED_SYMBOL= 's';

	//Includes actual external modifications and deletions, deletions that happen as a part of an update, 
	//and modifications that are result of 'Revert' operation
	public static final char RESOURCE_EXTERNALLY_MODIFIED_SYMBOL= 'x';

	public static final char FILE_UPDATED_SYMBOL= 'm';

	public static final char FILE_SVN_INITIALLY_COMMITTED_SYMBOL= 'i';

	public static final char FILE_CVS_INITIALLY_COMMITTED_SYMBOL= 'I';

	public static final char FILE_SVN_COMMITTED_SYMBOL= 'o';

	public static final char FILE_CVS_COMMITTED_SYMBOL= 'O';

	public static final char FILE_REFACTORED_SAVED_SYMBOL= 'a';

	public static final char FILE_NEW_SYMBOL= 'f';

	public static final char FILE_EDITED_SYMBOL= 'e';

	public static final char FILE_EDITED_UNSYNCHRONIZED_SYMBOL= 'H';

	public static final char FILE_REFRESHED_SYMBOL= 'G';

	public static final char TEXT_CHANGE_PERFORMED_SYMBOL= 't';

	public static final char TEXT_CHANGE_UNDONE_SYMBOL= 'h';

	public static final char TEXT_CHANGE_REDONE_SYMBOL= 'd';

	public static final char CONFLICT_EDITOR_TEXT_CHANGE_PERFORMED_SYMBOL= 'j';

	public static final char CONFLICT_EDITOR_TEXT_CHANGE_UNDONE_SYMBOL= 'k';

	public static final char CONFLICT_EDITOR_TEXT_CHANGE_REDONE_SYMBOL= 'n';

	public static final char TEST_SESSION_LAUNCHED_SYMBOL= 'v';

	public static final char TEST_SESSION_STARTED_SYMBOL= 'w';

	public static final char TEST_SESSION_FINISHED_SYMBOL= 'y';

	public static final char TEST_CASE_STARTED_SYMBOL= 'A';

	public static final char TEST_CASE_FINISHED_SYMBOL= 'B';

	public static final char APPLICATION_LAUNCHED_SYMBOL= 'C';

	public static final char APPLICATION_TERMINATED_SYMBOL = 'Z';

	public static final char WORKSPACE_OPTIONS_CHANGED_SYMBOL= 'D';

	public static final char PROJECT_OPTIONS_CHANGED_SYMBOL= 'E';

	public static final char REFERENCING_PROJECTS_CHANGED_SYMBOL= 'F';
	
	public static final char QUICKFIX_INVOCATION_SYMBOL= 'S';
	
	public static final char QUICKFIX_USAGE_SYMBOL= 'T';
	
	public static final char CODE_ERROR_SYMBOL= 'U';
	
	public static final char PLUGINS_SYMBOL= 'V'; //Issue ListaPlugins

	public static final char EDIT_PART_OPERATION_SYMBOL= '@';
	
	public static final char PLUGINS_USAGE = 'X';

	public static final char VIEW_PART_OPERATION_SYMBOL = '%';
	
	public static final char SELECTION_CHANGED_OPERATION_SYMBOL = '*';	
}
