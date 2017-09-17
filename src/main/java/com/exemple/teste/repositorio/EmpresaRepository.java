package com.exemple.teste.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import com.exemple.teste.modelo.Empresa;

public interface EmpresaRepository extends JpaRepository<Empresa, Integer>{

	Empresa findByNome(String nome);
	
}
