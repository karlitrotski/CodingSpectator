/**
 * This file is licensed under the University of Illinois/NCSA Open Source License. See LICENSE.TXT for details.
 */
package edu.illinois.codingtracker.recording.tests;

import static org.junit.Assert.*;

import edu.illinois.codingtracker.operations.conflicteditors.*;
import org.junit.*;
/**
 * @author Matías Pardo Gutiérrez
 *
 */
public class ConflictEditorOperationRecordTest {
	
	private OpenedConflictEditorOperation openedConflictEditorOperation;
	private SavedConflictEditorOperation savedConflictEditorOperation;
	
	@Before
	public void setUp(){
		openedConflictEditorOperation= new OpenedConflictEditorOperation();
		savedConflictEditorOperation = new SavedConflictEditorOperation();
		
	}
	
	//Test for ConflictEditorOperation
	@Test
	public void testingConflictEditorOperation(){
		//OperationTextChunk textChunk = openedConflictedEditorOperation;
	}
	
	//Test for OpenedConflictEditorOperation
	@Test
	public void testingOpenedConflictEditorOperation(){
		
	}
	
	//Test for SavedConflictEditorOperation
	@Test
	public void testingSavedConflictEditorOperation(){
		
	}
}
