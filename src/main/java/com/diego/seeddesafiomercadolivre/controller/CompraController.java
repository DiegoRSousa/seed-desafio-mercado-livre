package com.diego.seeddesafiomercadolivre.controller;

import com.diego.seeddesafiomercadolivre.dto.CompraRequest;
import com.diego.seeddesafiomercadolivre.dto.CompraResponse;
import com.diego.seeddesafiomercadolivre.dto.ProdutoCompraRequest;
import com.diego.seeddesafiomercadolivre.dto.ProdutoRequest;
import com.diego.seeddesafiomercadolivre.model.Compra;
import com.diego.seeddesafiomercadolivre.model.Produto;
import com.diego.seeddesafiomercadolivre.repository.CompraRepository;
import com.diego.seeddesafiomercadolivre.repository.ProdutoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("compras")
public class CompraController {

    private CompraRepository compraRepository;
    private ProdutoRepository produtoRepository;

    public CompraController(CompraRepository compraRepository, ProdutoRepository produtoRepository) {
        this.compraRepository = compraRepository;
        this.produtoRepository = produtoRepository;
    }

    @PostMapping
    public ResponseEntity<CompraResponse> save(@RequestBody @Valid CompraRequest request) {
        Compra compra = request.toModel(produtoRepository);
        compraRepository.save(compra);
        return new ResponseEntity<>(new CompraResponse(compra), HttpStatus.CREATED);
    }
}
