/**
 * This file is licensed under the University of Illinois/NCSA Open Source License. See LICENSE.TXT for details.
 */
package edu.illinois.codingtracker.listeners;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchesListener2;
import org.eclipse.debug.core.model.IProcess;

import edu.illinois.codingtracker.helpers.Debugger;
import edu.illinois.codingtracker.helpers.Messages;

/**
 * 
 * @author Stas Negara
 * 
 * @author Juraj Kubelka, @author Catalina Espinoza Inaipil - we change ILaunchListener to ILaunchesListener2, 
 * to record when a launch is terminated and then store exit values.
 */
public class LaunchListener extends BasicListener implements ILaunchesListener2 {

	public static void register() {
		DebugPlugin.getDefault().getLaunchManager().addLaunchListener(new LaunchListener());
	}

	public void launchAdded(ILaunch launch) {
		try {
			String launchMode= launch.getLaunchMode();
			ILaunchConfiguration launchConfiguration= launch.getLaunchConfiguration();
			String launchName= launchConfiguration.getName();
			String application= launchConfiguration.getAttribute("application", "");
			String product= launchConfiguration.getAttribute("product", "");
			boolean useProduct= launchConfiguration.getAttribute("useProduct", false);
			operationRecorder.recordLaunchedApplication(launchMode, launchName, application, product, useProduct);
		} catch (CoreException e) {
			Debugger.logExceptionToErrorLog(e, Messages.Recorder_LaunchConfigurationException);
		}
	}

	public void launchTerminated(ILaunch launch) {
		try {
			String launchMode = launch.getLaunchMode();
			ILaunchConfiguration launchConfiguration= launch.getLaunchConfiguration();
			String launchName = launchConfiguration.getName();
			String application = launchConfiguration.getAttribute("application", "");
			String product = launchConfiguration.getAttribute("product", "");
			boolean useProduct = launchConfiguration.getAttribute("useProduct", false);
			IProcess[] processes = launch.getProcesses();
			int[] exitValues = new int[processes.length];
			for (int i = 0; i < processes.length; i++) {
				exitValues[i] = processes[i].getExitValue();
			};
			operationRecorder.recordTerminatedApplication(launchMode, launchName, application, product, useProduct, 
					exitValues);
		} catch (DebugException e) {
			Debugger.logExceptionToErrorLog(e, Messages.Recorder_RemovedConfigurationException);
		} catch (CoreException e) {
			Debugger.logExceptionToErrorLog(e, Messages.Recorder_RemovedConfigurationException);
		}
	}

	@Override
	public void launchesAdded(ILaunch[] launches) {
		for(ILaunch launch : launches) {
			this.launchAdded(launch);
		}
	}

	@Override
	public void launchesTerminated(ILaunch[] launches) {
		for(ILaunch launch : launches) {
			this.launchTerminated(launch);
		}
	}

	@Override
	public void launchesRemoved(ILaunch[] launches) {
		// nothing to do
	}

	@Override
	public void launchesChanged(ILaunch[] launches) {
		// nothing to do
	}
}
