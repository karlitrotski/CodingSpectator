/**
 * This file is licensed under the University of Illinois/NCSA Open Source License. See LICENSE.TXT for details.
 */
package edu.illinois.codingspectator.ui.tests.pluginstests;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import edu.illinois.codingtracker.helpers.ResourceHelper;
import edu.illinois.codingtracker.recording.TextRecorder;

/**
 * @author Sebastian Sanchez
 * @author Carlos Dettoni
 */
public class PluginsListTest{
	
	protected static File mainRecordFile= new File(TextRecorder.getMainRecordFilePathXML());
	
	/*
	 * It's necessary clean the log on TextRecorder.getMainRecordFilePath() before run
	 * this test
	 */	
	@Test
	public void pluginsListOperationTest(){
		String generatedOperationsRecord= ResourceHelper.readFileContent(mainRecordFile);
		assertTrue(generatedOperationsRecord.contains("<PluginList>"));
	}
}

