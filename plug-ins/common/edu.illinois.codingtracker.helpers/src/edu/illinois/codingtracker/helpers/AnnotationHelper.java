package edu.illinois.codingtracker.helpers;

import java.util.ArrayList;
import org.eclipse.jface.text.source.Annotation;




/**
 * 
 * @author Joffre Yagual
 * 
 */
public class AnnotationHelper {


	public static ArrayList<Annotation> getAnnotationMatchesbyType(Annotation [] annotationArray, String type)
	{
		ArrayList<Annotation> annotationMatches = new ArrayList<Annotation>();
		for (int i = 0; i < annotationArray.length; i++) {
			if (annotationArray[i].getType().matches(type))
			{
				annotationMatches.add(annotationArray[i]);
			}
		}
		return annotationMatches;
	}

	public static Annotation getLastAnnotationMatchesbyType(Annotation [] annotationArray, String type)
	{
		Annotation last = null;
		for (int i = 0; i < annotationArray.length; i++) {
			if (annotationArray[i].getType().matches(type))
			{
				last = annotationArray[i];
			}
		}
		return last;
	}
}
