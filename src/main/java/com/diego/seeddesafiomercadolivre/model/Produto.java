package com.diego.seeddesafiomercadolivre.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

@Entity
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	private String nome;
	@NotNull
	@Positive
	private BigDecimal valor;
	@NotNull
	@PositiveOrZero
	private int quantidadeDisponivel;
	@Size(min = 1, max = 1000)
	private String descricao;
	@NotNull
	@ManyToOne
	@Valid
	private Categoria categoria;
	@NotNull
	@ManyToOne
	@Valid
	private Usuario usuario;
	@OneToMany(cascade = CascadeType.PERSIST)
	@Size(min = 3)
	private List<Caracteristica> caracteristicas;
	private LocalDateTime instante = LocalDateTime.now();

	@Deprecated
	public Produto() {}

	public Produto(@NotBlank String nome, @NotNull @Positive BigDecimal valor,
			@NotNull @PositiveOrZero int quantidadeDisponivel, @Size(min = 1, max = 1000) String descricao,
			@NotNull Categoria categoria, @NotNull Usuario usuario, @Size(min = 3) List<Caracteristica> caracteristicas) {
		this.nome = nome;
		this.valor = valor;
		this.quantidadeDisponivel = quantidadeDisponivel;
		this.descricao = descricao;
		this.categoria = categoria;
		this.usuario = usuario;
		this.caracteristicas = caracteristicas;
	}

	public Long getId() {
		return id;
	}
	public String getNome() {
		return nome;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public int getQuantidadeDisponivel() {
		return quantidadeDisponivel;
	}
	public String getDescricao() {
		return descricao;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public Usuario getUsuario() {
		return usuario;
	}

	public List<Caracteristica> getCaracteristicas() {
		return caracteristicas;
	}

	public LocalDateTime getInstante() {
		return instante;
	}
}