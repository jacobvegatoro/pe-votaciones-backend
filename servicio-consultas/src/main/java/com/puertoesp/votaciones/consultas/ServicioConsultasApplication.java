package com.puertoesp.votaciones.consultas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
@EntityScan({"com.puertoesp.votaciones.commons.models.entity"})
public class ServicioConsultasApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServicioConsultasApplication.class, args);
	}

}
