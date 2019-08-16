package com.novaeslucas.modelo.internal.message;

import java.io.Serializable;
import java.util.List;


public interface MessageContext extends Serializable {

	void add(Message message);
	void add(String text, SeverityType severity);
	List<Message> getMessages();
	void clear();
	
}
