package com.diego.seeddesafiomercadolivre.model;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Pergunta implements Comparable<Pergunta> {

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
	private LocalDateTime instante = LocalDateTime.now();
	
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

	public LocalDateTime getInstante() {
		return instante;
	}

	@Override
	public int hashCode() {
		return Objects.hash(titulo, usuario);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pergunta other = (Pergunta) obj;
		return Objects.equals(titulo, other.titulo) && Objects.equals(usuario, other.usuario);
	}

	@Override
	public int compareTo(Pergunta o) {
		return this.titulo.compareTo(o.getTitulo());
	}
}
