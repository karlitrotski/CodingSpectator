package edu.illinois.codingtracker.operations.pluginusage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TimerTask;
import java.util.TreeMap;

import org.eclipse.core.runtime.IBundleGroup;
import org.eclipse.core.runtime.IBundleGroupProvider;
import org.eclipse.core.runtime.Platform;
import org.osgi.framework.Bundle;

import edu.illinois.codingtracker.operations.starts.PluginsList;

public class MonitorTask extends TimerTask {
	
	TreeMap<String, Integer> bundlesMap = new TreeMap<String, Integer>(String.CASE_INSENSITIVE_ORDER);
	public StringBuffer text = new StringBuffer("");
	
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
		int numstate;
		IBundleGroupProvider[] providers = Platform.getBundleGroupProviders();
		if (providers != null) {
		    for (int i = 0; i < providers.length; i++) {
		        IBundleGroup[] bundleGroups = providers[i].getBundleGroups();
		        for (int j = 0; j < bundleGroups.length; j++) {
		            Bundle[] bundles = bundleGroups[j] == null ? new Bundle[0] : bundleGroups[j]
		                    .getBundles();
		            for (int k = 0; k < bundles.length; k++) {
		            	numstate = bundles[k].getState();
		            	if(bundlesMap.get(bundles[k].getSymbolicName()) == null){
		            		text.append("New plug-in: "+bundles[k].getSymbolicName()+" - State: "+StateSymbols.getStateName(bundles[k].getState())+"\n");
		            	}
		            	else if(bundlesMap.get(bundles[k].getSymbolicName()) != numstate){
		            		text.append("Plug-in "+bundles[k].getSymbolicName()+" changed state from "+StateSymbols.getStateName(bundlesMap.get(bundles[k].getSymbolicName()))+" to "+StateSymbols.getStateName(numstate)+"\n");
		            	}
		            }
		        }
		    }		    		    
		}
	}

}
