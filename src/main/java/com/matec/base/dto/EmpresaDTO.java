package com.matec.base.dto;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class EmpresaDTO {
	private String nome;
	private String cnpj;
	private String telefone;
	private String endereco;
	
	@NotEmpty (message = "Nome necessita ser preenchido!")
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	
	@Length(min=8, max=11, message = "Telefone deve possuir de 8 a 11 caracteres!")
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	@Override
	public String toString() {
		return "EmpresaDTO [nome=" + nome + ", cnpj=" + cnpj + ", telefone=" + telefone + ", endereco=" + endereco
				+ "]";
	}
	
	
	

}
