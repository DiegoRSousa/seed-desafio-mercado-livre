package com.diego.seeddesafiomercadolivre.model;

import net.bytebuddy.dynamic.loading.ClassReloadingStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
public class ProdutoCompra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Positive
    private int quantidade;
    @NotNull
    @ManyToOne
    private Produto produto;
    @ManyToOne
    private Compra compra;

    public ProdutoCompra() {}

    public ProdutoCompra(int quantidade, Produto produto) {
        this.quantidade = quantidade;
        this.produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public Produto getProduto() {
        return produto;
    }
}
