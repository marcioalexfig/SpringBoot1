package com.matec.base.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.matec.base.modelo.Usuario;
import com.matec.base.servico.UsuarioService;
import com.matec.base.servico.UsuarioServiceImpl;

@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	UsuarioService usuarioService;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		Optional<Usuario> funcionario = usuarioService.buscaUsuarioPorEmail(email);
		
		if (funcionario.isPresent()) {
			return JwtUsuarioFactory.create(funcionario.get());
		}
		
		
		throw new UsernameNotFoundException("Usuatio não encontrado!");
	}

	
	
	
}
