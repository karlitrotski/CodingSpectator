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

	public ShortCutCommandName(String nCommand, String nkeyShortcuts)
	{
		this.commandName=nCommand;
		this.KeyShortcuts=nkeyShortcuts;
	}
	@Override
	protected char getOperationSymbol() {
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
	protected void initializeFrom(OperationLexer operationLexer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void replay() throws Exception {
		// TODO Auto-generated method stub
		
	}


}
