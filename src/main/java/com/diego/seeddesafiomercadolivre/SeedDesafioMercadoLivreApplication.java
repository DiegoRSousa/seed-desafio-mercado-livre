package com.diego.seeddesafiomercadolivre;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class SeedDesafioMercadoLivreApplication {

	public static void main(String[] args) {
		SpringApplication.run(SeedDesafioMercadoLivreApplication.class, args);
		var x = new BCryptPasswordEncoder().encode("123456");
		System.out.println(x);
	}

}
