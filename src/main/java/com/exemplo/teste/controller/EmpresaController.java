package com.exemplo.teste.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exemplo.teste.servico.EmpresaService;

@RestController
@RequestMapping("/api/empresa")
public class EmpresaController {
	
	@Autowired
	private EmpresaService empresaService;
	
	/**
	 * busca no Service por nome
	 * @param nome
	 * @return
	 */
	@GetMapping(value = "/{nome}")
	public String retornaEmpresaPorNome(@PathVariable("nome") String nome) {
		return "Olá " + empresaService.retornaEmpresaPorNome(nome);
	}

}
