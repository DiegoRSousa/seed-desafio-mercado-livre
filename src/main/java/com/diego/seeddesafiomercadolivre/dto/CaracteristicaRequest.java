package com.diego.seeddesafiomercadolivre.dto;

import javax.validation.constraints.NotBlank;

import com.diego.seeddesafiomercadolivre.model.Caracteristica;

public class CaracteristicaRequest {

	@NotBlank
	private String nome;
	@NotBlank
	private String descricao;
	@Deprecated
	public CaracteristicaRequest() {}
	
	public CaracteristicaRequest(@NotBlank String nome, @NotBlank String descricao) {
		this.nome = nome;
		this.descricao = descricao;
	}
	
	public Caracteristica toModel() {
		return new Caracteristica(nome, descricao);
	}

	public String getNome() {
		return nome;
	}
	public String getDescricao() {
		return descricao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CaracteristicaRequest other = (CaracteristicaRequest) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}
	
}
