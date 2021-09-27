package com.diego.seeddesafiomercadolivre.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import com.diego.seeddesafiomercadolivre.dto.LoginForm;
import com.diego.seeddesafiomercadolivre.dto.OpiniaoRequest;
import com.diego.seeddesafiomercadolivre.dto.OpiniaoResponse;
import com.diego.seeddesafiomercadolivre.dto.TokenResponse;
import com.diego.seeddesafiomercadolivre.handler.ValidationError;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class OpiniaoControllerTest {

	private final String url = "/produtos"; 
	@Autowired
	private TestRestTemplate testRestTemplate;
	private HttpHeaders headers;
	
	private String getToken(LoginForm loginForm) {
		var request = loginForm;
		var response = testRestTemplate.postForEntity("/auth", request, TokenResponse.class);
		return response.getBody().getToken();
	}
	
	private void setHeaders(LoginForm loginForm) {
		headers = new HttpHeaders();
		headers.setBearerAuth(getToken(loginForm));
	}
	
	@Test
	@DisplayName("deve cadastrar opinião quando todos os dados válidos")
	void test1() {
		setHeaders(new LoginForm("usr@mail.com", "123456"));
		
		var request = new OpiniaoRequest(5, "Excelente Produto", "Atendeu a todas as expectativas");
		
		var httpEntity = new HttpEntity<OpiniaoRequest>(request, headers);
		var response = testRestTemplate.postForEntity(url +"/1/opiniao", httpEntity, OpiniaoResponse.class);
		
		Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
		Assertions.assertTrue(response.getBody().getId() > 0);
		Assertions.assertNotNull(response.getBody().getLoginUsuairo());
	}
	
	@Test
	@DisplayName("não deve cadastrar opinião quando produto não pertencer ao usuario")
	void test2() {
		setHeaders(new LoginForm("diego@mail.com", "123456"));
		
		var request = new OpiniaoRequest(5, "Excelente Produto", "Atendeu a todas as expectativas");
		
		var httpEntity = new HttpEntity<OpiniaoRequest>(request, headers);
		var response = testRestTemplate.postForEntity(url +"/1/opiniao", httpEntity, ValidationError.class);
		
		Assertions.assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());		
		Assertions.assertEquals("O produto não pertence ao usuário!", response.getBody().getError());
		
	}

}
