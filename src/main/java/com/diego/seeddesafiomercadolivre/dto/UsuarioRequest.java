package com.diego.seeddesafiomercadolivre.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.diego.seeddesafiomercadolivre.model.Usuario;
import com.diego.seeddesafiomercadolivre.validator.UniqueValue;

public class UsuarioRequest {

	@NotBlank
	@Email
	@UniqueValue(domainClass = Usuario.class, fieldName = "login")
	private String login;
	@NotBlank
	@Size(min = 6)
	private String senha;
	
	public UsuarioRequest(String login, String senha) {
		this.login = login;
		this.senha = senha;
	}

	public Usuario toModel() {
		return new Usuario(login, senha);
	}

	public String getLogin() {
		return login;
	}

	public String getSenha() {
		return senha;
	}
}
