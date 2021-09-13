package com.diego.seeddesafiomercadolivre.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.diego.seeddesafiomercadolivre.model.Opiniao;
import com.diego.seeddesafiomercadolivre.model.Produto;
import com.diego.seeddesafiomercadolivre.model.Usuario;

public class OpiniaoRequest {

	@Min(1) @Max(5)
	private int nota;
	@NotBlank
	private String titulo;
	@NotBlank
	@Size(max = 500)
	private String descricao;
	
	
	@Deprecated
	public OpiniaoRequest() {}
	
	public OpiniaoRequest(@Min(1) @Max(5) int nota, @NotBlank String titulo,
			@NotBlank @Size(max = 500) String descricao) {
		super();
		this.nota = nota;
		this.titulo = titulo;
		this.descricao = descricao;
	}
	
	
	public Opiniao toModel(Produto produto, Usuario usuario) {
		return new Opiniao(nota, titulo, descricao, usuario, produto);
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
}
