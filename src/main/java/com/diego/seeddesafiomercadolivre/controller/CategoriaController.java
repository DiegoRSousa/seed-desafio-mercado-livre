package com.diego.seeddesafiomercadolivre.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.diego.seeddesafiomercadolivre.dto.CategoriaRequest;
import com.diego.seeddesafiomercadolivre.dto.CategoriaResponse;
import com.diego.seeddesafiomercadolivre.repositotry.CategoriaRepository;

@RestController
@RequestMapping("categorias")
public class CategoriaController {

	private CategoriaRepository categoriaRepository;
	
	public CategoriaController(CategoriaRepository categoriaRepository) {
		this.categoriaRepository = categoriaRepository;
	}

	@PostMapping
	public ResponseEntity<CategoriaResponse> salvar(@RequestBody @Valid CategoriaRequest request) {
		var categoria = request.toModel(categoriaRepository);
		categoriaRepository.save(categoria);
		return new ResponseEntity<>(new CategoriaResponse(categoria), HttpStatus.CREATED);
	}
}
