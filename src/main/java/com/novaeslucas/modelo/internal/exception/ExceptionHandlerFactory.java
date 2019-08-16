package com.novaeslucas.modelo.internal.exception;

import java.io.Serializable;

public class ExceptionHandlerFactory extends javax.faces.context.ExceptionHandlerFactory implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private javax.faces.context.ExceptionHandlerFactory exceptionHandlerFactory;

	public ExceptionHandlerFactory(final javax.faces.context.ExceptionHandlerFactory exceptionHandlerFactory) {
		this.exceptionHandlerFactory = exceptionHandlerFactory;
	}
	
	@Override
	public javax.faces.context.ExceptionHandler getExceptionHandler() {
		javax.faces.context.ExceptionHandler result = this.exceptionHandlerFactory.getExceptionHandler();
		result = new ExceptionHandler(result);

		return result;
	}
	
	

	
	
}
