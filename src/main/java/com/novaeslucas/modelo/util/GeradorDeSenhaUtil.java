package com.novaeslucas.modelo.util;

import java.io.Serializable;
import java.security.MessageDigest;
import java.util.Date;
import java.util.Random;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class GeradorDeSenhaUtil implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public String gerarSenha(){
		String[] opcoes = {"A","B", "C", "D", "E", "F", "G", "H", "I", "J", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "X", "Z", "0",
				"1", "2", "3", "4", "5", "6", "7", "8", "9" };
		
		Random random = new Random(new Date().getTime());
		
		StringBuilder senha = new StringBuilder();
		for (int i = 0; i < 6; i++) {
			senha.append(opcoes[random.nextInt(opcoes.length - 1)]);
		}
		return senha.toString();
	}
	
	public String gerarMD5(String senha){
		try{
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(senha.toString().getBytes());
			byte[] hashMd5 = md5.digest();
			
			StringBuilder senhaMD5 = new StringBuilder();
		   for (int i = 0; i < hashMd5.length; i++) {
		       int parteAlta = ((hashMd5[i] >> 4) & 0xf) << 4;
		       int parteBaixa = hashMd5[i] & 0xf;
		       if (parteAlta == 0) senhaMD5.append('0');
		       senhaMD5.append(Integer.toHexString(parteAlta | parteBaixa));
		   }
		   return senhaMD5.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}	
	
}
