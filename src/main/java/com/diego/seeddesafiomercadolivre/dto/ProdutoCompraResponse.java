package com.diego.seeddesafiomercadolivre.dto;

import com.diego.seeddesafiomercadolivre.model.ProdutoCompra;

import java.math.BigDecimal;

public class ProdutoCompraResponse {
    private Long id;
    private String nome;
    private BigDecimal valor;
    private int quantidade;

    public ProdutoCompraResponse() {}

    public ProdutoCompraResponse(ProdutoCompra produtoCompra) {
        this.id = produtoCompra.getProduto().getId();
        this.nome = produtoCompra.getProduto().getNome();
        this.valor = produtoCompra.getProduto().getValor();
        this.quantidade = produtoCompra.getQuantidade();
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

    public int getQuantidade() {
        return quantidade;
    }
}
