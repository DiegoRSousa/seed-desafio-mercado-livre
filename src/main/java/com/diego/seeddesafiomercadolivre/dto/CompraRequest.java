package com.diego.seeddesafiomercadolivre.dto;

import com.diego.seeddesafiomercadolivre.model.Compra;
import com.diego.seeddesafiomercadolivre.repository.ProdutoRepository;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Set;
import java.util.stream.Collectors;

public class CompraRequest {
    @NotNull
    private BigDecimal total;
    @Size(min = 1)
    private Set<ProdutoCompraRequest> produtos;

    @Deprecated
    public CompraRequest() {}

    public CompraRequest(BigDecimal total, Set<ProdutoCompraRequest> produtos) {
        this.total = total;
        this.produtos = produtos;
    }

    public Compra toModel(ProdutoRepository produtoRepository) {
        return new Compra(total, produtos.stream().map(item
                -> item.toModel(produtoRepository)).collect(Collectors.toSet()));
    }

    public BigDecimal getTotal() {
        return total;
    }

    public Set<ProdutoCompraRequest> getProdutos() {
        return produtos;
    }
}