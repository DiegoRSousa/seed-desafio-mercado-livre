package com.diego.seeddesafiomercadolivre.dto;

import com.diego.seeddesafiomercadolivre.model.Categoria;

public class CategoriaResponse {

	private Long id;
	private String nome;
	private String categoria;
	
	@Deprecated
	public CategoriaResponse() {}
	public CategoriaResponse(Categoria categoria) {
		this.id = categoria.getId();
		this.nome = categoria.getNome();
		this.categoria = categoria.getCategoria() != null ? categoria.getCategoria().getNome() : "";
		
	}
	
	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getCategoria() {
		return categoria;
	}
}