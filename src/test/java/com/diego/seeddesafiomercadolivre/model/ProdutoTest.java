package com.diego.seeddesafiomercadolivre.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

class ProdutoTest {

	private Produto produto;
	private Usuario usuario;
	
	public ProdutoTest() {
		produto = new Produto("Teclado Logitech", new BigDecimal(100.00), 1, "Teclado mecânico", 
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
}
