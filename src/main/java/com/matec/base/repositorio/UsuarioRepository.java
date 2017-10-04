package com.matec.base.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.matec.base.modelo.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	List<Usuario> findByNome(String nome);
	Usuario findByEmail(String email);
	
}
