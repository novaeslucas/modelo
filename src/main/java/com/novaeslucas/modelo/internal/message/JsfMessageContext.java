package com.novaeslucas.modelo.internal.message;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Default;

@Default
@SessionScoped
public class JsfMessageContext implements MessageContext {

	private static final long serialVersionUID = 1L;
	
	private List<Message> messages = new ArrayList<Message>();
	
	@Override
	public void add(Message message) {
		messages.add(message);
	}

	@Override
	public void add(String text, SeverityType severity) {
		Message message = new JsfMessage(text, severity);
		add(message);
	}

	@Override
	public List<Message> getMessages() {
		return this.messages;
	}

	@Override
	public void clear() {
		messages.clear();
	}

}
