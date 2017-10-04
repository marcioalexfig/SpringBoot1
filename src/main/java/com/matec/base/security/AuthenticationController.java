package com.matec.base.security;




import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.matec.base.dto.JwtAuthenticationDTO;
import com.matec.base.dto.TokenDTO;
import com.matec.base.response.Response;


@RestController
@RequestMapping ("/auth")
@CrossOrigin	(origins = "*")
public class AuthenticationController {

	private static final Logger log = LoggerFactory.getLogger(AuthenticationController.class);

	private static final String TOKEN_HEADER = "Authorization";
	private static final String BEARER_PREFIX = "Bearer";
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtTokenUtils jwTokentUtil;
	
	@Autowired
	private UserDetailsService userDetaisService;
	
	/**
	 * Autentica e gera o token, além de adicionar no contexto e no response, a autenticação
	 * @param jwtAuthenticationDTO
	 * @param result
	 * @return
	 */
	@PostMapping
	public ResponseEntity<Response<TokenDTO>> gerarToken (@Valid @RequestBody JwtAuthenticationDTO jwtAuthenticationDTO, BindingResult result){
		Response<TokenDTO> response = new Response<>();
		//verifica se existem erros
		if (result.hasErrors()) {
			log.error("Ocurreu um erro: ", result.getAllErrors());
			result.getAllErrors().forEach(erro -> response.getErros().add(erro.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		
		//criação do token
		log.info("Criação do tken para o e-mail", jwtAuthenticationDTO.getEmail());
		
		//autentica o usuario
		try {
			Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(jwtAuthenticationDTO.getEmail(), jwtAuthenticationDTO.getSenha())
				);
		
			//adiciona o usuario autenticado no contexto
			SecurityContextHolder.getContext().setAuthentication(authentication);
		} catch (AuthenticationException e) {
			log.error(e.getLocalizedMessage());
		}
		
		//recupera o user details para a geracao do token
		UserDetails userDetail = userDetaisService.loadUserByUsername(jwtAuthenticationDTO.getEmail());
				
		//gera o token string
		String token = jwTokentUtil.obterToken(userDetail);
		
		//adicionar a string de token no response
		response.setDados(new TokenDTO(token));
		
		return ResponseEntity.ok(response);
	}
	
	/**
	 * Atualiza o token
	 * @param request
	 * @return
	 */
	@PostMapping (value = "/refresh")
	public ResponseEntity<Response<TokenDTO>> geraRefreshToken(HttpServletRequest request){
		log.info("Gerando novo token");
		Response<TokenDTO> response = new Response<>();
		Optional<String> token = Optional.ofNullable(request.getHeader(TOKEN_HEADER));
		
		if ( token.isPresent() && token.get().startsWith(BEARER_PREFIX) ) {
			token = Optional.of(token.get().substring(7));
		}
		
		if ( !token.isPresent() ) {
			response.getErros().add("Token inexistente");
		} else if (!jwTokentUtil.tokenValido(token.get())) {
			response.getErros().add("Token inválido");
		}
		
		if (!response.getErros().isEmpty()) {
			return ResponseEntity.badRequest().body(response);
		}
		
		//Se existir tokem e se for válido, gerar o refresh
		String tokenRefresh = jwTokentUtil.refreshToken(token.get());
		response.setDados(new TokenDTO(tokenRefresh));
		
		return ResponseEntity.ok(response);
	}
	
	
}

