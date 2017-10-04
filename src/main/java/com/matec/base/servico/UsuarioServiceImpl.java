package com.matec.base.servico;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matec.base.dto.UsuarioDTO;
import com.matec.base.modelo.Usuario;
import com.matec.base.repositorio.UsuarioRepository;

@Service
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
		usuario.setPerfil(usuarioDto.getPerfil());
		usuario.setSenha(usuarioDto.getSenha());
		
		Usuario usuarioCadastrado = usuarioRepositorio.save(usuario);
		
		return usuarioCadastrado;
	}
	
}
