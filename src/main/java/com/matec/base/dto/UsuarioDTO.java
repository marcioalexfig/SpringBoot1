package com.matec.base.dto;

import com.matec.base.modelo.enums.PerfilEnum;

public class UsuarioDTO {
	
	private String nome;
	private String email;
	private PerfilEnum perfil;
	private String senha;
	
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
	public PerfilEnum getPerfil() {
		return perfil;
	}
	public void setPerfil(PerfilEnum perfil) {
		this.perfil = perfil;
	}
	@Override
	public String toString() {
		return "UsuarioDTO [nome=" + nome + ", email=" + email + "]";
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}

	

}
