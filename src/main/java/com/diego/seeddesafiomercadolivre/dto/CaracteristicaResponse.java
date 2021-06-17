package com.diego.seeddesafiomercadolivre.dto;

import com.diego.seeddesafiomercadolivre.model.Caracteristica;

public class CaracteristicaResponse {

	private Long id;
	private String nome;
	private String descricao;
	
	@Deprecated
	public CaracteristicaResponse() {}
	public CaracteristicaResponse(Caracteristica caracteristica) {
		this.id = caracteristica.getId();
		this.nome = caracteristica.getNome();
		this.descricao = caracteristica.getDescricao();
	}

	public Long getId() {
		return id;
	}
	public String getNome() {
		return nome;
	}
	public String getDescricao() {
		return descricao;
	}
	
}
