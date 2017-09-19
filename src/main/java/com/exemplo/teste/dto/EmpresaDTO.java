package com.exemplo.teste.dto;

public class EmpresaDTO {
	private String nome;
	private String cnpj;
	private String telefone;
	private String endereco;
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
