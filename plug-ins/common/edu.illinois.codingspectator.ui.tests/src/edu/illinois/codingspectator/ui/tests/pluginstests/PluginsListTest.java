package edu.illinois.codingspectator.ui.tests.pluginstests;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import edu.illinois.codingtracker.helpers.ResourceHelper;
import edu.illinois.codingtracker.recording.TextRecorder;

public class PluginsListTest{
	
	protected static File mainRecordFile= new File(TextRecorder.getMainRecordFilePath());
	
	@Test
	public void logTest(){
		String generatedOperationsRecord= ResourceHelper.readFileContent(mainRecordFile);
		assertTrue(generatedOperationsRecord.contains("<PluginList>"));
	}
}

