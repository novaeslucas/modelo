package com.novaeslucas.modelo.internal.exception;

public class ConfigurationException extends RuntimeException {

	private static final long serialVersionUID = 3592320668923133574L;
		
	public ConfigurationException(String message){
		super(message);
	}
	
	public ConfigurationException(String message, Throwable cause){
		super(message, cause);
	}

}
