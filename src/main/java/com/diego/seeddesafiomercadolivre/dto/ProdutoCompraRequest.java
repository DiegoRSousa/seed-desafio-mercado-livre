package com.diego.seeddesafiomercadolivre.dto;

import com.diego.seeddesafiomercadolivre.model.ProdutoCompra;
import com.diego.seeddesafiomercadolivre.repository.ProdutoRepository;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class ProdutoCompraRequest {
    @NotNull
    @Positive
    private int quantidade;
    @NotNull
    private Long produtoId;

    public ProdutoCompraRequest() {}

    public ProdutoCompraRequest(int quantidade, Long produtoId) {
        this.quantidade = quantidade;
        this.produtoId = produtoId;
    }

    public ProdutoCompra toModel(ProdutoRepository produtoRepository) {
        var produto = produtoRepository.getOne(produtoId);
        produto.atualizaEstoque(quantidade);

        return new ProdutoCompra(quantidade, produto);
    }

    public int getQuantidade() {
        return quantidade;
    }

    public Long getProdutoId() {
        return produtoId;
    }
}
