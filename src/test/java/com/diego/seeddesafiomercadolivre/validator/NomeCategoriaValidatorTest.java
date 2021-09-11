package com.diego.seeddesafiomercadolivre.validator;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.validation.BeanPropertyBindingResult;

import com.diego.seeddesafiomercadolivre.dto.CaracteristicaRequest;
import com.diego.seeddesafiomercadolivre.dto.ProdutoRequest;

class NomeCategoriaValidatorTest {

	@ParameterizedTest
	@MethodSource("gerador")
	@DisplayName("não aceita produto com caracteristica igual")
	void test(boolean esperado, List<CaracteristicaRequest> caracteristicas) {
		var request = new ProdutoRequest("nome", BigDecimal.TEN, 10, "descricao", 1L, caracteristicas);
		
		var validator = new NomeCaracteristicaValidator();
		var errors = new BeanPropertyBindingResult(request, "teste");
		validator.validate(request, errors);
		Assertions.assertEquals(esperado, errors.hasFieldErrors());
	}
	
	private static Stream<Arguments> gerador() {
		return Stream.of(
				Arguments.of(false, List.of(new CaracteristicaRequest("nome", "descricao"), 
										new CaracteristicaRequest("nome1", "descricao1"))),
				Arguments.of(true, List.of(new CaracteristicaRequest("nome", "descricao"), 
										new CaracteristicaRequest("nome", "descricao"))));
	}

}
