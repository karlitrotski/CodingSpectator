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
		textChunk.concat("<UsingMenuToolIcons>" + "\n");
		textChunk.concat("\t" + "<Area>" + "\n");
		textChunk.concat("\t" + nPart + "\n");
		textChunk.concat("\t" + "</Area>" + "\n");
		
		textChunk.concat("\t" + "<Type>" + "\n");
		textChunk.concat("\t" + nType + "\n");
		textChunk.concat("\t" + "</Type>" + "\n");
		
		textChunk.concat("\t" + "<Index>" + "\n");
		textChunk.concat("\t" + nIndex + "\n");
		textChunk.concat("\t" + "</Index>" + "\n");

		textChunk.concat("\t" + "<Name>" + "\n");
		textChunk.concat("\t" + nName + "\n");
		textChunk.concat("\t" + "</Name>" + "\n");
		
		textChunk.concat("\t" + "<CommandId>" + "\n");
		textChunk.concat("\t" + nIdCommand + "\n");
		textChunk.concat("\t" + "</CommandId>" + "\n");
		
		textChunk.concat("\t" + "<CommandName>" + "\n");
		textChunk.concat("\t" + nNameCommand + "\n");
		textChunk.concat("\t" + "</CommandName>" + "\n");
		
		textChunk.concat("\t" + "<timestamp>" + "\n");
		textChunk.concat("\t" + getTime() + "\n");
		textChunk.concat("\t" + "</timestamp>" + "\n");
		textChunk.concat("</UsingMenuToolIcons>" + "\n");
		
	}

}
