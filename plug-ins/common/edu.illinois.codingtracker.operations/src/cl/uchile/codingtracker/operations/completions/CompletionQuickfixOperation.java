
package cl.uchile.codingtracker.operations.completions;

import edu.illinois.codingtracker.operations.OperationLexer;
import edu.illinois.codingtracker.operations.OperationSymbols;
import edu.illinois.codingtracker.operations.OperationTextChunk;
import edu.illinois.codingtracker.operations.UserOperation;

/**
 * 
 * @author Maite Gonzalez
 * 
 */
public class CompletionQuickfixOperation extends UserOperation {

	//TODO QF
	protected int annotationErrorsNumber;
	protected int line;
	
	public CompletionQuickfixOperation() {
		super();
	}
	//TODO QF
	public CompletionQuickfixOperation(int currentErrorsNumber) {
		super();
		this.annotationErrorsNumber= currentErrorsNumber;
	}

	@Override
	protected char getOperationSymbol() {
		return OperationSymbols.QUICKFIX_INVOCATION_SYMBOL;
	}

	@Override
	public String getDescription() {
		return "New QuickFix invocation";
	}
	//TODO QF
	@Override
	protected void populateTextChunk(OperationTextChunk textChunk) {
		textChunk.append(Integer.toString(this.annotationErrorsNumber));
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
