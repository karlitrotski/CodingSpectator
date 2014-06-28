/**
 * This file is licensed under the University of Illinois/NCSA Open Source License. See LICENSE.TXT for details.
 */
package edu.illinois.codingspectator.ui.tests.pluginstests;

import static org.junit.Assert.assertTrue;
import java.io.File;

import org.eclipse.swtbot.eclipse.finder.SWTWorkbenchBot;
import org.junit.BeforeClass;
import org.junit.Test;

import edu.illinois.codingtracker.helpers.ResourceHelper;
import edu.illinois.codingtracker.recording.TextRecorder;

/**
 * @author Sebastian Sanchez
 * @author Carlos Dettoni
 */
public class PluginsMonitorTest {
	
	protected static File mainRecordFile= new File(TextRecorder.getMainRecordFilePath());
	private static SWTWorkbenchBot	bot;
	
	@BeforeClass
	public static void beforeClass() throws Exception {
		bot = new SWTWorkbenchBot();
	}
	
	/*
	 * It's necessary clean the log on TextRecorder.getMainRecordFilePath() before run
	 * this test
	 */
	@Test
	public void pluginsMonitorOperationTest(){
		bot.sleep(150000);
		String generatedOperationsRecord= ResourceHelper.readFileContent(mainRecordFile);		
		assertTrue(generatedOperationsRecord.contains("<TerminatedApplicationOperation>"));
	}
}
