package com.diego.seeddesafiomercadolivre.dto;

import com.diego.seeddesafiomercadolivre.model.Caracteristica;

public class DetalheProdutoCaracteristicaResponse {
	
	private String nome;
	private String descricao;
	
	@Deprecated
	public DetalheProdutoCaracteristicaResponse() {}
	
	public DetalheProdutoCaracteristicaResponse(Caracteristica caracteristica) {
		this.nome = caracteristica.getNome();
		this.descricao = caracteristica.getDescricao();
	}

	public String getNome() {
		return nome;
	}

	public String getDescricao() {
		return descricao;
	}
}
