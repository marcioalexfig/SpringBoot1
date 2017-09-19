package com.exemplo.teste.servico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exemplo.teste.modelo.Empresa;
import com.exemplo.teste.repositorio.EmpresaRepository;

@Service
public class EmpresaService {

	@Autowired
	EmpresaRepository empresaRepository;
	
	public void metodoTeste() {
		System.out.println("#####  Metodo de Teste  #####");
	}
	/**
	 * busca na base de dados
	 * @param nome
	 * @return
	 */
	public Empresa retornaEmpresaPorNome (String nome) {
		Empresa e = empresaRepository.findByNome(nome);
		return e;
	}
	
}
