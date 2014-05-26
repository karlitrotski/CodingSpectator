
package edu.illinois.codingtracker.listeners;

import org.eclipse.core.resources.IMarkerDelta;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.ResourcesPlugin;

/**
 * 
 * @author Joffre Yagual
 * 
 */
public class ResourceChangeListener extends BasicListener implements IResourceChangeListener {

	private static ResourceChangeListener instance = null;

	public static ResourceChangeListener getInstance() {
		if (instance == null) {
			instance = new ResourceChangeListener();
		}

		return instance;
	}

	private ResourceChangeListener() {
		super();
	}

	public static void register()
	{		
		ResourcesPlugin.getWorkspace().addResourceChangeListener(
				getInstance(), IResourceChangeEvent.POST_CHANGE);			
	}
	

	@Override
	public void resourceChanged(IResourceChangeEvent event) {
		  IMarkerDelta [] deltas;
		  if(event.getType() == IResourceChangeEvent.POST_CHANGE){		  
			  deltas = event.findMarkerDeltas("org.eclipse.jdt.core.problem", false);
			  if (deltas.length > 0){
				  operationRecorder.recordMarkersStatus(deltas);
			  }
		  }
	}
}
