package com.novaeslucas.modelo.exception;

import javax.ejb.ApplicationException;

@ApplicationException(rollback=true)
public class LoginExistenteException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public LoginExistenteException(){
		super("Login já cadastrado no sistema. Por favor, escolher outro login.");
	}
	
}
