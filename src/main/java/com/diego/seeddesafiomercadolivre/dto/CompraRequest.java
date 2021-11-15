package com.diego.seeddesafiomercadolivre.dto;

import com.diego.seeddesafiomercadolivre.model.Compra;
import com.diego.seeddesafiomercadolivre.model.GatewayPagamento;
import com.diego.seeddesafiomercadolivre.repository.ProdutoRepository;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class CompraRequest {
    @NotNull
    private BigDecimal total;
    @Size(min = 1)
    @Valid
    private List<ProdutoCompraRequest> produtos;
    @NotNull
    private GatewayPagamento gatewayPagamento;

    @Deprecated
    public CompraRequest() {}

    public CompraRequest(BigDecimal total, List<ProdutoCompraRequest> produtos,
                         GatewayPagamento gatewayPagamento) {
        this.total = total;
        this.produtos = produtos;
        this.gatewayPagamento = gatewayPagamento;
    }

    public Compra toModel(ProdutoRepository produtoRepository) {
        return new Compra(total, produtos.stream().map(item
                -> item.toModel(produtoRepository)).collect(Collectors.toSet()), gatewayPagamento);
    }

    public BigDecimal getTotal() {
        return total;
    }

    public List<ProdutoCompraRequest> getProdutos() {
        return produtos;
    }

    public GatewayPagamento getGatewayPagamento() {
        return gatewayPagamento;
    }
}