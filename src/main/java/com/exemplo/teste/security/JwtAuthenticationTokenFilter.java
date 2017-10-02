package com.exemplo.teste.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

public class JwtAuthenticationTokenFilter extends OncePerRequestFilter{

	private static final String AUTH_HEADER = "Authorization";
	private static final String BEARER_PREFIX = "Bearer ";
	
	@Autowired
	private UserDetailsService userDetailService;
	
	@Autowired
	private JwtTokenUtils jwtTokenUtil;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		String token = request.getHeader(AUTH_HEADER);
		if (token != null && token.startsWith(BEARER_PREFIX)) {
				token = token.substring(7);
		}
		
		//extração do username (normalmente é o e-mail) contido no token
		String username = jwtTokenUtil.retornaUsernameFromToken(token);
		
		//pegar detalhes pelo username (email)
		if (username != null && SecurityContextHolder.getContext().getAuthentication() != null) {
			UserDetails userDetails = this.userDetailService.loadUserByUsername(username);
			
			//verificar se o token é valido
			if (jwtTokenUtil.tokenValido(token)) {
				UsernamePasswordAuthenticationToken autenticacao = new UsernamePasswordAuthenticationToken(
						userDetails, 
						null, 
						userDetails.getAuthorities()
						);
				SecurityContextHolder.getContext().setAuthentication(autenticacao);
			}
		}
		
		
		chain.doFilter(request, response);
		
		
	}

}
