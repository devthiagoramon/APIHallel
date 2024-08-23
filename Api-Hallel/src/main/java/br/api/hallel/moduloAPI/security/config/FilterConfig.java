package br.api.hallel.moduloAPI.security.config;

import br.api.hallel.moduloAPI.security.ministerio.MinisterioCoordenadorFilter;
import br.api.hallel.moduloAPI.security.ministerio.TokenCoordenadorMinisterio;
import br.api.hallel.moduloAPI.service.ministerio.MinisterioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Autowired
    private TokenCoordenadorMinisterio tokenCoordenadorMinisterio;
    @Autowired
    private MinisterioService ministerioService;

    @Bean
    public FilterRegistrationBean<MinisterioCoordenadorFilter> filterRegistrationBean() {
        FilterRegistrationBean<MinisterioCoordenadorFilter> registrationBean = new FilterRegistrationBean<MinisterioCoordenadorFilter>();
        registrationBean.setFilter(new MinisterioCoordenadorFilter(tokenCoordenadorMinisterio, ministerioService));
        registrationBean.addUrlPatterns("/api/membros/ministerio/coordenador/*");
        return registrationBean;
    }

}
