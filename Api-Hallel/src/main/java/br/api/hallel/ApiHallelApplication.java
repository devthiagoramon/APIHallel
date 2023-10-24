package br.api.hallel;

import br.api.hallel.config.MariaDBConfig;
import br.api.hallel.config.MongoDBConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@Import({MongoDBConfig.class, MariaDBConfig.class})
@EnableScheduling
public class ApiHallelApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiHallelApplication.class, args);
	}

}
