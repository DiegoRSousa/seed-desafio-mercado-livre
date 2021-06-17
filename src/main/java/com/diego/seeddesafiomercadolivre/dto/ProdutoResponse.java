package com.diego.seeddesafiomercadolivre.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.diego.seeddesafiomercadolivre.model.Produto;

public class ProdutoResponse {

	private Long id;
	private String nome;
	private BigDecimal valor;
	private int quantidadeDisponivel;
	private String descricao;
	private String nomeCategoria;
	private String loginUsuario;
	private List<CaracteristicaResponse> caracteristicas;
	private LocalDateTime instante = LocalDateTime.now();
	
	@Deprecated
	public ProdutoResponse() {}
	
	public ProdutoResponse(Produto produto) {
		this.id = produto.getId();
		this.nome = produto.getNome();
		this.valor = produto.getValor();
		this.quantidadeDisponivel = produto.getQuantidadeDisponivel();
		this.descricao = produto.getDescricao();
		this.nomeCategoria = produto.getCategoria().getNome();
		this.loginUsuario = produto.getUsuario().getLogin();
		this.caracteristicas = produto.getCaracteristicas().stream().map(CaracteristicaResponse::new)
				.collect(Collectors.toList());
		this.instante = produto.getInstante();
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
	public String getNomeCategoria() {
		return nomeCategoria;
	}
	public String getLoginUsuario() {
		return loginUsuario;
	}

	public List<CaracteristicaResponse> getCaracteristicas() {
		return caracteristicas;
	}

	public LocalDateTime getInstante() {
		return instante;
	}
}
