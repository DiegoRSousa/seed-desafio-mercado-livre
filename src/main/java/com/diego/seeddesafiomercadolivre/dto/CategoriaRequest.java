package com.diego.seeddesafiomercadolivre.dto;

import javax.validation.constraints.NotBlank;

import com.diego.seeddesafiomercadolivre.model.Categoria;
import com.diego.seeddesafiomercadolivre.repository.CategoriaRepository;
import com.diego.seeddesafiomercadolivre.validator.Exists;
import com.diego.seeddesafiomercadolivre.validator.UniqueValue;

public class CategoriaRequest {

	@NotBlank
	@UniqueValue(domainClass = Categoria.class, fieldName = "nome")
	private String nome;
	@Exists(domainClass = Categoria.class, fieldName = "id")
	private Long idCategoria;
	
	public CategoriaRequest(String nome, Long idCategoria) {
		this.nome = nome;
		this.idCategoria = idCategoria;
	}

	public Categoria toModel(CategoriaRepository categoriaReposittory) {
		var categoria = new Categoria(nome);
		if (idCategoria != null) {
			var possivelCategoria = categoriaReposittory.getOne(idCategoria);
			categoria.setCategoria(possivelCategoria);
		}
		return categoria;
	}

	public String getNome() {
		return nome;
	}

	public Long getIdCategoria() {
		return idCategoria;
	}
}
