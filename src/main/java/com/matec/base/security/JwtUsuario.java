package com.matec.base.security;

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
		return authoryties;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}



}
