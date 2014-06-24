package edu.illinois.codingtracker.operations.shortcuts;

import edu.illinois.codingtracker.operations.OperationLexer;
import edu.illinois.codingtracker.operations.OperationTextChunk;
import edu.illinois.codingtracker.operations.UserOperation;

public class UsingMenuToolIcons extends UserOperation {
	private String nPart;
	private String nType;
	private String nIndex;
	private String nName;
	private	String nNameCommand;
	private String nIdCommand;
	public UsingMenuToolIcons(String Part,String type,String Index,String Name,String idcommand, String command)
	{
		this.nPart=Part;
		this.nType= type;
		this.nIndex= Index;
		this.nName= Name;
		this.nNameCommand= command;
		this.nIdCommand=idcommand;
	}
	
	@Override
	protected char getOperationSymbol() { 
		return '0';
	}

	@Override
	public String getDescription() {
 		 return "get Name Menu,Context Name ToolbarIcons";
	}

	@Override
	protected void populateTextChunk(OperationTextChunk textChunk) {
		
			textChunk.append(nPart+"/"+nType+"/["+nIndex +"]["+nName+"]-["+nIdCommand+"]["+nNameCommand+"]");
				
	}

	@Override
	protected void initializeFrom(OperationLexer operationLexer) {	}

	@Override
	public void replay() throws Exception {	
	}

	@Override
	protected void populateXMLTextChunk(OperationTextChunk textChunk) {
		// TODO Auto-generated method stub
		
	}

}
