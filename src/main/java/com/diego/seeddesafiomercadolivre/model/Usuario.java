package com.diego.seeddesafiomercadolivre.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity	
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	@Email
	private String login;
	@NotBlank
	@Size(min = 6)
	private String senha;
	private LocalDate instante = LocalDate.now();

	public Usuario(String login, String senha) {
		this.login = login;
		this.senha = new BCryptPasswordEncoder().encode(senha);
	}

	public Long getId() {
		return id;
	}

	public String getLogin() {
		return login;
	}

	public String getSenha() {
		return senha;
	}

	public LocalDate getInstante() {
		return instante;
	}
}
