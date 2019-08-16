package com.novaeslucas.modelo.exception;

import javax.ejb.ApplicationException;

@ApplicationException(rollback=true)
public class ItemComRelacionamentoException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public ItemComRelacionamentoException(){
		super("N\u00E3o foi poss\u00EDvel remover o item. Remova primeiro os registros relacionados.");
	}

}
