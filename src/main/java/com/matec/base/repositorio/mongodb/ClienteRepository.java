package com.matec.base.repositorio.mongodb;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.matec.base.modelo.mongodb.Cliente;

//Id no mongoDB é um hash, por isso é uma string
public interface ClienteRepository extends MongoRepository<Cliente, String>{

	Cliente findByNome(String nome);
	
	@Query ("{ 'idade' : { $gt: ?0, $lt: ?1 } }")
	List<Cliente> findByIdadeBetween (int idadeInicial, int idadeFinal);
	
}
