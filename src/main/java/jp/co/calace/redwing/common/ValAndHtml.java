package jp.co.calace.redwing.common;

/*
 * option tags and checkbox..etc
 * tag name and value <xx> ~ </xx> generic holder ?
 */
public class ValAndHtml {

	private String optValue;
	private String optInnerHTML;
	
	
	//constructor 
	public ValAndHtml(String optValue, String optInnerHTML) {
		super();
		this.optValue = optValue;
		this.optInnerHTML = optInnerHTML;
	}
	
	//setters & getters 
	
	public String getOptInnerHTML() {
		return optInnerHTML;
	}
	public void setOptName(String optLabel) {
		this.optInnerHTML = optLabel;
	}
	//tagy  why different name ?
	public String getOptValue() {
		return optValue;
	}
	public void setOptValue(String optValue) {
		this.optValue = optValue;
	}
	
}
