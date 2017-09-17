package com.example.teste;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.exemple.teste.modelo.Empresa;
import com.exemple.teste.repositorio.EmpresaRepository;

@SpringBootApplication
public class TesteSpringBootApplication {
	
	@Autowired
	private EmpresaRepository empresaRepository;

	public static void main(String[] args) {
		SpringApplication.run(TesteSpringBootApplication.class, args);
		System.out.println("teste");
		
	}
	
	@Bean
	public CommandLineRunner commandLineRunner() {
		return args -> {
			Empresa empresa = new Empresa();
			empresa.setNome("MaTec");
			
			this.empresaRepository.save(empresa);
			
			List<Empresa> listaEmpresa = empresaRepository.findAll();
			listaEmpresa.forEach(System.out::println);
			
			Empresa empresaPorNome = empresaRepository.findByNome("MaTec");
			System.out.println("Busca empresa por nome:" + empresaPorNome.toString());
			
		};
	}
	
}
