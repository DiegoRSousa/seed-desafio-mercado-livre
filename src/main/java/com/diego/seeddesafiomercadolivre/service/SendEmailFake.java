package com.diego.seeddesafiomercadolivre.service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Service;

import com.diego.seeddesafiomercadolivre.model.Pergunta;

@Service
public class SendEmailFake implements SendEmail{

	public void sendPergunta(@NotNull @Valid Pergunta pergunta) {
		System.out.println("Nova pergunta");
		System.out.println("de " + pergunta.getUsuario().getLogin());
		System.out.println("para " + pergunta.getProduto().getUsuario().getLogin());
		System.out.println(pergunta.getInstante());
		System.out.println(pergunta.getProduto().getNome());
		System.out.println(pergunta.getTitulo());
	}
}
