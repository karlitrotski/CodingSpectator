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
	
	protected void populateXMLTextChunk(OperationTextChunk textChunk) {
		textChunk.concat("<CompletionQuickfixOperation>"+"\n");
		textChunk.concat("\t" + "<errorInfo>");
		textChunk.concat("" + errorInfo);
		textChunk.concat("</errorInfo>" + "\n");
		textChunk.concat("\t" + "<errorText>");
		textChunk.concat("" + errorText);
		textChunk.concat("</errorText>" + "\n");
		textChunk.concat("\t" + "<offset>");
		textChunk.concat("" + offset);
		textChunk.concat("</offset>" + "\n");
		textChunk.concat("\t" + "<timestamp>");
		textChunk.concat("" + getTime());
		textChunk.concat("</timestamp>" + "\n");	
		textChunk.concat("</CompletionQuickfixOperation>"+"\n");
	}
	
	protected void populateCSVTextChunk(OperationTextChunk textChunk){
		textChunk.concat("CompletionQuickfixOperation , "+ getTime()+ " ,");
		textChunk.concat("\"[{ErrorInfo : "+ errorInfo +",");
		textChunk.concat("ErrorText : "+ errorText + ",");
		textChunk.concat("Offset : "+ offset + "}]\" \n");
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
