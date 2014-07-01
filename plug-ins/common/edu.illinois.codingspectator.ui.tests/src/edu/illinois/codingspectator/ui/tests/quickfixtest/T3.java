/**
 * This file is licensed under the University of Illinois/NCSA Open Source License. See LICENSE.TXT for details.
 */
package edu.illinois.codingspectator.ui.tests.quickfixtest;

import static org.junit.Assert.assertTrue;

import java.io.File;

import edu.illinois.codingtracker.helpers.ResourceHelper;
import edu.illinois.codingtracker.recording.TextRecorder;

public class T3 extends QuickFixTest{
	
	protected static File mainRecordFile= new File(TextRecorder.getMainRecordFilePath());
	
	@Override
	protected String getTestFileName() {
		return "WriteError";
	}
	
	/*
	 * It's necessary clean the log on TextRecorder.getMainRecordFilePath() before run
	 * this test
	 */
	@Override
	public void executeTest(){
		bot.closeAll();
		String generatedOperationsRecord= ResourceHelper.readFileContent(mainRecordFile);
		assertTrue(generatedOperationsRecord.contains("StringBuffer3 cannot be resolved to a type"));
	}

}