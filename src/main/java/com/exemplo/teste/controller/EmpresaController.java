package com.exemplo.teste.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exemplo.teste.dto.EmpresaDTO;
import com.exemplo.teste.modelo.Empresa;
import com.exemplo.teste.response.Response;
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
	public EmpresaDTO retornaEmpresaPorNome(@PathVariable("nome") String nome) {
		EmpresaDTO empresaDTO = new EmpresaDTO();
		Empresa empresa = empresaService.retornaEmpresaPorNome(nome);
		empresaDTO.setNome(empresa.getNome());
		return empresaDTO;
	}
	
	@PostMapping	
	/**
	 * @Valid habilita as validações declaradas no DTO
	 * @param empresaDto
	 * @param resultadoValidacao  é o retorno com as mensagens de erro das validação de dentro do DTO
	 * @return
	 */
	public ResponseEntity<Response<EmpresaDTO>> cadastrarEmpresa(@Valid @RequestBody EmpresaDTO empresaDto, BindingResult resultadoValidacao)	{

		Response<EmpresaDTO> resposta = new Response<EmpresaDTO>();
		List<String> erros = new ArrayList<String>();
		
		try {
			//verifica erros de validação e adiciona o que tiver no response
			if (!resultadoValidacao.getAllErrors().isEmpty()) {
				
				resultadoValidacao.getAllErrors().forEach(erro -> resposta.getErros().add(erro.getDefaultMessage()));
				
				//para tudo e retorna os erros
				return ResponseEntity.badRequest().body(resposta);
			} 
			//se não tem erros, chama o serviço e grava na base de dados
			Empresa empresaCadastrada = empresaService.cadastrarEmpresa(empresaDto);
			empresaDto.setNome(empresaCadastrada.getNome());
			empresaDto.setCnpj(empresaCadastrada.getCnpj());
			empresaDto.setEndereco(empresaCadastrada.getEndereco());
			empresaDto.setTelefone(empresaCadastrada.getTelefone());
			resposta.setDados(empresaDto);
			
		} catch (Exception e) {
			erros.add(e.getLocalizedMessage());
			erros.forEach(erro -> resposta.getErros().add(erro));
			//para tudo e retorna os erros
			return ResponseEntity.badRequest().body(resposta);
		}

		return ResponseEntity.ok(resposta);
		
	}

}
