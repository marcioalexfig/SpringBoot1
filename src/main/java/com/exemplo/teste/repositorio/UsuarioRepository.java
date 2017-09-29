package com.exemplo.teste.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exemplo.teste.modelo.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	List<Usuario> findByNome(String nome);
	Usuario findByEmail(String email);
	
}
