package com.exemplo.teste.security;




import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exemplo.teste.dto.JwtAuthenticationDTO;
import com.exemplo.teste.dto.TokenDTO;
import com.exemplo.teste.response.Response;


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
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(jwtAuthenticationDTO.getEmail(), jwtAuthenticationDTO.getSenha())
				);
		
		//adiciona o usuario autenticado no contexto
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		//recupera o user details para a geracao do token
		UserDetails userDetail = userDetaisService.loadUserByUsername(jwtAuthenticationDTO.getEmail());
				
		//gera o token string
		String token = jwTokentUtil.obterToken(userDetail);
		
		//adicionar a string de token no response
		response.setDados(new TokenDTO(token));
		
		return ResponseEntity.ok(response);
	}
	
	
}
