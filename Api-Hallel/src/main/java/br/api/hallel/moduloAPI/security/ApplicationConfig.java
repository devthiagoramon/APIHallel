package br.api.hallel.moduloAPI.security;

import br.api.hallel.moduloAPI.repository.AdministradorRepository;
import br.api.hallel.moduloAPI.repository.AssociadoRepository;
import br.api.hallel.moduloAPI.repository.MembroGoogleRepository;
import br.api.hallel.moduloAPI.repository.MembroRepository;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Properties;


@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

    @Autowired
    private AdministradorRepository administradorRepository;
    @Autowired
    private MembroRepository membroRepository;

    @Autowired
    private AssociadoRepository associadoRepository;
    @Autowired
    private MembroGoogleRepository googleRepository;

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            if (associadoRepository.findByEmail(username)
                                   .isPresent()) {
                return associadoRepository.findByEmail(username)
                                          .get();
            } else if (membroRepository.findByEmail(username)
                                       .isPresent()) {
                return membroRepository.findByEmail(username).get();
            } else if (administradorRepository.findByEmail(username)
                                              .isPresent()) {
                return administradorRepository.findByEmail(username)
                                              .get();
            } else if (this.googleRepository.findByEmail(username)
                                            .isPresent()) {
                return this.googleRepository.findByEmail(username)
                                            .get();
            }
            throw new UsernameNotFoundException("Usuario não encontrado");
        };
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEnconder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEnconder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);

        mailSender.setUsername("");
        mailSender.setPassword("");

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        return mailSender;
    }

    @Bean
    public OpenAPI api() {
        return new OpenAPI()
                .components(new Components()
                                .addSecuritySchemes("USER", new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("Bearer")
                                        .bearerFormat("JWT")
                                        .in(SecurityScheme.In.HEADER)
                                        .name("Authorization"))
                                .addSecuritySchemes("ASSOCIADO", new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("Bearer")
                                        .bearerFormat("JWT")
                                        .in(SecurityScheme.In.HEADER)
                                        .name("Authorization"))
                                .addSecuritySchemes("ADM", new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("Bearer")
                                        .bearerFormat("JWT")
                                        .in(SecurityScheme.In.HEADER)
                                        .name("Authorization"))
                           )
                .info(new Info().title("API HALLEL").version("0.1"));
    }
}
