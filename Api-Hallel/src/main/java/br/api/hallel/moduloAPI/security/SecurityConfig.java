package br.api.hallel.moduloAPI.security;

import br.api.hallel.moduloAPI.security.jwt.AuthEntryPointJwt;
import br.api.hallel.moduloAPI.security.jwt.JwtTokenFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.oauth2.client.InMemoryOAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.oidc.web.logout.OidcClientInitiatedLogoutSuccessHandler;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.AuthenticatedPrincipalOAuth2AuthorizedClientRepository;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig {

    private String endpointsPermitidosAll[] = {"/api/login",
            "/api/home/isTokenExpired",
            "/api/cadastrar",
            "/api/administrador/login",
            "/api/administrador/create",
            "/api/listarCurso",
            "/api/descCurso/{id}",
            "/api/matricularParticipante/{idAssociado}/{idCurso}",
            "/api/eventos",
            "/api/doacao/**",
            "/api/home/eventos/semDestaque",
            "/api/home/eventos/participarEvento",
            "/api/home/eventos/listar"
    };

    private String endpointsPermitidasAdm[] =
            {"/api/administrador/**", "/api/administrador/cursos/**",
                    "/api/administrador/associados/**", "/api/administrador/doacoes/**",
                    "/api/administrador/eventos/**", "/api/administrador/loja/produtos/**",
                    "/api/administrador/retiros/**", "/api/administrador/sorteios/**", "/api/administrador/locais/**",
                    "/api/administrador/financeiro/**"};
    private String endpointsMembros[] = {"/api/membros/**", "/api/eventos/**", "/api/cursos/**"};
    private String endpointsAssociado[] = {"/api/cursos/**", "/api/associados/**", "/api/sorteios/**"};

    private AuthEntryPointJwt unauthorizedHandler;

    private final JwtTokenFilter jwtTokenFilter;

    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers(endpointsPermitidosAll).permitAll()
                .requestMatchers(endpointsMembros).hasRole("USER")
                .requestMatchers(endpointsAssociado).hasRole("ASSOCIADO")
                .requestMatchers(endpointsPermitidasAdm).hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .oauth2Login(Customizer.withDefaults())
                .logout()
                .logoutUrl("/api/google/logout")
                .clearAuthentication(true)
                .deleteCookies("JSESSIONID")
                .logoutSuccessHandler(this.oidcLogoutSuccessHandler())
                .and()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    private LogoutSuccessHandler oidcLogoutSuccessHandler() {
        OidcClientInitiatedLogoutSuccessHandler oidcLogoutSuccessHandler =
                new OidcClientInitiatedLogoutSuccessHandler(this.clientRegistrationRepository());
        oidcLogoutSuccessHandler.setPostLogoutRedirectUri("/api/google/sucess");

        return oidcLogoutSuccessHandler;
    }

    @Bean
    public ClientRegistrationRepository clientRegistrationRepository() {
        return new InMemoryClientRegistrationRepository(this.googleClientRegistration());
    }

    @Bean
    public OAuth2AuthorizedClientService authorizedClientService(
            ClientRegistrationRepository clientRegistrationRepository) {
        return new InMemoryOAuth2AuthorizedClientService(clientRegistrationRepository);
    }

    @Bean
    public OAuth2AuthorizedClientRepository authorizedClientRepository(
            OAuth2AuthorizedClientService authorizedClientService) {
        return new AuthenticatedPrincipalOAuth2AuthorizedClientRepository(authorizedClientService);
    }

    private ClientRegistration googleClientRegistration() {
        return CommonOAuth2Provider.GOOGLE.getBuilder("google")
                .clientId("746574312478-goh6jej88b401ehnspb5hncphnmu46b5.apps.googleusercontent.com")
                .clientSecret("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")
                .build();
    }


}
