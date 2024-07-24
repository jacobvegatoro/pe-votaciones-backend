package com.puertoesp.votaciones.alternativas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class ServicioAlternativasApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServicioAlternativasApplication.class, args);
	}

}
