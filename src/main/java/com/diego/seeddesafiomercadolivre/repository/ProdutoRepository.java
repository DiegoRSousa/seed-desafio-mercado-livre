package com.diego.seeddesafiomercadolivre.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.diego.seeddesafiomercadolivre.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{

}
