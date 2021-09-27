package com.diego.seeddesafiomercadolivre.dto;

import com.diego.seeddesafiomercadolivre.model.Pergunta;

public class PerguntaResponse {

	private String titulo;
	private String loginUsuario;
	private Long produtoId;
	
	@Deprecated
	public PerguntaResponse() {}
	
	public PerguntaResponse(Pergunta pergunta) {
		this.titulo = pergunta.getTitulo();
		this.loginUsuario = pergunta.getUsuario().getLogin();
		this.produtoId = pergunta.getProduto().getId();
	}

	public String getTitulo() {
		return titulo;
	}

	public String getLoginUsuario() {
		return loginUsuario;
	}

	public Long getProdutoId() {
		return produtoId;
	}
}
