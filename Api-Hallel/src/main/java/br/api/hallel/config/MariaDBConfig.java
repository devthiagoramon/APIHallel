package br.api.hallel.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "br.api.hallel.moduloMoodle")
public class MariaDBConfig {

}
