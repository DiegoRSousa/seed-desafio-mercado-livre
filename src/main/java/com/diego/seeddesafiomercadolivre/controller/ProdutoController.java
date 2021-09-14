package com.diego.seeddesafiomercadolivre.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.diego.seeddesafiomercadolivre.dto.ImagemRequest;
import com.diego.seeddesafiomercadolivre.dto.ProdutoRequest;
import com.diego.seeddesafiomercadolivre.dto.ProdutoResponse;
import com.diego.seeddesafiomercadolivre.model.Produto;
import com.diego.seeddesafiomercadolivre.model.Usuario;
import com.diego.seeddesafiomercadolivre.repository.CategoriaRepository;
import com.diego.seeddesafiomercadolivre.repository.ProdutoRepository;
import com.diego.seeddesafiomercadolivre.service.Uploader;
import com.diego.seeddesafiomercadolivre.validator.NomeCaracteristicaValidator;

@RestController
@RequestMapping("produtos")
public class ProdutoController {

	private CategoriaRepository categoriaRepository;
	private ProdutoRepository produtoRepository;
	private Uploader uploaderFake;	
	
	@InitBinder(value = "produtoRequest")
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(new NomeCaracteristicaValidator());
	}
	
	public ProdutoController(CategoriaRepository categoriaRepository, ProdutoRepository produtoRepository,
			Uploader uploaderFake) {
		this.categoriaRepository = categoriaRepository;
		this.produtoRepository = produtoRepository;
		this.uploaderFake = uploaderFake;
	}

	@PostMapping
	public ResponseEntity<ProdutoResponse> save(@RequestBody @Valid ProdutoRequest request, 
			@AuthenticationPrincipal Usuario usuario) {
		Produto produto = request.toModel(categoriaRepository, usuario);
		produtoRepository.save(produto);
		return new ResponseEntity<>(new ProdutoResponse(produto), HttpStatus.CREATED);
	}
	
	@PostMapping("/{id}/imagens")
	public void addImagens(@PathVariable Long id, @Valid ImagemRequest request,
			@AuthenticationPrincipal Usuario usuario) {
		var links = uploaderFake.envia(request.getImagens());
		var produto = produtoRepository.getOne(id);
		
		if(!produto.pertenceAoUsuario(usuario))
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "O produto não pertence ao usuário!");
		
		produto.associaImagens(links);
		produtoRepository.save(produto);
		
	}
}
