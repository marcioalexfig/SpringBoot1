
package com.exemplo.teste;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.exemplo.teste.modelo.Empresa;
import com.exemplo.teste.repositorio.EmpresaRepository;
import com.exemplo.teste.servico.EmpresaService;

@SpringBootApplication
public class TesteSpringBootApplication {
	
	@Autowired //Chama Repository para testar banco
	private EmpresaRepository empresaRepository;
	
	@Autowired
	private EmpresaService empresaService;

	public static void main(String[] args) {
		System.out.println("####################  ----  inicio   -----   ######################");
		SpringApplication.run(TesteSpringBootApplication.class, args);
		System.out.println("####################  ----  fim   -----   ######################");
		
	}
	
	
	@Bean
	public CommandLineRunner rodarServico () {
		return args -> {
			this.empresaService.metodoTeste();
		};
	}
	
	
	// - Testa o Banco de Dados MySql ou H2, conforme estiver no aplication.properties
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
	
	
	
}
