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

import com.diego.seeddesafiomercadolivre.dto.PerguntaRequest;
import com.diego.seeddesafiomercadolivre.dto.PerguntaResponse;
import com.diego.seeddesafiomercadolivre.model.Pergunta;
import com.diego.seeddesafiomercadolivre.model.Usuario;
import com.diego.seeddesafiomercadolivre.repository.PerguntaRepository;
import com.diego.seeddesafiomercadolivre.repository.ProdutoRepository;
import com.diego.seeddesafiomercadolivre.service.SendEmail;

@RestController
public class PerguntaController {

	private ProdutoRepository produtoRepository;
	private PerguntaRepository perguntaRepository;
	private SendEmail sendEmail;
		
	public PerguntaController(ProdutoRepository produtoRepository, PerguntaRepository perguntaRepository,
			SendEmail sendEmail) {
		this.produtoRepository = produtoRepository;
		this.perguntaRepository = perguntaRepository;
		this.sendEmail = sendEmail;
	}

	@PostMapping("produtos/{id}/pergunta")
	public ResponseEntity<PerguntaResponse> save(@PathVariable Long id,  
			@RequestBody @Valid PerguntaRequest request, 
			@AuthenticationPrincipal Usuario usuario) {
		
		var produto = produtoRepository.findById(id).orElseThrow(() 
				-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não econtrado!"));
		
		Pergunta pergunta = request.toModel(produto, usuario);
		
		perguntaRepository.save(pergunta);
		
		sendEmail.sendPergunta(pergunta);
		
		return new ResponseEntity<>(new PerguntaResponse(pergunta), HttpStatus.CREATED);
	}
}
