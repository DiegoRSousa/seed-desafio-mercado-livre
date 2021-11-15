package com.diego.seeddesafiomercadolivre.validator;

import com.diego.seeddesafiomercadolivre.dto.CompraRequest;
import com.diego.seeddesafiomercadolivre.repository.ProdutoRepository;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class EstoqueDisponivelProdutoCompraValidator implements Validator {

    private ProdutoRepository produtoRepository;

    public EstoqueDisponivelProdutoCompraValidator(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return CompraRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        if(errors.hasErrors())
            return;

        CompraRequest request = (CompraRequest) target;
        var produtosRequest = request.getProdutos();

        for(int i = 0; i < produtosRequest.size(); i++) {
            var produtoId = produtosRequest.get(i).getProdutoId();
            var produto = produtoRepository.getOne(produtoId);
            if(produto.getQuantidadeDisponivel() < produtosRequest.get(i).getQuantidade()) {
                errors.rejectValue("produtos["+i+"].produtoId",
                        null,
                        "nao tem estoque suficiente, quantidade disponível: "
                        + produto.getQuantidadeDisponivel());
            }
        }
    }
}