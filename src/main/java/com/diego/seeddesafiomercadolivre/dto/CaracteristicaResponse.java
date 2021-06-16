package com.diego.seeddesafiomercadolivre.dto;

import com.diego.seeddesafiomercadolivre.model.Caracteristica;

public class CaracteristicaResponse {

	private String nome;
	private String descricao;
	
	@Deprecated
	public CaracteristicaResponse() {}
	public CaracteristicaResponse(Caracteristica caracteristica) {
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
