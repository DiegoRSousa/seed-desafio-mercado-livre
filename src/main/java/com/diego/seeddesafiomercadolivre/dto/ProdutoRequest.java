package com.diego.seeddesafiomercadolivre.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import com.diego.seeddesafiomercadolivre.model.Categoria;
import com.diego.seeddesafiomercadolivre.model.Produto;
import com.diego.seeddesafiomercadolivre.model.Usuario;
import com.diego.seeddesafiomercadolivre.repository.CategoriaRepository;
import com.diego.seeddesafiomercadolivre.validator.Exists;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ProdutoRequest {

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
	@Exists(domainClass = Categoria.class, fieldName = "id")
	private Long categoriaId;
	@Size(min = 3)
	@JsonProperty("caracteristicas")
	private List<CaracteristicaRequest> caracteristicasRequest;
	
	@Deprecated
	public ProdutoRequest() {}
	public ProdutoRequest(@NotBlank String nome, @NotNull @Positive BigDecimal valor,
			@NotNull @PositiveOrZero int quantidadeDisponivel, @Size(min = 1, max = 1000) String descricao,
			@NotNull Long categoriaId, @Size(min = 3) List<CaracteristicaRequest> caracteristicasRequest) {
		super();
		this.nome = nome;
		this.valor = valor;
		this.quantidadeDisponivel = quantidadeDisponivel;
		this.descricao = descricao;
		this.categoriaId = categoriaId;
		this.caracteristicasRequest = caracteristicasRequest;
	}

	public Produto toModel(CategoriaRepository categoriaRepository, Usuario usuario) {
		var categoria = categoriaRepository.getOne(categoriaId);
		var caracteriscas = caracteristicasRequest.stream().map(CaracteristicaRequest::toModel)
				.collect(Collectors.toList());
		return new Produto(nome, valor, quantidadeDisponivel, descricao, categoria, usuario, caracteriscas);
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
	public Long getCategoriaId() {
		return categoriaId;
	}
	public List<CaracteristicaRequest> getCaracteristicasRequest() {
		return caracteristicasRequest;
	}
}
