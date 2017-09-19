package com.exemplo.teste.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.exemplo.teste.dto.EmpresaDTO;
import com.exemplo.teste.modelo.Empresa;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Integer>{

	Empresa findByNome(String nome);
	
}
