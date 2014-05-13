/**
 * This file is licensed under the University of Illinois/NCSA Open Source License. See LICENSE.TXT for details.
 */
package edu.illinois.codingtracker.recording.formats;

import edu.illinois.codingtracker.operations.OperationTextChunk;

/**
 * @author Kera Notebook 2.0
 *
 */
public abstract class AbsOriginalFormat implements IFormat{
	
	public OperationTextChunk generateSerializationText(){
		return null;
	}
	
	public void populateTextChunk(OperationTextChunk textChunk){
		
	}
}
