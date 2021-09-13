package com.diego.seeddesafiomercadolivre.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.diego.seeddesafiomercadolivre.dto.OpiniaoRequest;
import com.diego.seeddesafiomercadolivre.dto.OpiniaoResponse;
import com.diego.seeddesafiomercadolivre.model.Opiniao;
import com.diego.seeddesafiomercadolivre.model.Usuario;
import com.diego.seeddesafiomercadolivre.repository.OpiniaoRepository;
import com.diego.seeddesafiomercadolivre.repository.ProdutoRepository;

@RestController
public class OpiniaoController {

	private ProdutoRepository produtoRepository;
	private OpiniaoRepository opiniaoRepository;
	
	public OpiniaoController(ProdutoRepository produtoRepository, OpiniaoRepository opiniaoRepository) {
		this.produtoRepository = produtoRepository;
		this.opiniaoRepository = opiniaoRepository;
	}


	@PostMapping("/produto/{id}/opiniao")
	public ResponseEntity<OpiniaoResponse> save(@PathVariable Long id, @RequestBody @Valid OpiniaoRequest request, 
			@AuthenticationPrincipal Usuario usuario) {
		
		var produto = produtoRepository.findById(id).orElseThrow(() 
				-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não econtrado!"));
		
		if(!produto.pertenceAoUsuario(usuario))
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "O produto não pertence ao usuário!");
		
		Opiniao opiniao = request.toModel(produto, usuario);
		opiniaoRepository.save(opiniao);
		
		return new ResponseEntity<>(new OpiniaoResponse(opiniao), HttpStatus.CREATED);
	}
}
