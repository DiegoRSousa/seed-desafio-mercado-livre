package com.diego.seeddesafiomercadolivre.controller;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import com.diego.seeddesafiomercadolivre.dto.CaracteristicaRequest;
import com.diego.seeddesafiomercadolivre.dto.LoginForm;
import com.diego.seeddesafiomercadolivre.dto.ProdutoRequest;
import com.diego.seeddesafiomercadolivre.dto.ProdutoResponse;
import com.diego.seeddesafiomercadolivre.dto.TokenResponse;
import com.diego.seeddesafiomercadolivre.handler.ValidationError;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProdutoControllerTest {

	private final String url = "/produtos"; 
	@Autowired
	private TestRestTemplate testRestTemplate;
	private HttpHeaders headers;
	
	private String getToken() {
		var request = new LoginForm("usr@mail.com", "123456");
		var response = testRestTemplate.postForEntity("/auth", request, TokenResponse.class);
		return response.getBody().getToken();
	}
	
	private void setHeaders() {
		headers = new HttpHeaders();
		headers.setBearerAuth(getToken());
	}
	
	@Test
	@DisplayName("deve cadastrar produto quando todos os dados válidos")
	void test1() {
		setHeaders();
		
		var request = new ProdutoRequest("nome", BigDecimal.TEN, 10, "descricao", 1L, List.of(
				new CaracteristicaRequest("nome1", "descricao1"), 
				new CaracteristicaRequest("nome2", "descricao2"),
				new CaracteristicaRequest("nome3", "descricao3")));
		var httpEntity = new HttpEntity<ProdutoRequest>(request, headers);
		var response = testRestTemplate.postForEntity(url, httpEntity, ProdutoResponse.class);
		
		Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
		Assertions.assertTrue(response.getBody().getId() > 0);
		Assertions.assertNotNull(response.getBody().getInstante());
	}
	
	@Test
	@DisplayName("não deve cadastrar produto quando existir categoria com nomes iguais")
	void test2() {
		setHeaders();
		
		var request = new ProdutoRequest("nome", BigDecimal.TEN, 10, "descricao", 1L, List.of(
				new CaracteristicaRequest("nome1", "descricao1"), 
				new CaracteristicaRequest("nome2", "descricao2"),
				new CaracteristicaRequest("nome2", "descricao2")));
		var httpEntity = new HttpEntity<ProdutoRequest>(request, headers);
		var response = testRestTemplate.postForEntity(url, httpEntity, ValidationError.class);
		
		Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		Assertions.assertEquals(1, response.getBody().getErrors().size());
		Assertions.assertEquals("caracteristicas", response.getBody().getErrors().get(0).getFieldName());
		Assertions.assertEquals("existe(m) caracteristica(s) com o mesmo nome: [nome2]", 
				response.getBody().getErrors().get(0).getMessage());
	}

}
