package edu.illinois.codingspectator.monitor.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import edu.illinois.codingspectator.monitor.core.authentication.AuthenticationProvider;
import edu.illinois.codingspectator.monitor.ui.AuthenticationPrompter;
import edu.illinois.codingspectator.monitor.ui.RunningModes;
import edu.illinois.codingspectator.monitor.ui.prefs.PrefsFacade;


/**
 * This class tests the modification of the upload URL set in 
 * the preference store and whether this modification is reflected
 * in the AuthenticationPrompter's getUploardURL method
 * 
 * 
 * @author Tomas
 *
 */
public class TestUploadURLModification {
	AuthenticationProvider authProvider;
	
	@Before
	public void setUp(){
		authProvider = new AuthenticationPrompter();
	}

	
	@Test
	public void shouldChangeUploadUrl(){
		if (RunningModes.isInProductionMode()){
			shouldChangeUploadURL(PrefsFacade.ProductionUploadURLKey);
		}else{
			shouldChangeUploadURL(PrefsFacade.TestUploadURLKey);
		}
	}
	
	private void shouldChangeUploadURL(String key){
		String oldUploadURLValue = "oldUploadURLValue";
		String newUploadURLValue = "newUploadURLValue";
		
		PrefsFacade.getInstance().getPreferenceStore().setValue(key, oldUploadURLValue);
		assertEquals(oldUploadURLValue, authProvider.getRepositoryURL());
		
		PrefsFacade.getInstance().getPreferenceStore().setValue(key, newUploadURLValue);
		assertEquals(newUploadURLValue, authProvider.getRepositoryURL());

	}
}
