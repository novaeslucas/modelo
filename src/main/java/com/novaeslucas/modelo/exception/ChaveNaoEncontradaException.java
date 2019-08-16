package com.novaeslucas.modelo.exception;

import javax.ejb.ApplicationException;

@ApplicationException(rollback=true)
public class ChaveNaoEncontradaException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	public ChaveNaoEncontradaException(){
		super("Ocorreu um erro ao gerar a senha.");
	}

}
