package com.diego.seeddesafiomercadolivre.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.Arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ProdutoTest {

	private Produto produto;
	private Usuario usuario;

	public ProdutoTest() {
		produto = new Produto("Teclado Logitech", new BigDecimal(100.00), 4, "Teclado mecânico",
				new Categoria("Teclados"), new Usuario("teste", "teste"), Arrays.asList(
						new Caracteristica("nome1", "descricao1"),
						new Caracteristica("nome2", "descricao2"),
						new Caracteristica("nome3", "descricao3")));
	}

	@Test
	void deveRetornarTrueQuandoProdutoPertencerAoUsuario() {
		usuario = new Usuario("teste", "teste");
		assertTrue(produto.pertenceAoUsuario(usuario));
	}
	
	@Test
	void deveRetornarFalseQuandoProdutoNaoPertencerAoUsuario() {
		var usuario = new Usuario("teste2", "teste");
		assertFalse(produto.pertenceAoUsuario(usuario));
	}

	@ParameterizedTest
	@CsvSource({"0", "-1"})
	void naoAceitaAtualizarEstoqueComQuantidadeMenorOuIgualAZero(int quantidade) {
		Assertions.assertThrows(IllegalArgumentException.class, ()
				-> produto.atualizaEstoque(quantidade));
	}


	@Test
	void aceitaAtualizarEstoqueComQuantidadeMaiorQueZero() {
		var quantidade = 1;
		produto.atualizaEstoque(quantidade);
		assertEquals(3, produto.getQuantidadeDisponivel());
	}

	@ParameterizedTest
	@CsvSource({"3", "4"})
	void deveRetornarTrueQuandoTiverEstoqueSuficiente(int quantidade) {
		var resultado = produto.temEstoqueSuficiente(quantidade);
		assertEquals(true, resultado);

	}

	@Test
	void deveRetornarFalseQuandoNaoTiverEstoqueSuficiente() {
		var resultado = produto.temEstoqueSuficiente(5);
		assertEquals(false, resultado);

	}



}
