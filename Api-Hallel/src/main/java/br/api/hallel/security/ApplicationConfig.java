package br.api.hallel.security;

import br.api.hallel.model.Role;
import br.api.hallel.repository.AdministradorRepository;
import br.api.hallel.repository.MembroGoogleRepository;
import br.api.hallel.repository.MembroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.InMemoryOAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.AuthenticatedPrincipalOAuth2AuthorizedClientRepository;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

    @Autowired
    private AdministradorRepository administradorRepository;
    @Autowired
    private MembroRepository membroRepository;
    @Autowired
    private MembroGoogleRepository googleRepository;

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            if (membroRepository.findByEmail(username).isPresent()) {
                return membroRepository.findByEmail(username).get();
            } else if (administradorRepository.findByEmail(username).isPresent()) {
                return administradorRepository.findByEmail(username).get();
            } else if(this.googleRepository.findByEmail(username).isPresent()){
                return this.googleRepository.findByEmail(username).get();
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
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEnconder() {
        return new BCryptPasswordEncoder();
    }


}
