package br.api.hallel.moduloAPI.security;

import br.api.hallel.moduloAPI.repository.AdministradorRepository;
import br.api.hallel.moduloAPI.repository.AssociadoRepository;
import br.api.hallel.moduloAPI.repository.MembroGoogleRepository;
import br.api.hallel.moduloAPI.repository.MembroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


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
            if (associadoRepository.findByEmail(username).isPresent()) {
                return associadoRepository.findByEmail(username).get();
            } else if (membroRepository.findByEmail(username).isPresent()) {
                return membroRepository.findByEmail(username).get();
            } else if (administradorRepository.findByEmail(username).isPresent()) {
                return administradorRepository.findByEmail(username).get();
            } else if (this.googleRepository.findByEmail(username).isPresent()) {
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
