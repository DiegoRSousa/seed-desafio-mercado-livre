package com.diego.seeddesafiomercadolivre.service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.diego.seeddesafiomercadolivre.model.Pergunta;

public interface SendEmail {

	void sendPergunta(@NotNull @Valid Pergunta pergunta);
}
