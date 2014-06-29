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
	public char getOperationSymbol() {
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
				textChunk.concat("\t" + "<CurrentMarker"+ i +">");
				textChunk.concat("\t" + "\t" + "<Kind>");
				textChunk.concat("" + kind);
				textChunk.concat("</Kind>" + "\n");
				textChunk.concat("\t" + "\t" + "<LineNumber>");
				textChunk.concat("" + lineNumber);
				textChunk.concat("</LineNumber>" + "\n");
				textChunk.concat("\t" + "\t" + "<Value>");
				textChunk.concat("" + value);
				textChunk.concat("</Value>" + "\n");
				textChunk.concat("\t" + "\t" + "<Message>");
				textChunk.concat("" + message);
				textChunk.concat("</Message>" + "\n");
				textChunk.concat("\t" + "\t" + "<Resource>");
				textChunk.concat("" + resource);	
				textChunk.concat("</Resource>" + "\n");
				textChunk.concat("\t" + "</CurrentMarker" + i +">" + "\n");
			}
		}
		textChunk.concat("\t" + "<timestamp>");
		textChunk.concat("" + getTime());
		textChunk.concat("</timestamp>" + "\n");
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

