package edu.illinois.codingtracker.operations.shortcuts;

import edu.illinois.codingtracker.operations.OperationLexer;
import edu.illinois.codingtracker.operations.OperationTextChunk;
import edu.illinois.codingtracker.operations.UserOperation;

public class UsingMenuToolIcons extends UserOperation {
	private String type;
	private String nToolIcon;
	private String nMenuManager;
	private	String nSubMenu;
	public UsingMenuToolIcons(String type,String nToolIcon,String nMenuManager,String nSubMenu)
	{
		this.type= type;
		this.nToolIcon= nToolIcon;
		this.nMenuManager= nMenuManager;
		this.nSubMenu= nSubMenu;	
	}
	
	@Override
	protected char getOperationSymbol() { 
		return '0';
	}

	@Override
	public String getDescription() {
 		 return "get Name Menu and Name ToolIcons";
	}

	@Override
	protected void populateTextChunk(OperationTextChunk textChunk) {
		if(type.equals("ToolItem"))
		{
			textChunk.append(type +"-"+
								nMenuManager +"-"+
										nToolIcon);
		}
		else
		{
			textChunk.append(type +"-"+
								nMenuManager +"-"+
									nSubMenu);
		}
		
	}

	@Override
	protected void initializeFrom(OperationLexer operationLexer) {	}

	@Override
	public void replay() throws Exception {	
	}

}
