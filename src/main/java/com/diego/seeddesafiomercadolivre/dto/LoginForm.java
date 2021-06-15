package com.diego.seeddesafiomercadolivre.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class LoginForm {

	@NotBlank
	@Email
	private String login;
	@Size(min = 6)
	private String senha;
	
	public LoginForm(String login, String senha) {
		this.login = login;
		this.senha = senha;
	}
	
	public UsernamePasswordAuthenticationToken converter() {
		return new UsernamePasswordAuthenticationToken(login, senha);
	}
	
	public String getLogin() {
		return login;
	}
	public String getSenha() {
		return senha;
	}
}
