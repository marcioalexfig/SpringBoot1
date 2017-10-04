package com.matec.base.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.hibernate.loader.GeneratedCollectionAliases;
import org.hibernate.sql.ordering.antlr.GeneratedOrderByFragmentRendererTokenTypes;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenUtils {

	static final String CLAIM_KEY_USERNAME = "sub";
	static final String CLAIM_KEY_ROLE = "role";
	static final String CLAIM_KEY_CREATED = "created";
	
	@Value("${jwt.secret}")
	private String secret;
	
	@Value("${jwt.expiration}")
	private Long expiration;
	
	/**
	 * Retorna Username
	 * @param token
	 * @return
	 */
	public String retornaUsernameFromToken(String token) {
		String username;
		try {
			Claims claim = retornaClaimsFromToken(token);
			username = claim.getSubject();
		}catch (Exception e) {
			// TODO: handle exception
			username = null;
		}
		
		return username;
	}

	/**
	 * Retorna data de expiração
	 * @param token
	 * @return
	 */
	public Date retornaExpirationFromToken(String token) {
		Date expiration;
		try {
			Claims claim = retornaClaimsFromToken(token);
			expiration = claim.getExpiration();
		}catch (Exception e) {
			// TODO: handle exception
			expiration = null;
		}
		
		return expiration;
	}
	
	/**
	 * 
	 * @param token
	 * @return
	 */
	public String refreshToken (String token) {
		String refreshToken;
		try {
			Claims claim = retornaClaimsFromToken(token);
			claim.put(CLAIM_KEY_CREATED, new Date());
			refreshToken = gerarToken(claim);
		}catch (Exception e) {
			// TODO: handle exception
			refreshToken = null;
		}
		
		return refreshToken;
		
	}
	
	/**
	 * 
	 * @param userDetails
	 * @return
	 */
	public String obterToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<>();
		//username
		claims.put(CLAIM_KEY_USERNAME, userDetails.getUsername());
		//roles
		userDetails.getAuthorities().forEach(autority -> claims.put(CLAIM_KEY_ROLE, autority.getAuthority()));
		//data de criação
		claims.put(CLAIM_KEY_CREATED, new Date());
		
		return gerarToken(claims);
		
	}
	
	/**
	 * Diz se token é valido
	 * @param token
	 * @return
	 */
	public boolean tokenValido(String token) {
		return !tokenExpirado(token);
	}
	
	/**
	 * Diz se torem é expirado
	 * @param token
	 * @return
	 */
	private boolean tokenExpirado(String token) {
		Date dataExpiracao = this.retornaExpirationFromToken(token);
		if(dataExpiracao==null) {
			return false;
		}
		return dataExpiracao
				.before(new Date());
	}

	/**
	 * 
	 * @param claims
	 * @return
	 */
	public String gerarToken(Map<String, Object> claims) {
		
		return Jwts
				.builder()
				.setClaims(claims)
				.setExpiration(gerarDataExpiracao())
				.signWith(SignatureAlgorithm.HS512, this.secret)
				.compact();
	}

	/**
	 * Faz parser no token para extrair informações do mesmo, usando a chave do aplication.properties
	 * @param token
	 * @return
	 */
	public Claims retornaClaimsFromToken(String token) {
		Claims claims;
		try {
			claims = Jwts
					.parser()
					.setSigningKey(this.secret)
					.parseClaimsJws(token)
					.getBody();
			
		} catch(Exception e) {
			claims = null;
		}
		return claims;
	}
	
	/**
	 * 
	 * @return
	 */
	public Date gerarDataExpiracao() {
		Date data = new Date(System.currentTimeMillis()  +  this.expiration * 1000);
		
		return data;
	}
	
}

