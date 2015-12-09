package edu.illinois.codingtracker.operations.shortcuts;

import edu.illinois.codingtracker.operations.OperationLexer;
import edu.illinois.codingtracker.operations.OperationSymbols;
import edu.illinois.codingtracker.operations.OperationTextChunk;
import edu.illinois.codingtracker.operations.UserOperation;
/**
 * Class write symbol W when developer uses keys ShortCuts and
 * Write the ShortCuts command of execution name Example #W#Ctrl+C->Copy#
 * Related to issue karlitrotski/CodingSpectator/#14
 * @author Teofilo Chambilla Aquino
 *
 */
public class ShortCutCommandName extends UserOperation{ 

	private String commandName;
	private String KeyShortcuts;

		public ShortCutCommandName() {
		super();
	}
	
	public ShortCutCommandName(String nCommand, String nkeyShortcuts)
	{
		this.commandName=nCommand;
		this.KeyShortcuts=nkeyShortcuts;
	}

	@Override
	public char getOperationSymbol() {
		return OperationSymbols.APPLICATION_SHORTCUTS;
	}

	@Override
	public String getDescription() {
		return "get ShortCuts and NameCommand";
	 
	}

	@Override
	protected void populateTextChunk(OperationTextChunk textChunk) {
		textChunk.append(KeyShortcuts.trim() +"->"+commandName);
	}
	
	@Override
	protected void populateXMLTextChunk(OperationTextChunk textChunk){
		textChunk.concat("<ShortCutCommandName>" + "\n");
		textChunk.concat("\t" + "<KeyShortCuts>");
		textChunk.concat("" + KeyShortcuts.trim());
		textChunk.concat("</KeyShortCuts>" + "\n");
		textChunk.concat("\t" + "<CommandName>");
		textChunk.concat("" + commandName);
		textChunk.concat("</CommandName>" + "\n");
		textChunk.concat("\t" + "<timestamp>");
		textChunk.concat("" + getTime());
		textChunk.concat("</timestamp>" + "\n");
		textChunk.concat("</ShortCutCommandName>" + "\n");
	}
	
	protected void populateCSVTextChunk(OperationTextChunk textChunk){
		textChunk.concat("ShortCutCommandName , "+ getTime()+ " ,");
		textChunk.concat("\"[{[{KeyShortCuts : "+ KeyShortcuts.trim() +",");
		textChunk.concat("CommandName : "+ commandName + "}]\" \n");
	}

	@Override
	protected void initializeFrom(OperationLexer operationLexer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void replay() throws Exception {
		// TODO Auto-generated method stub
		
	}


}
