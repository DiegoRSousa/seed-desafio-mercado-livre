package com.diego.seeddesafiomercadolivre.service;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploaderFake {

	/**
	 * 
	 * @param imagens
	 * @return links para imagens que foram enviadas
	 */
	public Set<String> envia(List<MultipartFile> imagens) {
		
		return imagens.stream().map(imagem -> {
			var nomeSeparado = imagem.getOriginalFilename().split("\\.");
			return "http://bucket.io/" + nomeSeparado[0] 
					+ "-" + UUID.randomUUID().toString() + "." +nomeSeparado[1];
		}).collect(Collectors.toSet());
	}
}
