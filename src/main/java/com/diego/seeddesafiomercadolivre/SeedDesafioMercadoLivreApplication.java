package com.diego.seeddesafiomercadolivre;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class SeedDesafioMercadoLivreApplication {

	public static void main(String[] args) {
		SpringApplication.run(SeedDesafioMercadoLivreApplication.class, args);
	}

}
