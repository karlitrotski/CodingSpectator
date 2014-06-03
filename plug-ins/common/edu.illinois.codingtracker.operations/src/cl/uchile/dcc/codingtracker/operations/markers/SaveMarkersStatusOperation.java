package cl.uchile.dcc.codingtracker.operations.markers;

import org.eclipse.core.resources.IMarkerDelta;
import org.eclipse.core.resources.IResourceDelta;
import edu.illinois.codingtracker.operations.OperationLexer;
import edu.illinois.codingtracker.operations.OperationSymbols;
import edu.illinois.codingtracker.operations.OperationTextChunk;
import edu.illinois.codingtracker.operations.UserOperation;

/**
 * 
 * @author Joffre Yagual
 * 
 */
public class SaveMarkersStatusOperation extends UserOperation{
	
	protected IMarkerDelta [] currentMarkers;
	
	public SaveMarkersStatusOperation(IMarkerDelta [] deltas) {
		super();
		currentMarkers = deltas;
	}

	@Override
	protected char getOperationSymbol() {
		return OperationSymbols.CODE_ERROR_SYMBOL;
	}

	@Override
	public String getDescription() {
		return "Current markers status";
	}

	@Override
	protected void populateTextChunk(OperationTextChunk textChunk) {
		for (int i = 0; i < currentMarkers.length; i++) {
			int kind = currentMarkers[i].getKind();
			String lineNumber = currentMarkers[i].getAttribute("lineNumber").toString();
			String value = currentMarkers[i].getAttribute("arguments").toString();
			String message = currentMarkers[i].getAttribute("message").toString();
			String resource = currentMarkers[i].getResource().getFullPath().toString();
			if (kind == IResourceDelta.ADDED || kind == IResourceDelta.REMOVED) {
				textChunk.append(kind);
				textChunk.append(lineNumber);
				textChunk.append(value);
				textChunk.append(message);
				textChunk.append(resource);				
			}
		}
	}
	
	@Override
	protected void populateXMLTextChunk(OperationTextChunk textChunk){
		textChunk.concat("<SaveMarkersStatusOperation>" + "\n");
		for (int i = 0; i < currentMarkers.length; i++) {
			int kind = currentMarkers[i].getKind();
			String lineNumber = currentMarkers[i].getAttribute("lineNumber").toString();
			String value = currentMarkers[i].getAttribute("arguments").toString();
			String message = currentMarkers[i].getAttribute("message").toString();
			String resource = currentMarkers[i].getResource().getFullPath().toString();
			if (kind == IResourceDelta.ADDED || kind == IResourceDelta.REMOVED) {
				textChunk.concat("\t" + "\t" + "<CurrentMarker"+ i +">" + "\n");
				textChunk.concat("\t" + "\t" + "<Kind>" + "\n");
				textChunk.concat("\t" + "\t" + kind);
				textChunk.concat("\t" + "\t" + "</Kind>" + "\n");
				textChunk.concat("\t" + "\t" + "<LineNumber>" + "\n");
				textChunk.concat("\t" + "\t" + lineNumber);
				textChunk.concat("\t" + "\t" + "</LineNumber>" + "\n");
				textChunk.concat("\t" + "\t" + "<Value>" + "\n");
				textChunk.concat("\t" + "\t" + value);
				textChunk.concat("\t" + "\t" + "</Value>" + "\n");
				textChunk.concat("\t" + "\t" + "<Message>" + "\n");
				textChunk.concat("\t" + "\t" + message);
				textChunk.concat("\t" + "\t" + "</Message>" + "\n");
				textChunk.concat("\t" + "\t" + "<Resource>" + "\n");
				textChunk.concat("\t" + "\t" + resource);	
				textChunk.concat("\t" + "\t" + "</Resource>" + "\n");
				textChunk.concat("\t" + "\t" + "</CurrentMarker" + i +">" + "\n");
			}
		}
		textChunk.concat("\t" + "<timestamp>" + "\n");
		textChunk.concat("\t" + getTime() + "\n");
		textChunk.concat("\t" + "</timestamp>" + "\n");
		textChunk.concat("</SaveMarkersStatusOperation>" + "\n");
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

