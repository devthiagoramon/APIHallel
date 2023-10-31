package br.api.hallel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling

public class ApiHallelApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiHallelApplication.class, args);
	}

}
