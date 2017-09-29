package com.exemplo.teste.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.MapKeyTemporal;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.exemplo.teste.modelo.Usuario;
import com.exemplo.teste.modelo.enums.PerfilEnum;

public class JwtUsuarioFactory {

	private JwtUsuarioFactory() {}
	
	/**
	 * cria um usuario JWT a partir do modelo e já com as authorities 
	 * @param usuario
	 * @return
	 */
	public static JwtUsuario create(Usuario usuario) {
		return new JwtUsuario(
				usuario.getId(),
				usuario.getEmail(), 
				usuario.getSenha(),
				mapToGrantedAuthorities(usuario.getPerfil())
				);
	}

	/**
	 * Adiciona as authorties com as roles do enum, conforme exigido pelo spring security
	 * @param perfil
	 * @return
	 */
	private static Collection<? extends GrantedAuthority> mapToGrantedAuthorities(PerfilEnum perfil) {
		List<GrantedAuthority> authority = new ArrayList<>();
		authority.add(new SimpleGrantedAuthority(perfil.toString()));
		return authority;
	}
	
	
}
