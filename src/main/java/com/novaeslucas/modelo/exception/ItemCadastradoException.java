package com.novaeslucas.modelo.exception;

import javax.ejb.ApplicationException;

@ApplicationException(rollback=true)
public class ItemCadastradoException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public ItemCadastradoException(){
		super("N�o foi poss�vel concluir o cadastro. O registro j� existe.");
	}

}
