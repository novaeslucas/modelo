package com.novaeslucas.modelo.internal.exception;

public class InternalException extends RuntimeException {

	private static final long serialVersionUID = -254671297150462552L;

	public InternalException(String message){
		super(message);
	}
	
	public InternalException(String message, Throwable cause){
		super(message, cause);
	}
	
}
