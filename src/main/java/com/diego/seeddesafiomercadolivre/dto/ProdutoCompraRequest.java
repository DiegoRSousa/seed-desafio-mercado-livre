package com.diego.seeddesafiomercadolivre.dto;

import com.diego.seeddesafiomercadolivre.model.Produto;
import com.diego.seeddesafiomercadolivre.model.ProdutoCompra;
import com.diego.seeddesafiomercadolivre.repository.ProdutoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.ArrayList;

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
        var produto = produtoRepository.findById(produtoId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "produto nao encontrado, id: "  + produtoId));

        boolean atualizou = produto.atualizaEstoque(quantidade);

        if(atualizou)
            return new ProdutoCompra(quantidade, produto);

        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                "o produto " + produto.getNome() + " nao tem estoque suficiente, quantidade disponivel: " +
                produto.getQuantidadeDisponivel());
    }

    public int getQuantidade() {
        return quantidade;
    }

    public Long getProdutoId() {
        return produtoId;
    }
}
