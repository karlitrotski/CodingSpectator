package edu.illinois.codingspectator.ui.tests.modifyuploadurl;

import static org.junit.Assert.assertEquals;

import org.eclipse.swtbot.eclipse.finder.SWTWorkbenchBot;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.illinois.codingspectator.monitor.ui.AuthenticationPrompter;
import edu.illinois.codingspectator.monitor.ui.prefs.PrefsFacade;

public class TestModifyUploadUrl {
	SWTWorkbenchBot bot;
	AuthenticationPrompter authProvider;
	
	@Before
	public void setUp(){
		authProvider = new AuthenticationPrompter();
		bot = new SWTWorkbenchBot();
		dismissWelcomeScreenIfPresent();
	}
	
	@After
	public void tearDown(){
		bot.resetWorkbench(); 
	}
	
	@Test
	public void shouldChangeTestUploadURL(){
		assertEquals(PrefsFacade.TestUploadURL_Default, authProvider.getRepositoryURL());
		String newUploadURLValue= "newTestUploadURLValue";
		
		if(System.getProperty("os.name").startsWith("Windows")){
			bot.menu("Window").menu("Preferences").click();
			SWTBotShell shell=bot.shell("Preferences");
			shell.activate();
			bot.tree().expandNode("CodingSpectator").select("Uploading");
			bot.textWithLabel(PrefsFacade.TestUploadURLKey).setText(newUploadURLValue);
			bot.button("OK").click();
			
			/* 
			bot.tree().getTreeItem("CodingSpectator").select();
			bot.tree().getTreeItem("CodingSpectator").getNode("Uploading").select(); */
			assertEquals(newUploadURLValue, authProvider.getRepositoryURL());
		}
	}

	public void dismissWelcomeScreenIfPresent() {
		try {
			bot.viewByTitle("Welcome").close();
		} catch (WidgetNotFoundException exception) {
			// The welcome screen might not be shown so just ignore
		}
	}
}
