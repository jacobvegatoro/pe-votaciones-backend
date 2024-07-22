package com.puertoesp.votaciones.boletas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class ServicioBoletasApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServicioBoletasApplication.class, args);
	}

}
