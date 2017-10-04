package com.matec.base.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.matec.base.dto.EmpresaDTO;
import com.matec.base.modelo.Empresa;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Integer>{

	Empresa findByNome(String nome);
	
}
