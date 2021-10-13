package com.diego.seeddesafiomercadolivre.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.diego.seeddesafiomercadolivre.dto.DetalheProdutoResponse;
import com.diego.seeddesafiomercadolivre.model.Produto;
import com.diego.seeddesafiomercadolivre.repository.ProdutoRepository;

@RestController
public class DetalheProdutoController {

	private ProdutoRepository produtoRepository;
	
	public DetalheProdutoController(ProdutoRepository produtoRepository) {
		this.produtoRepository = produtoRepository;
	}

	@GetMapping("produtos/{id}/detalhes")
	public ResponseEntity<DetalheProdutoResponse> detalhar(@PathVariable Long id) {
		Produto produto = produtoRepository.findById(id).orElseThrow(()
				-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não econtrado!")); 
		return ResponseEntity.ok(new DetalheProdutoResponse(produto));
	}
}
