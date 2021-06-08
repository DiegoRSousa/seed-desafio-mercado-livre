package com.diego.seeddesafiomercadolivre.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;

import com.diego.seeddesafiomercadolivre.dto.UsuarioRequest;
import com.diego.seeddesafiomercadolivre.dto.UsuarioResponse;
import com.diego.seeddesafiomercadolivre.handler.ValidationError;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UsuarioControllerTest {

	private final String URL = "/usuarios";
	@Autowired
	private TestRestTemplate testRestTemplate;
	private UsuarioRequest request = new UsuarioRequest("admin@gmail.com", "123456");
	
	
	@Test
	@DisplayName("deve salvar usuário quando todos os dados válidos")
	void test1() {
		var response = testRestTemplate.postForEntity(URL, request, UsuarioResponse.class);
		
		Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
		Assertions.assertNotNull(response.getBody().getId());
	}
	
	@Test
	@DisplayName("não deve salvara usuário quando email já existe")
	void test2() {
		var response = testRestTemplate.postForEntity(URL, request, ValidationError.class);
		
		Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		Assertions.assertEquals(1, response.getBody().getErrors().size());
		Assertions.assertEquals("login", response.getBody().getErrors().get(0).getFieldName());
		Assertions.assertEquals("já existe", response.getBody().getErrors().get(0).getMessage());
	}

}
