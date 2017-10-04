package com.matec.base.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class SenhaUtils {

	
	/**
	 * Gerar senha encriptada
	 * @param senha
	 * @return
	 */
	public static String gerarBCript(String senha) {
		
		if (senha==null) {
			return senha;
		}
		
		BCryptPasswordEncoder bcript = new BCryptPasswordEncoder();
		
		return bcript.encode(senha);
	}
	
	public static boolean senhaValida(String senha, String senhaBCript) {
		BCryptPasswordEncoder bcript = new BCryptPasswordEncoder();
		
		return bcript.matches(senha,  senhaBCript);
		
	}
	
}
