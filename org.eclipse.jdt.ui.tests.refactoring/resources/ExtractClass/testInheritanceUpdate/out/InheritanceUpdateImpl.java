/*******************************************************************************
 * Copyright (c) 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package p;

public class InheritanceUpdateImpl extends InheritanceUpdate{
	protected String test="Test";
	public void foo() {
		System.out.println("Test:"+test+" Super:"+super.parameterObject.test);
		parameterObject.test2++;
	}
}