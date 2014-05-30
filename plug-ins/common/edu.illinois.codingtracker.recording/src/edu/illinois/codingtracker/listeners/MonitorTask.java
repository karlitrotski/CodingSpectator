package edu.illinois.codingtracker.listeners;

import java.util.TimerTask;
import java.util.TreeMap;

import org.eclipse.core.runtime.IBundleGroup;
import org.eclipse.core.runtime.IBundleGroupProvider;
import org.eclipse.core.runtime.Platform;
import org.osgi.framework.Bundle;

import edu.illinois.codingtracker.operations.pluginusage.*;
import edu.illinois.codingtracker.recording.TextRecorder;

/* Class that compare all plug-in states, and throw a message when they change. */
public class MonitorTask extends TimerTask {
	
	TreeMap<String, Integer> bundlesMap = new TreeMap<String, Integer>(String.CASE_INSENSITIVE_ORDER);
	
	public MonitorTask(){
		IBundleGroupProvider[] providers = Platform.getBundleGroupProviders();
		if (providers != null) {
		    for (int i = 0; i < providers.length; i++) {
		        IBundleGroup[] bundleGroups = providers[i].getBundleGroups();
		        for (int j = 0; j < bundleGroups.length; j++) {
		            Bundle[] bundles = bundleGroups[j] == null ? new Bundle[0] : bundleGroups[j]
		                    .getBundles();
		            for (int k = 0; k < bundles.length; k++) {
		            	bundlesMap.put(bundles[k].getSymbolicName(), bundles[k].getState());
		            }
		        }
		    }		    		    
		}		
	}
	
	@Override
	public void run() {
		int newState;
		StringBuffer text = new StringBuffer("");
		IBundleGroupProvider[] providers = Platform.getBundleGroupProviders();
		if (providers != null) {
		    for (int i = 0; i < providers.length; i++) {
		        IBundleGroup[] bundleGroups = providers[i].getBundleGroups();
		        for (int j = 0; j < bundleGroups.length; j++) {
		            Bundle[] bundles = bundleGroups[j] == null ? new Bundle[0] : bundleGroups[j]
		                    .getBundles();
		            for (int k = 0; k < bundles.length; k++) {
		            	newState = bundles[k].getState();
		            	if(!bundlesMap.containsKey(bundles[k].getSymbolicName())){
		            		text.append("New plug-in: "+bundles[k].getSymbolicName() 
		            				+" - State: "+StateSymbols.getStateName(bundles[k].getState())+" \n");
		            		bundlesMap.put(bundles[k].getSymbolicName(), bundles[k].getState());
		            	}
		            	else{
		            		int oldState = bundlesMap.get(bundles[k].getSymbolicName());
		            		if(oldState != newState){
			            		text.append("Plug-in "+bundles[k].getSymbolicName()+" changed state from " 
			            				+ StateSymbols.getStateName(oldState) + 
			            				" to "+StateSymbols.getStateName(newState)+" \n");
			            		bundlesMap.put(bundles[k].getSymbolicName(), bundles[k].getState());
		            		}
		            	}
		            }
		        }
		    }		    		    
		}
		TextRecorder.record(new PluginMonitor(text));
	}

}
