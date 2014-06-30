package edu.illinois.codingspectator.ui.tests.quickfixtest;

import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEclipseEditor;
import org.junit.BeforeClass;
import org.junit.Test;

import edu.illinois.codingspectator.ui.tests.CodingSpectatorBot;


/**
 * @author Sebastian Sanchez
 * @author Carlos Dettoni
 */
public abstract class QuickFixTest {
	
	protected static CodingSpectatorBot bot;
	
	protected String getProjectName() {
		return "Test";
	}
	
	protected abstract String getTestFileName();
	
	protected String getTestFileFullName() {
		return getTestFileName() + ".java";
	}
	
	protected void doAddJavaClass() throws Exception {
		bot.createANewJavaClass(getProjectName(), getTestFileName());
		bot.prepareJavaTextInEditor("quickfixtest", getTestFileFullName());
	}
	
	
	@BeforeClass
	public static void beforeClass() throws Exception {
		bot =new CodingSpectatorBot();
		bot.dismissWelcomeScreenIfPresent();
	}
	
	@Test
	public final void test() throws Exception{
		setupProject();
		executeTest();
	}
	protected abstract void executeTest();

	public final void setupProject() throws Exception {
		bot.createANewJavaProject(getProjectName());
		doAddJavaClass();
	}

	
	protected void applyQuickFix(int index){
		SWTBotEclipseEditor e = bot.getTextEditor(getTestFileName());
		bot.activeTabItem(getTestFileName());
		e.quickfix(index);
	}

}
