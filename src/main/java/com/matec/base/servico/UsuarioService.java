package com.matec.base.servico;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matec.base.dto.UsuarioDTO;
import com.matec.base.modelo.Usuario;
import com.matec.base.modelo.enums.PerfilEnum;
import com.matec.base.repositorio.UsuarioRepository;

@Service
public interface UsuarioService {
	
	public Optional<Usuario> buscaUsuarioPorEmail(String email);
	
	public Usuario cadastrarUsuario(UsuarioDTO usuarioDto);
	
}
