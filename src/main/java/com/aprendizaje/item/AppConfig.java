package com.aprendizaje.item;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

// Una clase de configuración nos sirve para crear beans
@Configuration
public class AppConfig {

	// Registramos como bean, para lo que devuelva este método se registre en el
	// contenedor de beans y luego lo podamos inyectar
	@Bean(name = "clienteRest")
	public RestTemplate registrarRestTemplate() {
		return new RestTemplate();
	}

}