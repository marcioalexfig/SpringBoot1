package com.exemplo.teste.servico;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.exemplo.teste.dto.UsuarioDTO;
import com.exemplo.teste.modelo.Usuario;
import com.exemplo.teste.repositorio.UsuarioRepository;

public class UsuarioServiceImpl implements UsuarioService{

	@Autowired
	UsuarioRepository usuarioRepositorio;
	
	/**
	 * 
	 * @param email
	 * @return
	 */
	public Optional<Usuario> buscaUsuarioPorEmail(String email) {
		
		return Optional.ofNullable(usuarioRepositorio.findByEmail(email));
		
	}
	
	/**
	 * 
	 */
	public Usuario cadastrarUsuario(UsuarioDTO usuarioDto) {
		Usuario usuario = new Usuario();
		usuario.setEmail(usuarioDto.getEmail());
		usuario.setNome(usuarioDto.getNome());
		//usuario.setPerfil();
		
		Usuario usuarioCadastrado = usuarioRepositorio.save(usuario);
		
		return usuarioCadastrado;
	}
	
}
