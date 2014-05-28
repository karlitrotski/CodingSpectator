package edu.illinois.codingtracker.operations.pluginusage;

/**
 * Integer representation of plug-ins state.
 * 
 * @author Sebastian Sanchez
 * @author Carlos Dettoni
 * 
 */
public abstract class StateSymbols {

	public static final int UNINSTALLED= 1;
	public static final int INSTALLED= 2;
	public static final int RESOLVED= 4;
	public static final int STARTING= 8;
	public static final int STOPPING= 16;
	public static final int ACTIVE= 32;
	
	
	/*
	 * Class to get State's name
	 * 
	 * @param state integer representation of the state
	 * @return String representation of the state
	 */
	static public String getStateName(int state){
		switch (state) {
			case UNINSTALLED:
				return "Uninstalled";
			case INSTALLED:
				return "Installed";
			case RESOLVED:
				return "Resolved";
			case STARTING:
				return "Starting";
			case STOPPING:
				return "Stopping";
			case ACTIVE:
				return "Active";
			default:
				return "Unknown state";
		}
		
	}
}
