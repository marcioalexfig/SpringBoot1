package com.exemplo.teste.servico;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exemplo.teste.dto.UsuarioDTO;
import com.exemplo.teste.modelo.Usuario;
import com.exemplo.teste.modelo.enums.PerfilEnum;
import com.exemplo.teste.repositorio.UsuarioRepository;

@Service
public interface UsuarioService {
	
	public Optional<Usuario> buscaUsuarioPorEmail(String email);
	
	public Usuario cadastrarUsuario(UsuarioDTO usuarioDto);
	
}
