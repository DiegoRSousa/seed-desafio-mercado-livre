package com.diego.seeddesafiomercadolivre.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.diego.seeddesafiomercadolivre.config.TokenService;
import com.diego.seeddesafiomercadolivre.dto.LoginForm;
import com.diego.seeddesafiomercadolivre.dto.TokenResponse;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {

	@Autowired
	private AuthenticationManager authManager;
	@Autowired
	private TokenService tokenService;
	
	@PostMapping
	public ResponseEntity<TokenResponse> autenticar(@RequestBody @Valid LoginForm form) {
		UsernamePasswordAuthenticationToken dadosLogin = form.converter();
		var authentication = authManager.authenticate(dadosLogin);
		var token = tokenService.gerarToken(authentication);
		return ResponseEntity.ok(new TokenResponse(token, "Bearer"));	
	}
}
