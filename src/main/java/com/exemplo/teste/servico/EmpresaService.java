package com.exemplo.teste.servico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exemplo.teste.dto.EmpresaDTO;
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
	
	/**
	 * Cadastrar empresa e retornar DTO para o Json/Rest
	 * @param empresa
	 * @return
	 */
	public Empresa cadastrarEmpresa(EmpresaDTO empresaDTO) {
		//empresa a ser cadastrada
		Empresa empresa = new Empresa();
		
		//preenchendo objeto com dados do parametro
		empresa.setCnpj(empresaDTO.getCnpj());
		empresa.setNome(empresaDTO.getNome());
		empresa.setTelefone(empresaDTO.getTelefone());
		empresa.setTelefone(empresaDTO.getTelefone());
		
		//retorno do cadastro
		Empresa empresaCadastrada = empresaRepository.save(empresa);
			
		return empresaCadastrada;
		
	}
	
}
