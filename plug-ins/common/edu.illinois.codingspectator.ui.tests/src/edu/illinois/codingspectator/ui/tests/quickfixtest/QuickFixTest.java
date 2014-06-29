package edu.illinois.codingspectator.ui.tests.quickfixtest;

import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEclipseEditor;
import org.junit.BeforeClass;
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
	
	protected void doAddJavaClass(String file) throws Exception {
		bot.createANewJavaClass(getProjectName(), getTestFileName());
		bot.prepareJavaTextInEditor(file, getTestFileFullName());
	}
	
	
	@BeforeClass
	public static void beforeClass() throws Exception {
		bot =new CodingSpectatorBot();
		bot.dismissWelcomeScreenIfPresent();
	}
	
	public final void setupProject(String file) throws Exception {
		bot.createANewJavaProject(getProjectName());
		doAddJavaClass(file);
	}

	
	protected void applyQuickFix(int index){
		SWTBotEclipseEditor e = bot.getTextEditor(getTestFileName());
		bot.activeTabItem(getTestFileName());
		e.quickfix(index);
	}

}
