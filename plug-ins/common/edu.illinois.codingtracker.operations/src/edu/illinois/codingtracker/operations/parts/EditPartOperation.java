package edu.illinois.codingtracker.operations.parts;

import org.eclipse.core.resources.IFile;

import edu.illinois.codingtracker.operations.OperationLexer;
import edu.illinois.codingtracker.operations.OperationSymbols;
import edu.illinois.codingtracker.operations.OperationTextChunk;
import edu.illinois.codingtracker.operations.files.FileOperation;
/**
 * @see edu.illinois.codingtracker.listeners.PartListener.partActivated(IWorkBenchPart part)
 * 
 * @author Juraj Kubelka, @author Catalina Espinoza Inaipil
 * It records the editor parts states (@see edu.illinois.codingtracker.operations.parts.IPartState).
 * */
public class EditPartOperation extends FileOperation implements IPartState {
	
	private String state;
	
	public EditPartOperation() {
		super();
	}

	public EditPartOperation(IFile file, String state) {
		super(file);
		this.state = state;
	}

	@Override
	public char getOperationSymbol() {	
		return OperationSymbols.EDIT_PART_OPERATION_SYMBOL;
	}

	@Override
	public String getDescription() {
		return "Edit Part Operation, state: " + state;
	}

	@Override
	public void replay() throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	protected void populateTextChunk(OperationTextChunk textChunk) {
		textChunk.append(state);
		super.populateTextChunk(textChunk);
	}
	
	@Override
	protected void populateXMLTextChunk(OperationTextChunk textChunk){
		textChunk.concat("<EditPartOperation>" + "\n");
		super.populateXMLTextChunk(textChunk);
		textChunk.concat("\t" + "<state>" + state + "</state>" + "\n");
		textChunk.concat("\t" + "<timestamp>" + getTime() + "</timestamp>" + "\n");
		textChunk.concat("</EditPartOperation>" + "\n");
	}
	
	@Override
	protected void populateCSVTextChunk(OperationTextChunk textChunk){
		textChunk.concat("EditPartOperation , "+ getTime()+ " ,");
		textChunk.concat("\"[{");
		super.populateCSVTextChunk(textChunk);
		textChunk.concat(",");
		textChunk.concat("state : "+ state + "}]\" \n");
	}

	@Override
	protected void initializeFrom(OperationLexer operationLexer) {
		state = operationLexer.readString();
		super.initializeFrom(operationLexer);
	}

}
