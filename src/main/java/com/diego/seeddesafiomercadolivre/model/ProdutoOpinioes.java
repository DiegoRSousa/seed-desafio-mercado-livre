package com.diego.seeddesafiomercadolivre.model;

import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ProdutoOpinioes {

	private Set<Opiniao> opinioes;

	public ProdutoOpinioes(Set<Opiniao> opinioes) {
		this.opinioes = opinioes;
	}
	
	public <T> Set<T> mapeiaOpinioes(Function<Opiniao, T> funcaoMapeadora) {
		return this.opinioes.stream().map(funcaoMapeadora)
				.collect(Collectors.toSet());
	}
	
	public double media() {
		var notas = mapeiaOpinioes(Opiniao::getNota);
		return notas.stream().mapToInt(nota -> nota).average().orElse(0);
	}
	
	public int total() {
		return opinioes.size();
	}

	public Set<Opiniao> getOpinioes() {
		return opinioes;
	}

}
