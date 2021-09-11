package com.diego.seeddesafiomercadolivre.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class ProdutoRequestTest {

	@ParameterizedTest
	@MethodSource("gerador")
	@DisplayName("cria produtos com diversos tipos de caracteristicas")
	void test(int esperado, List<CaracteristicaRequest> caracteristicas) {
		var request = new ProdutoRequest("nome", BigDecimal.TEN, 10, "descricao", 1L, caracteristicas);
		Assertions.assertEquals(esperado, request.buscaCaracteristicasIguais().size());
		
	}
	
	private static Stream<Arguments> gerador() {
		return Stream.of(
				Arguments.of(0, List.of()),
				Arguments.of(0, List.of(new CaracteristicaRequest("nome", "descricao"), 
										new CaracteristicaRequest("nome1", "descricao1"))),
				Arguments.of(1, List.of(new CaracteristicaRequest("nome", "descricao"), 
										new CaracteristicaRequest("nome", "descricao"))));
	}

}
