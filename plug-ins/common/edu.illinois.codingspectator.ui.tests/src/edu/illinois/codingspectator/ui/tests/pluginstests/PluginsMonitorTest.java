package edu.illinois.codingspectator.ui.tests.pluginstests;

import static org.junit.Assert.assertTrue;
import java.io.File;
import org.junit.Test;
import edu.illinois.codingtracker.helpers.ResourceHelper;
import edu.illinois.codingtracker.recording.TextRecorder;

public class PluginsMonitorTest {
	
	protected static File mainRecordFile= new File(TextRecorder.getMainRecordFilePath());
	
	@Test
	public void pluginsMonitorOperationTest(){
		String generatedOperationsRecord= ResourceHelper.readFileContent(mainRecordFile);		
		assertTrue(generatedOperationsRecord.contains("<TerminatedApplicationOperation>"));
	}
}
