package com.diego.seeddesafiomercadolivre.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Set;

@Entity
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Positive
    private BigDecimal total;
    @Size(min = 1)
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name ="compra_Id")
    private Set<ProdutoCompra> produtos;
    @Enumerated(EnumType.STRING)
    @NotNull
    private GatewayPagamento gatewayPagamento;

    @Deprecated
    public Compra(){}
    public Compra(@NotNull @Positive BigDecimal total, @Size(min = 1) Set<ProdutoCompra> produtos,
                  @NotNull GatewayPagamento gatewayPagamento) {
        this.total = total;
        this.produtos = produtos;
        this.gatewayPagamento = gatewayPagamento;
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public Set<ProdutoCompra> getProdutos() {
        return produtos;
    }

    public GatewayPagamento getGatewayPagamento() {
        return gatewayPagamento;
    }
}
