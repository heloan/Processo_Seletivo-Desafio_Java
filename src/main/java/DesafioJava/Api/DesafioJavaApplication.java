/// :: **************************************************
/// :: Desafio Java | Author: Heloan Marinho | 10/04/2023
/// :: Version 1.0 - 10/04/2023
/// :: **************************************************

package DesafioJava.Api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
public class DesafioJavaApplication {

	public static void main(String[] args) {
		SpringApplication.run(DesafioJavaApplication.class, args);
	}

}
