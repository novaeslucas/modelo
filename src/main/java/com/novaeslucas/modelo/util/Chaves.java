package com.novaeslucas.modelo.util;

public enum Chaves {
	EMAIL_CADASTRO_USUARIO(0),
	EMAIL_RESULTADO_AVALIACAO(1),
	EMAIL_ESQUECI_MINHA_SENHA(2),
	EMAIL_CONFIRMACAO_NOVA_SENHA(3);
	
	private int tipoEmail;
	
	Chaves(int tipoEmail){
		this.tipoEmail = tipoEmail; 
	}
	
	public int getTipoEmail(){
		return this.tipoEmail;
	}
}
