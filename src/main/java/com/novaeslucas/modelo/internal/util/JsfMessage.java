package com.novaeslucas.modelo.internal.util;

import com.novaeslucas.modelo.internal.message.Message;
import com.novaeslucas.modelo.internal.message.SeverityType;

public class JsfMessage implements Message {

	private static final long serialVersionUID = 1L;

	private String text;
	private SeverityType severity;
	

	public JsfMessage(String text, SeverityType severity){
		this.text = text;
		this.severity = severity;
	}
	
	public String getText() {
		return this.text;
	}

	public SeverityType getSeverity() {
		return this.severity;
	}

}
