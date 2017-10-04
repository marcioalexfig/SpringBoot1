package com.matec.base;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/teste")
public class TesteController {
	/**
	 * Somente perfil admin pode sxecutar este metodo
	 * @param nome
	 * @return
	 */
	@GetMapping ("/{nome}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public String testando(@PathVariable("nome") String nome ) {
		return "Testando : " + nome;
	}

}
