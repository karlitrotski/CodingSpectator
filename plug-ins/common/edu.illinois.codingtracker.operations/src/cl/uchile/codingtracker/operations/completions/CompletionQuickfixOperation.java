package cl.uchile.codingtracker.operations.completions;

import edu.illinois.codingtracker.operations.OperationLexer;
import edu.illinois.codingtracker.operations.OperationSymbols;
import edu.illinois.codingtracker.operations.OperationTextChunk;
import edu.illinois.codingtracker.operations.UserOperation;

public class CompletionQuickfixOperation extends UserOperation {

	protected String errorInfo, errorText;
	protected int offset;
	
	public CompletionQuickfixOperation() {
		super();
	}

	public CompletionQuickfixOperation(String errorInfo, String errorText, int offset) {
		super();
		this.errorInfo= errorInfo;
		this.errorText= errorText;
		this.offset= offset;
	}

	@Override
	public char getOperationSymbol() {
		return OperationSymbols.QUICKFIX_INVOCATION_SYMBOL;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void populateTextChunk(OperationTextChunk textChunk) {
		textChunk.append(errorInfo);
		textChunk.append(errorText);
		textChunk.append(offset);
	}
	/*esto deberìa funcionar en el master
	@Override
	protected void populateXMLTextChunk(OperationTextChunk textChunk){
		textChunk.concat("CompletionQuickFixOperation");
		super.populateXMLTextChunk(textChunk);
		//textChunk.concat(result);
		textChunk.concat("\t" + "" + "\n"); 
		textChunk.concat("\t" + getTime() + "\n"); 
		textChunk.concat("\t" + "" + "\n");
		
	}
	*/
	
	protected void populateXMLTextChunk(OperationTextChunk textChunk) {
		textChunk.concat("<CompletionQuickfixOperation>"+"\n");
		textChunk.concat("\t" + "<timestamp>");
		textChunk.concat("" + getTime());
		textChunk.concat("</timestamp>" + "\n");	
		textChunk.concat("</CompletionQuickfixOperation>"+"\n");
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
