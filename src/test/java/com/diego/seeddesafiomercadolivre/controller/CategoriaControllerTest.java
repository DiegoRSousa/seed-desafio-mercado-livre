package com.diego.seeddesafiomercadolivre.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;

import com.diego.seeddesafiomercadolivre.dto.CategoriaRequest;
import com.diego.seeddesafiomercadolivre.dto.CategoriaResponse;
import com.diego.seeddesafiomercadolivre.handler.FieldMessage;
import com.diego.seeddesafiomercadolivre.handler.ValidationError;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CategoriaControllerTest {

	private final String URL = "/categorias"; 
	@Autowired
	private TestRestTemplate testRestTemplate;
	
	@Test
	@DisplayName("deve salvar categoria quando todos os dados válidos")
	void test1() {
		var request = new CategoriaRequest("Tecnologia", null);
		var response = testRestTemplate.postForEntity(URL, request, CategoriaResponse.class);
		
		Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
		Assertions.assertNotNull(response.getBody().getId());
				
	}
	
	@Test
	@DisplayName("deve salvar categoria quando todos os dados válidos")
	void test2() {
		var request = new CategoriaRequest("Celular", 1L);
		var response = testRestTemplate.postForEntity(URL, request, CategoriaResponse.class);
		
		Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
		Assertions.assertNotNull(response.getBody().getId());
				
	}
	
	@Test
	@DisplayName("não deve salvar categoria quando nome já existe idCategoria não existe")
	void test3() {
		var request = new CategoriaRequest("Tecnologia", 4L);
		var response = testRestTemplate.postForEntity(URL, request, ValidationError.class);
		
		Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		Assertions.assertEquals(2, response.getBody().getErrors().size());
		Assertions.assertTrue(response.getBody().getErrors().contains(new FieldMessage("nome", "já existe")));
		Assertions.assertTrue(response.getBody().getErrors().contains(new FieldMessage("idCategoria", "não existe")));
	}
}
