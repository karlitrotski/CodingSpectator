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
	public void shouldUseDefaultUploadUrl(){
		if (RunningModes.isInProductionMode()){
			checkDefaultURL(PrefsFacade.ProductionUploadURLKey, PrefsFacade.ProductionUploadURL_Default);
		}else{
			checkDefaultURL(PrefsFacade.TestUploadURLKey, PrefsFacade.TestUploadURL_Default);
		}
	}
	

	@Test
	public void shouldChangeUploadURL(){
		if (RunningModes.isInProductionMode()){
			checkChangeUploadURL(PrefsFacade.ProductionUploadURLKey);
		}else{
			checkChangeUploadURL(PrefsFacade.TestUploadURLKey);
		}
	}
	
	private void checkDefaultURL(String key, String defaultValue) {
		assertEquals(defaultValue, authProvider.getRepositoryURL());
	}
	
	private void checkChangeUploadURL(String key){
		String oldUploadURLValue = "oldUploadURLValue";
		String newUploadURLValue = "newUploadURLValue";
		
		PrefsFacade.getInstance().getPreferenceStore().setValue(key, oldUploadURLValue);
		assertEquals(oldUploadURLValue, authProvider.getRepositoryURL());
		
		PrefsFacade.getInstance().getPreferenceStore().setValue(key, newUploadURLValue);
		assertEquals(newUploadURLValue, authProvider.getRepositoryURL());

	}
}
