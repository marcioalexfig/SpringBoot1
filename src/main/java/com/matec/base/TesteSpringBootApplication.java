
package com.matec.base;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.type.filter.RegexPatternTypeFilter;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.matec.base.modelo.Empresa;
import com.matec.base.modelo.Usuario;
import com.matec.base.modelo.enums.PerfilEnum;
import com.matec.base.repositorio.EmpresaRepository;
import com.matec.base.repositorio.UsuarioRepository;
import com.matec.base.security.SenhaUtils;
import com.matec.base.servico.EmpresaService;

@SpringBootApplication
public class TesteSpringBootApplication {
	/*
	@Autowired //Chama Repository para testar emprena no banco
	private EmpresaRepository empresaRepository;
	
	@Autowired //Carrega o serviço empresa para testar algo relativo a esta classe
	private EmpresaService empresaService;
	*/
	@Autowired //Chama Repository para testar usuario no banco
	private UsuarioRepository usuarioRepository;

	public static void main(String[] args) {
		System.out.println("####################  ----  inicio   -----   ######################");
		SpringApplication.run(TesteSpringBootApplication.class, args);
		System.out.println("####################  ----  fim   -----   ######################");
		
	}
	
	/**
	 * Executa uma simples teste para verificar se a aplicação está rodando
	@Bean
	public CommandLineRunner rodarServico () {
		return args -> {
			this.empresaService.metodoTeste();
		};
	}
	*/
	
	
	// - Testa o Banco de Dados MySql ou H2, conforme estiver no aplication.properties
	/*
	@Bean
	public CommandLineRunner rodar() {
		return args -> {
			Empresa empresa = new Empresa();
			empresa.setNome("MaTec");
			empresa.setCnpj("2312331231230001");
			empresa.setEndereco("Rua blablabla");
			empresa.setTelefone("974234234");
			this.empresaRepository.save(empresa);
			
			Empresa empresa2 = new Empresa();
			empresa2.setNome("Enterprise");
			empresa2.setCnpj("735631231230001");
			empresa2.setEndereco("Rua lalala");
			empresa2.setTelefone("543234234");
			this.empresaRepository.save(empresa2);
			
			List<Empresa> listaEmpresa = (ArrayList<Empresa>) this.empresaRepository.findAll();
			listaEmpresa.forEach(System.out::println);
			
			Empresa empresaPorNome = this.empresaRepository.findByNome("MaTec");
			System.out.println("Busca empresa por nome:" + empresaPorNome.toString());
		};
	}
	*/
	/* Adiciona 2 usuarios
	@Bean
	public CommandLineRunner rodarCadastroUsuarios () {
		return args -> {
			Usuario usu1 = new Usuario();
			usu1.setEmail("usu1@usu1.com");
			usu1.setNome("Usuario 1 - Nao Adm");
			usu1.setSenha(SenhaUtils.gerarBCript("123456"));
			usu1.setPerfil(PerfilEnum.ROLE_USUARIO);
			usuarioRepository.save(usu1);
			
			Usuario usu2 = new Usuario();
			usu2.setEmail("usu2@usu2.com");
			usu2.setNome("Usuario 2 - Adm");
			usu2.setSenha(SenhaUtils.gerarBCript("123456"));
			usu2.setPerfil(PerfilEnum.ROLE_ADMIN);
			usuarioRepository.save(usu2);
		};
	}
	*/
	
	
}
