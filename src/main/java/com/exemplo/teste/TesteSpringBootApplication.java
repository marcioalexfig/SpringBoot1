
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

@SpringBootApplication
public class TesteSpringBootApplication {
	
	@Autowired
	private EmpresaRepository empresaRepository;

	public static void main(String[] args) {
		System.out.println("####################  ----  inicio   -----   ######################");
		SpringApplication.run(TesteSpringBootApplication.class, args);
		System.out.println("####################  ----  fim   -----   ######################");
		
	}
	
	
	@Bean
	public CommandLineRunner rodar() {
		return args -> {
			Empresa empresa = new Empresa();
			empresa.setNome("MaTec");
			
			this.empresaRepository.save(empresa);
			
			List<Empresa> listaEmpresa = (ArrayList<Empresa>) this.empresaRepository.findAll();
			listaEmpresa.forEach(System.out::println);
			
			Empresa empresaPorNome = this.empresaRepository.findByNome("MaTec");
			System.out.println("Busca empresa por nome:" + empresaPorNome.toString());
			
		};
	}
	
	
}
