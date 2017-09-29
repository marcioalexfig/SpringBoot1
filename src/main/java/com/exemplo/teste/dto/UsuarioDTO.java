package com.exemplo.teste.dto;

public class UsuarioDTO {
	
	private String nome;
	private String email;
	private Integer perfil;
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getPerfil() {
		return perfil;
	}
	public void setPerfil(Integer perfil) {
		this.perfil = perfil;
	}
	@Override
	public String toString() {
		return "UsuarioDTO [nome=" + nome + ", email=" + email + "]";
	}
	

}
