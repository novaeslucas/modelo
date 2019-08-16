package com.novaeslucas.modelo.util;

import java.io.Serializable;
import java.util.Date;
import java.util.Random;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AppUtil implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public synchronized static String criarChave(){
		StringBuilder chave = new StringBuilder();
		try{
			String[] caracters = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "x", "z", "0", 
					"1", "2", "3", "4", "5", "6", "7", "8", "9"};
			Random random = new Random(new Date().getTime());
			
			for (int i = 0; i < 20; i++) {
				int index = random.nextInt(caracters.length);
				chave.append(caracters[index]);
			}
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return chave.toString();
	}
	
}
