
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
	public char getOperationSymbol() {
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
	
	protected void populateXMLTextChunk(OperationTextChunk textChunk) {
		textChunk.concat("<AnnotationErrorOperation>"+"\n");
		textChunk.concat("\t"+"<ErrorsNumber>");
		textChunk.concat(""+annotationErrorsNumber);
		textChunk.concat("</ErrorsNumber>"+"\n");
		textChunk.concat("\t" + "<timestamp>");
		textChunk.concat("" + getTime());
		textChunk.concat("</timestamp>" + "\n");	
		textChunk.concat("</AnnotationErrorOperation>"+"\n");
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
