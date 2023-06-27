package br.api.hallel.config;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(basePackages = {"br.api.hallel.moduloMoodle.repository"},
        entityManagerFactoryRef = "mariaEntityManagerFactory", transactionManagerRef = "mariaTransactionManager"
        )
@Log4j2
public class MariaDBConfig {
    @Value("${spring.datasource.url}")
    private String mariaDBUrl;

    @Value("${spring.datasource.username}")
    private String mariaDBUsername;

    @Value("${spring.datasource.password}")
    private String mariaDBPassword;

    @Bean
    public DataSource mariaDBDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(mariaDBUrl);
        dataSource.setUsername(mariaDBUsername);
        dataSource.setPassword(mariaDBPassword);
        return dataSource;
    }

    @Bean(name = "mariaEntityManagerFactory")
    public EntityManagerFactory mariaDBEntityManagerFactory(DataSource mariaDBDataSource) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(mariaDBDataSource);
        em.setPackagesToScan("br.api.hallel.moduloMoodle.model");
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.afterPropertiesSet();
        return em.getObject();
    }

    @Bean(name = "mariaTransactionManager")
    public PlatformTransactionManager mariaDBTransactionManager(EntityManagerFactory mariaDBEntityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(mariaDBEntityManagerFactory);
        return transactionManager;
    }
}
