package com.diego.seeddesafiomercadolivre.dto;

import javax.validation.constraints.NotBlank;

import com.diego.seeddesafiomercadolivre.model.Pergunta;
import com.diego.seeddesafiomercadolivre.model.Produto;
import com.diego.seeddesafiomercadolivre.model.Usuario;

public class PerguntaRequest {

	@NotBlank
	private String titulo;
	
	@Deprecated
	public PerguntaRequest() {}

	public PerguntaRequest(@NotBlank String titulo) {
		this.titulo = titulo;
	}

	public String getTitulo() {
		return titulo;
	}

	public Pergunta toModel(Produto produto, Usuario usuario) {
		return new Pergunta(titulo, produto, usuario);
	}
}
