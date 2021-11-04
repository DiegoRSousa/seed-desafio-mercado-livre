package com.diego.seeddesafiomercadolivre.dto;

import com.diego.seeddesafiomercadolivre.model.Compra;

import java.math.BigDecimal;
import java.util.Set;
import java.util.stream.Collectors;

public class CompraResponse {
    private Long id;
    private BigDecimal total;
    private Set<ProdutoCompraResponse> produtos;

    public CompraResponse() {}

    public CompraResponse(Compra compra) {
        this.id = compra.getId();
        this.total = compra.getTotal();
        this.produtos = compra.getProdutos().stream().map(
                produto -> new ProdutoCompraResponse(produto)).collect(Collectors.toSet());
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public Set<ProdutoCompraResponse> getProdutos() {
        return produtos;
    }
}
