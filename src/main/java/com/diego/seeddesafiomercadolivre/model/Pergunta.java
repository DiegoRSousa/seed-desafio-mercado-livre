package com.diego.seeddesafiomercadolivre.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Pergunta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	private String titulo;
	@NotNull
	@ManyToOne
	private Produto produto;
	@NotNull
	@ManyToOne
	private Usuario usuario;
	private LocalDate instante = LocalDate.now();
	
	@Deprecated
	public Pergunta() {}

	public Pergunta(@NotBlank String titulo, @NotNull Produto produto, @NotNull Usuario usuario) {
		this.titulo = titulo;
		this.produto = produto;
		this.usuario = usuario;
	}

	public String getTitulo() {
		return titulo;
	}

	public Produto getProduto() {
		return produto;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public LocalDate getInstante() {
		return instante;
	}
}
