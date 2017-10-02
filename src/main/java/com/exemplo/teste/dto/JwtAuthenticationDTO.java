package com.exemplo.teste.dto;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class JwtAuthenticationDTO {

	private String email;
	private String senha;
	
	@NotEmpty (message = "O campo e-mail não pode ser vazio!")
	@Email (message = "Digite um e-mail válido")
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@NotEmpty (message = "O campo senha não pode ser vazio!")
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public JwtAuthenticationDTO(String email, String senha) {
		super();
		this.email = email;
		this.senha = senha;
	}
	public JwtAuthenticationDTO() {	}
	
	
	
}
