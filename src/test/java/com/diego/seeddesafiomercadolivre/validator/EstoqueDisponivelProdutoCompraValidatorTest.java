package com.diego.seeddesafiomercadolivre.validator;

import java.math.BigDecimal;
import java.util.Arrays;

import com.diego.seeddesafiomercadolivre.dto.CompraRequest;
import com.diego.seeddesafiomercadolivre.dto.ProdutoCompraRequest;
import com.diego.seeddesafiomercadolivre.model.Caracteristica;
import com.diego.seeddesafiomercadolivre.model.Categoria;
import com.diego.seeddesafiomercadolivre.model.GatewayPagamento;
import com.diego.seeddesafiomercadolivre.model.Produto;
import com.diego.seeddesafiomercadolivre.model.Usuario;
import com.diego.seeddesafiomercadolivre.repository.ProdutoRepository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.validation.BeanPropertyBindingResult;

class EstoqueDisponivelProdutoCompraValidatorTest {

    @Mock
    private ProdutoRepository produtoRepository;

    @BeforeEach
    private void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @ParameterizedTest
    @CsvSource({"false,4", "true,5"})
    void verificaSeProdutoTemEstoqueDisponivel(boolean esperado, int quantidade) {
        Mockito.when(produtoRepository.getOne(1L)).thenReturn(
                new Produto("Teclado Logitech", new BigDecimal(350.00), 4,
                        "Teclado mecânico switch red", new Categoria("teste"),
                        new Usuario("teste", "teste"),
                        Arrays.asList(new Caracteristica("nome", "descricao"))));

        var produtoCompraRequest
                = Arrays.asList(new ProdutoCompraRequest(quantidade, 1L));
        var request = new CompraRequest(
                new BigDecimal(1000.00), produtoCompraRequest, GatewayPagamento.PAYPAL);

        var validator = new EstoqueDisponivelProdutoCompraValidator(produtoRepository);
        var errors = new BeanPropertyBindingResult(request, "teste");
        validator.validate(request, errors);

        Assertions.assertEquals(esperado, errors.hasFieldErrors());
    }

}