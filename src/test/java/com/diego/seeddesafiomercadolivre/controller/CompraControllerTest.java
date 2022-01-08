package com.diego.seeddesafiomercadolivre.controller;

import java.math.BigDecimal;
import java.util.Arrays;

import com.diego.seeddesafiomercadolivre.dto.CompraRequest;
import com.diego.seeddesafiomercadolivre.dto.LoginForm;
import com.diego.seeddesafiomercadolivre.dto.ProdutoCompraRequest;
import com.diego.seeddesafiomercadolivre.dto.TokenResponse;
import com.diego.seeddesafiomercadolivre.model.GatewayPagamento;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CompraControllerTest {

    private final String url = "/compras";
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
    void deveCadastrarCompraQuandoGatewayPagamentoPAYPAL() {
        setHeaders();

        var produtosCompraRequest = Arrays.asList(new ProdutoCompraRequest(1, 1L));
        var request = new CompraRequest(new BigDecimal(100.00), produtosCompraRequest, GatewayPagamento.PAYPAL);
        var httpEntity = new HttpEntity<CompraRequest>(request, headers);
        var response = testRestTemplate.postForEntity(url, httpEntity, String.class);

        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Assertions.assertTrue(response.getBody().contains("paypal.com?redirectUrl="));
    }

    @Test
    void deveCadastrarCompraQuandoGatewayPagamentoPAGSEGURO() {
        setHeaders();

        var produtosCompraRequest = Arrays.asList(new ProdutoCompraRequest(1, 1L));
        var request = new CompraRequest(new BigDecimal(100.00), produtosCompraRequest, GatewayPagamento.PAGSEGURO);
        var httpEntity = new HttpEntity<CompraRequest>(request, headers);
        var response = testRestTemplate.postForEntity(url, httpEntity, String.class);

        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Assertions.assertTrue(response.getBody().contains("pagseguro.com?redirectUrl="));
    }

}