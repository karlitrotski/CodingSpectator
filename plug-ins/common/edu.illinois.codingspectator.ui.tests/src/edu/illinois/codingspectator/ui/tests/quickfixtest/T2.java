package edu.illinois.codingspectator.ui.tests.quickfixtest;

import static org.junit.Assert.assertTrue;

import java.io.File;

import edu.illinois.codingtracker.helpers.ResourceHelper;
import edu.illinois.codingtracker.recording.TextRecorder;

public class T2 extends QuickFixTest{
	
	protected static File mainRecordFile= new File(TextRecorder.getMainRecordFilePath());
	
	@Override
	protected String getTestFileName() {
		return "ApplyQuickFix";
	}

	@Override
	protected void executeTest() {
		applyQuickFix(1, 2);
		bot.closeAll();
		String generatedOperationsRecord= ResourceHelper.readFileContent(mainRecordFile);
		assertTrue(generatedOperationsRecord.contains("StringBuffer3 cannot be resolved to a type"));
	}
	
}
