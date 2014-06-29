/**
 * This file is licensed under the University of Illinois/NCSA Open Source License. See LICENSE.TXT for details.
 */
package edu.illinois.codingtracker.operations;

/**
 * 
 * @author Stas Negara
 * 
 * @author Juraj Kubelka
 * @author Catalina Espinoza Inaipil - we added append(int [] values), append(String[] messages).
 */
public class OperationTextChunk implements CharSequence {

	private final StringBuffer text;

	public OperationTextChunk(char operationSymbol) {
		text= new StringBuffer();
		text.append(operationSymbol);
	}

	public OperationTextChunk() {
		text=new StringBuffer();
	}

	public void append(Object obj) {
		append(obj.toString());
	}

	public void append(String str) {
		if (str == null) {
			str= "";
		}
		text.append(OperationLexer.escapeString(str)).append(OperationLexer.DELIMETER_SYMBOL);
	}
	
	public void append(int num) {
		text.append(num).append(OperationLexer.DELIMETER_SYMBOL);
	}
	
	public void append(int [] values) {
		this.append(values.length);
		for(int value: values) {
			this.append(value);
		}
	}
	
	public void append(String[] messages) {
		this.append(messages.length);
		for(String message: messages) {
			this.append(message);
		}
	}
	
	public void append(long num) {
		text.append(num).append(OperationLexer.DELIMETER_SYMBOL);
	}

	public void append(boolean val) {
		append(val ? 1 : 0);
	}

	public void concat(String str) {
		if (str == null) {
			str= "";
		}
		text.append(OperationLexer.escapeString(str));
	}

	@Override
	public char charAt(int index) {
		return text.charAt(index);
	}

	@Override
	public int length() {
		return text.length();
	}

	@Override
	public CharSequence subSequence(int start, int end) {
		return text.subSequence(start, end);
	}

	@Override
	public String toString() {
		return text.toString();
	}

}
