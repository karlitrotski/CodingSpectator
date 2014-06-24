/**
 * This file is licensed under the University of Illinois/NCSA Open Source License. See LICENSE.TXT for details.
 */
package edu.illinois.codingtracker.operations;

import java.io.UnsupportedEncodingException;

/**
 * @author matpardo, dmunozl
 *
 */
public class OperationXMLTextChunk implements CharSequence {
	
	private final StringBuffer text;
	private final String xmlversion="1.0";
	private final String encoding="UTF-8";
	private final String pluginVersion;
	
	public OperationXMLTextChunk() {
		text = new StringBuffer();
		pluginVersion = "";
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
	
	public void concat(String str) {
		if (str == null) {
			str= "";
		}
		text.append(encode(str));
	}
	
	public void appendXMLStart(){
		String input = "<?xml version=" + "\"" + xmlversion + "\""
				+ " encoding=" + "\"" + encoding + "\""  
				+ " pluginversion=" + "\"" + pluginVersion + "\"" + "?>";
		this.concat(input);
	}
	
	private String encode(String s){
		try {
			return new String(s.getBytes(encoding), encoding);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return "ENCODING_ERROR";
		}
	}
	
	public void appendStartRoot(UserOperation userOperation){
		String input= "" + openTag(userOperation.getClass().getName())+"\n";
		this.concat(input);
	}
	
	private String escapeXMLString(String s){
		String escaping = s;
		escaping = escaping.replace("&", "&amp");
		escaping = escaping.replace("<", "&lt");
		escaping = escaping.replace(">", "&gt");
		escaping = escaping.replace("'", "&apos");
		escaping = escaping.replace("\"", "&quot");
		return escaping;
	}
	
	public void appendChild(String atributeName, int num) {
		this.appendChild(atributeName, "" + num);
	}
	
	public void appendChild(String atributeName, long num) {
		this.appendChild(atributeName, "" + num);
	}

	public void appendChild(String atributeName, boolean val) {
		this.appendChild(atributeName, val ? 1 : 0);
	}
	
	public void appendChild(String atributeName, String atributeValue){
		String input="\t" + openTag(atributeName) + 
				escapeXMLString(atributeValue) + 
				closeTag(atributeName) + "\n";
		this.concat(input);
	}
	
	public void appendCloseRoot(UserOperation userOperation){
		this.appendChild("timestamp", "" + userOperation.getTime());
		String input = "" + closeTag(userOperation.getClass().getName())+"\n";
		this.concat(input);
	}
	
	private String openTag(String s){
		return "<"+s+">";
	}
	
	private String closeTag(String s){
		return "</"+s+">";
	}

}
