package com.diego.seeddesafiomercadolivre.dto;

import java.time.LocalDate;

import com.diego.seeddesafiomercadolivre.model.Usuario;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

public class UsuarioResponse {
	
	private Long id;
	private String login;
	private String senha;
	@JsonFormat(pattern = "dd/MM/yyyy", shape = Shape.STRING)
	private LocalDate instante;
	
	@Deprecated
	public UsuarioResponse() {}
	
	public UsuarioResponse(Usuario usuario) {
		this.id = usuario.getId();
		this.login = usuario.getLogin();
		this.senha = usuario.getSenha();
		this.instante = usuario.getInstante();
	}
	public Long getId() {
		return id;
	}
	public String getLogin() {
		return login;
	}
	public String getSenha() {
		return senha;
	}
	public LocalDate getInstante() {
		return instante;
	}
	
}
