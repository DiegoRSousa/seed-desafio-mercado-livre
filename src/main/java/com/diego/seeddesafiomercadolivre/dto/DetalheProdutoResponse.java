package com.diego.seeddesafiomercadolivre.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import com.diego.seeddesafiomercadolivre.model.Produto;
import com.diego.seeddesafiomercadolivre.model.ProdutoImagem;

public class DetalheProdutoResponse {

	private Set<String> imagens;
	private String nome;
	private String descricao;
	private BigDecimal preco;
	private int quantidadeDisponivel;
	private List<DetalheProdutoCaracteristicaResponse> caracteristicas;	

	public DetalheProdutoResponse(Produto produto) {
		this.imagens = produto.mapeiaImagens(ProdutoImagem::getLink);
		this.nome = produto.getNome();
		this.descricao = produto.getDescricao();
		this.preco = produto.getValor();
		this.quantidadeDisponivel = produto.getQuantidadeDisponivel();
		this.caracteristicas = produto
				.mapeiaCaracteristicas(DetalheProdutoCaracteristicaResponse::new);
	}
		
	public Set<String> getImagens() {
		return imagens;
	}

	public void setImagens(Set<String> imagens) {
		this.imagens = imagens;
	}


	public String getNome() {
		return nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public int getQuantidadeDisponivel() {
		return quantidadeDisponivel;
	}

	public List<DetalheProdutoCaracteristicaResponse> getCaracteristicas() {
		return caracteristicas;
	}	
}
