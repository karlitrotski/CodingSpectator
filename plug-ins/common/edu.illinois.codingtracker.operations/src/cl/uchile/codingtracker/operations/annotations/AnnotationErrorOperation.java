
package cl.uchile.codingtracker.operations.annotations;

import edu.illinois.codingtracker.operations.OperationLexer;
import edu.illinois.codingtracker.operations.OperationSymbols;
import edu.illinois.codingtracker.operations.OperationTextChunk;
import edu.illinois.codingtracker.operations.UserOperation;

/**
 * 
 * @author Joffre Yagual
 * 
 */
public class AnnotationErrorOperation extends UserOperation {

	protected int annotationErrorsNumber;

	public AnnotationErrorOperation() {
		super();
	}

	public AnnotationErrorOperation(int currentErrorsNumber) {
		super();
		this.annotationErrorsNumber= currentErrorsNumber;
	}

	@Override
	protected char getOperationSymbol() {
		return OperationSymbols.CODE_ERROR_SYMBOL;
	}

	@Override
	public String getDescription() {
		return "New spelling error detected";
	}

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
