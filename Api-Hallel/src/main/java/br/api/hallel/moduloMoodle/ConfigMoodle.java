package br.api.hallel.moduloMoodle;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "moodleEntityManagerFactory",
        transactionManagerRef = "moodleTransactionManager",
        basePackages = {"br.api.hallel.moduloMoodle"}
)
public class ConfigMoodle {


    @Bean(name = "moodleDataSource")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource moodleDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "moodleEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean moodleEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("moodleDataSource") DataSource moodleDataSource) {
        return builder.
                dataSource(moodleDataSource).
                packages("br.api.halle.moodle").
                build();
    }

    @Bean(name = "moodleTransactionManager")
    public PlatformTransactionManager moodleTransactionManager(
            @Qualifier(value = "moodleEntityManagerFactory") EntityManagerFactory moodleEntityManagerFactory
    ) {
        return new JpaTransactionManager(moodleEntityManagerFactory);
    }

}
