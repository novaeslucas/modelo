package com.novaeslucas.modelo.internal.message;


public class JsfMessage implements Message {

	private static final long serialVersionUID = 1L;

	private String text;
	private SeverityType severity;
	

	public JsfMessage(String text, SeverityType severity){
		this.text = text;
		this.severity = severity;
	}
	
	@Override
	public String getText() {
		return this.text;
	}

	@Override
	public SeverityType getSeverity() {
		return this.severity;
	}

}
