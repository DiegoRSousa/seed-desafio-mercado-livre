package com.diego.seeddesafiomercadolivre.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.diego.seeddesafiomercadolivre.dto.ProdutoRequest;
import com.diego.seeddesafiomercadolivre.dto.ProdutoResponse;
import com.diego.seeddesafiomercadolivre.model.Produto;
import com.diego.seeddesafiomercadolivre.model.Usuario;
import com.diego.seeddesafiomercadolivre.repository.CategoriaRepository;
import com.diego.seeddesafiomercadolivre.repository.ProdutoRepository;
import com.diego.seeddesafiomercadolivre.validator.NomeCategoriaValidator;

@RestController
@RequestMapping("produtos")
public class ProdutoController {

	private CategoriaRepository categoriaRepository;
	private ProdutoRepository produtoRepository;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(new NomeCategoriaValidator());
	}
	
	public ProdutoController(CategoriaRepository categoriaRepository, ProdutoRepository produtoRepository) {
		this.categoriaRepository = categoriaRepository;
		this.produtoRepository = produtoRepository;
	}

	@PostMapping
	public ResponseEntity<ProdutoResponse> save(@RequestBody @Valid ProdutoRequest request, 
			@AuthenticationPrincipal Usuario usuario) {
		Produto produto = request.toModel(categoriaRepository, usuario);
		produtoRepository.save(produto);
		return new ResponseEntity<>(new ProdutoResponse(produto), HttpStatus.CREATED);
	}
}
