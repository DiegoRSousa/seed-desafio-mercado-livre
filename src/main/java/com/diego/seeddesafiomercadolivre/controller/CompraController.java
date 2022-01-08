package com.diego.seeddesafiomercadolivre.controller;

import javax.validation.Valid;

import com.diego.seeddesafiomercadolivre.dto.CompraRequest;
import com.diego.seeddesafiomercadolivre.model.Compra;
import com.diego.seeddesafiomercadolivre.model.GatewayPagamento;
import com.diego.seeddesafiomercadolivre.repository.CompraRepository;
import com.diego.seeddesafiomercadolivre.repository.ProdutoRepository;
import com.diego.seeddesafiomercadolivre.validator.EstoqueDisponivelProdutoCompraValidator;
import com.diego.seeddesafiomercadolivre.validator.ExisteProdutoCompraValidator;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("compras")
public class CompraController {

    private CompraRepository compraRepository;
    private ProdutoRepository produtoRepository;

    public CompraController(CompraRepository compraRepository, ProdutoRepository produtoRepository) {
        this.compraRepository = compraRepository;
        this.produtoRepository = produtoRepository;
    }

    @InitBinder(value = "compraRequest")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(new ExisteProdutoCompraValidator(produtoRepository),
                new EstoqueDisponivelProdutoCompraValidator(produtoRepository));
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid CompraRequest request,
                                     UriComponentsBuilder uriComponentBuilder) {
        var url = "";
        Compra compra = request.toModel(produtoRepository);
        compraRepository.save(compra);
        if(request.getGatewayPagamento().equals(GatewayPagamento.PAYPAL)) {
            var urlRetornoPaypal = uriComponentBuilder.path("retorno-pagseguro/{id}")
                    .buildAndExpand(compra.getId()).toString();
            url = "paypal.com?redirectUrl="+urlRetornoPaypal;
        } else {
            var urlRetornoPagseguro = uriComponentBuilder.path("retorno-pagseguro/{id}")
                    .buildAndExpand(compra.getId()).toString();
            url = "pagseguro.com?redirectUrl="+urlRetornoPagseguro;
        }
        return new ResponseEntity<>(url, HttpStatus.CREATED);
    }
}
