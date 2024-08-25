package br.api.hallel.moduloAPI.security;

import br.api.hallel.moduloAPI.security.config.CustomCSRFRepository;
import br.api.hallel.moduloAPI.security.exception.CustomAccessDeniedHandler;
import br.api.hallel.moduloAPI.security.jwt.AuthEntryPointJwt;
import br.api.hallel.moduloAPI.security.jwt.JwtTokenFilter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
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
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.io.IOException;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
@Log4j2
public class SecurityConfig {

    private String endpointsPermitidosAll[] = {
            // Apagar até o proximo comentário
            "/swagger-ui/**",
            "/api-docs/**",
            "/api-docs-ui",
            "/api/administrador/login",
            //
            "/api/public/**",
            };

    private final String[] endpointsPermitidasAdm =
            {"/api/administrador/**",
             "/api/membros/ministerio/coordenador/**"};
    private final String[] endpointsMembros = {"/api/membros/**",
                                               "/api/eventos/**"};
    private final String[] endpointsAssociado = {"/api/cursos/**",
                                                 "/api/associados/**",
                                                 "/api/sorteios/**"};

    private AuthEntryPointJwt unauthorizedHandler;

    private final JwtTokenFilter jwtTokenFilter;

    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws
            Exception {
        http
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers(
                                "/api/**",
                                "/api/cadastro",
                                "/api/home/isTokenValid",
                                // Apagar os proximos endpoints depois!
                                "/api-docs/**",
                                "/api-docs-ui",
                                "/swagger-ui/**"
                                                )
                        .csrfTokenRepository(new CustomCSRFRepository()))
                .authorizeHttpRequests((authorize) -> {
                    authorize
                            .requestMatchers(endpointsPermitidosAll)
                            .permitAll()
                            .requestMatchers(endpointsMembros)
                            .hasRole("USER")
                            .requestMatchers(endpointsAssociado)
                            .hasRole("ASSOCIADO")
                            .requestMatchers(endpointsPermitidasAdm)
                            .hasRole("ADMIN");
                })
                .exceptionHandling(configurer -> configurer
                        .accessDeniedHandler(accessDeniedHandler()))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(corsFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOriginPattern("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }

    private AccessDeniedHandler accessDeniedHandler() {
        return new CustomAccessDeniedHandler();
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
