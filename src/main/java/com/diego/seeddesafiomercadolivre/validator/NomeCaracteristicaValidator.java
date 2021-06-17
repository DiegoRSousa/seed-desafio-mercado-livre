package com.diego.seeddesafiomercadolivre.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.diego.seeddesafiomercadolivre.dto.ProdutoRequest;

@Component
public class NomeCaracteristicaValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return ProdutoRequest.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ProdutoRequest request = (ProdutoRequest) target;
		var nomesIguais = request.buscaCaracteristicasIguais();
		if(!nomesIguais.isEmpty()) {
			errors.rejectValue("caracteristicas", null, "existe(m) caracteristica(s) com o mesmo nome: " + nomesIguais);
		}
	}

}
