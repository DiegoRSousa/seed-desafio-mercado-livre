package com.diego.seeddesafiomercadolivre.dto;

import com.diego.seeddesafiomercadolivre.model.Opiniao;

public class OpiniaoResponse {

	private Long id;
	private int nota;
	private String titulo;
	private String descricao;
	private String loginUsuairo;
	private Long produtoId;
	
	@Deprecated
	public OpiniaoResponse() {}
	
	public OpiniaoResponse(Opiniao opiniao) {
		this.id = opiniao.getId();
		this.nota = opiniao.getNota();
		this.titulo = opiniao.getTitulo();
		this.descricao = opiniao.getDescricao();
		this.loginUsuairo = opiniao.getUsuario().getLogin();
		this.produtoId = opiniao.getProduto().getId();
	}

	public Long getId() {
		return id;
	}

	public int getNota() {
		return nota;
	}


	public String getTitulo() {
		return titulo;
	}


	public String getDescricao() {
		return descricao;
	}


	public String getLoginUsuairo() {
		return loginUsuairo;
	}


	public Long getProdutoId() {
		return produtoId;
	}
}
