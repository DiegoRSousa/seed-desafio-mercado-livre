package com.diego.seeddesafiomercadolivre.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import io.jsonwebtoken.lang.Assert;
import org.hibernate.annotations.SortNatural;

@Entity
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	private String nome;
	@NotNull
	@Positive
	private BigDecimal valor;
	@NotNull
	@PositiveOrZero
	private int quantidadeDisponivel;
	@Size(min = 1, max = 1000)
	private String descricao;
	@NotNull
	@ManyToOne
	@Valid
	private Categoria categoria;
	@NotNull
	@ManyToOne
	@Valid
	private Usuario usuario;
	@OneToMany(cascade = CascadeType.PERSIST)
	@Size(min = 3)
	private List<Caracteristica> caracteristicas;
	@OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
	private Set<ProdutoImagem> imagens;
	@OneToMany(mappedBy = "produto")
	@SortNatural
	private SortedSet<Pergunta> perguntas = new TreeSet<>();
	@OneToMany(mappedBy="produto")
	private Set<Opiniao> opinioes;
	private LocalDateTime instante = LocalDateTime.now();

	@Deprecated
	public Produto() {}

	public Produto(@NotBlank String nome, @NotNull @Positive BigDecimal valor,
			@NotNull @PositiveOrZero int quantidadeDisponivel, 
			@Size(min = 1, max = 1000) String descricao, @NotNull Categoria categoria,
			@NotNull Usuario usuario, @Size(min = 3) List<Caracteristica> caracteristicas) {
		this.nome = nome;
		this.valor = valor;
		this.quantidadeDisponivel = quantidadeDisponivel;
		this.descricao = descricao;
		this.categoria = categoria;
		this.usuario = usuario;
		this.caracteristicas = caracteristicas;
	}

	public boolean atualizaEstoque(@Positive int quantidade) {
		Assert.isTrue(quantidade > 0, "A quantidade deve ser maior que zero " + quantidade);
		if(this.quantidadeDisponivel >= quantidade) {
			this.quantidadeDisponivel -= quantidade;
			return true;
		}
		return false;
	}
	
	public void associaImagens(Set<String> links) {
		this.imagens.addAll(links.stream().map(link 
				-> new ProdutoImagem(this, link)).collect(Collectors.toSet()));
	}
	
	public boolean pertenceAoUsuario(Usuario usuario) {
		return this.usuario.equals(usuario);
	}
	
	public <T> List<T> mapeiaCaracteristicas(Function<Caracteristica, T> funcaoMapeadora) {
		return this.caracteristicas.stream().map(funcaoMapeadora).collect(Collectors.toList());
	}
	
	public <T> Set<T> mapeiaImagens(Function<ProdutoImagem, T> funcaoMapeadora) {
		return this.imagens.stream().map(funcaoMapeadora).collect(Collectors.toSet());
	}
	
	public <T extends Comparable<T>> SortedSet<T> mapeiaPerguntas(Function<Pergunta, T> funcaoMapeadora) {
		return this.perguntas.stream().map(funcaoMapeadora)
				.collect(Collectors.toCollection(TreeSet::new));
	}
	
	public ProdutoOpinioes getOpinioes() {
		return new ProdutoOpinioes(this.opinioes);
	}

	public Long getId() {
		return id;
	}
	public String getNome() {
		return nome;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public int getQuantidadeDisponivel() {
		return quantidadeDisponivel;
	}
	public String getDescricao() {
		return descricao;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public Usuario getUsuario() {
		return usuario;
	}

	public List<Caracteristica> getCaracteristicas() {
		return caracteristicas;
	}
	
	public Set<ProdutoImagem> getImagens() {
		return imagens;
	}
	
	public SortedSet<Pergunta> getPerguntas() {
		return perguntas;
	}

	public LocalDateTime getInstante() {
		return instante;
	}
	
}
