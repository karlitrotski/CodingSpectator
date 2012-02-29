/**
 * This file is licensed under the University of Illinois/NCSA Open Source License. See LICENSE.TXT for details.
 */
package edu.illinois.codingtracker.tests.postprocessors.ast.refactoring.properties;



/**
 * This class represents a deleted declaration of a variable.
 * 
 * @author Stas Negara
 * 
 */
public class DeletedVariableDeclarationRefactoringProperty extends RefactoringProperty {


	public DeletedVariableDeclarationRefactoringProperty(String entityName) {
		addAttribute(RefactoringPropertyAttributes.ENTITY_NAME, entityName);
	}

}