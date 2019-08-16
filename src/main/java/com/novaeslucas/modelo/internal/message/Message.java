package com.novaeslucas.modelo.internal.message;

import java.io.Serializable;

public interface Message extends Serializable {

	String getText();
	SeverityType getSeverity();
	
}
