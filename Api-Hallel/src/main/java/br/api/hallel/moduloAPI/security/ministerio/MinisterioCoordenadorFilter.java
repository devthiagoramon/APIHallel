package br.api.hallel.moduloAPI.security.ministerio;

import br.api.hallel.moduloAPI.service.ministerio.MinisterioService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class MinisterioCoordenadorFilter extends HttpFilter {

    private final TokenCoordenadorMinisterio tokenCoordenadorMinisterio;
    private final MinisterioService ministerioService;

    public MinisterioCoordenadorFilter(
            TokenCoordenadorMinisterio tokenCoordenadorMinisterio,
            MinisterioService ministerioService) {
        this.tokenCoordenadorMinisterio = tokenCoordenadorMinisterio;
        this.ministerioService = ministerioService;

    }

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doFilter(HttpServletRequest request,
                            HttpServletResponse response,
                            FilterChain chain) throws IOException,
            ServletException {
        String token = request.getHeader("coordenador-token");

        if (token == null || !tokenCoordenadorMinisterio.validateToken(token)) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid token for a coordenador or isn't inserted");
            return;
        }

        String ministerioId = tokenCoordenadorMinisterio.getMinisterioFromToken(token);
        String membroId = tokenCoordenadorMinisterio.getMembroFromToken(token);

        if (!ministerioService.validateCoordenadorInMinisterio(ministerioId, membroId)) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid token for a coordenador");
            return;
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
