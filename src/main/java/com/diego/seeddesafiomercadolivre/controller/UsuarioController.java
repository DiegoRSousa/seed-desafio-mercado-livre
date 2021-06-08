package com.diego.seeddesafiomercadolivre.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.diego.seeddesafiomercadolivre.dto.UsuarioRequest;
import com.diego.seeddesafiomercadolivre.dto.UsuarioResponse;
import com.diego.seeddesafiomercadolivre.repository.UsuarioRepository;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	private UsuarioRepository usuarioRepository;
	
	public UsuarioController(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

	@PostMapping
	public ResponseEntity<UsuarioResponse> salvar(@RequestBody @Valid  UsuarioRequest request) {
		var usuario = request.toModel();
		usuarioRepository.save(usuario);
		return new ResponseEntity<>(new UsuarioResponse(usuario), HttpStatus.CREATED);
	}	
}
