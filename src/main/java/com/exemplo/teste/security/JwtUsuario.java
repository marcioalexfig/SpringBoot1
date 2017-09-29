package com.exemplo.teste.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class JwtUsuario implements UserDetails {

	
	private Long id;
	private String username;
	private String password;
	private Collection<? extends GrantedAuthority> authoryties;
	
	
	
	@Override
	public String toString() {
		return "JwtUsuario [id=" + id + ", username=" + username + ", password=" + password + ", authoryties="
				+ authoryties + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Collection<? extends GrantedAuthority> getAuthoryties() {
		return authoryties;
	}

	public void setAuthoryties(Collection<? extends GrantedAuthority> authoryties) {
		this.authoryties = authoryties;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public JwtUsuario(Long id, String username, String password, Collection<? extends GrantedAuthority> authoryties) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.authoryties = authoryties;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 5600280362226359357L;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}



}
