package com.diego.seeddesafiomercadolivre.dto;

import com.diego.seeddesafiomercadolivre.model.Opiniao;

public class OpiniaoResponse {

	
	private int nota;
	private String titulo;
	private String descricao;
	private String nomeUsuairo;
	private Long produtoId;
	
	@Deprecated
	public OpiniaoResponse() {}
	
	public OpiniaoResponse(Opiniao opiniao) {
		this.nota = opiniao.getNota();
		this.titulo = opiniao.getTitulo();
		this.descricao = opiniao.getDescricao();
		this.nomeUsuairo = opiniao.getUsuario().getLogin();
		this.produtoId = opiniao.getProduto().getId();
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


	public String getNomeUsuairo() {
		return nomeUsuairo;
	}


	public Long getProdutoId() {
		return produtoId;
	}
}
