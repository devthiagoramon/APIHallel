package br.api.hallel.moduloAPI.security.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.DefaultCsrfToken;

import java.util.UUID;

public class CustomCSRFRepository implements CsrfTokenRepository {

    private static final String CSRF_TOKEN_ATTR_NAME = "X-CSRF-TOKEN";

    @Override
    public CsrfToken generateToken(HttpServletRequest request) {
        String token = UUID.randomUUID().toString();
        return new DefaultCsrfToken("X-CSRF-TOKEN", "_csrf", token);
    }

    @Override
    public void saveToken(CsrfToken token, HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        if (token == null) {
            session.removeAttribute(CSRF_TOKEN_ATTR_NAME);
        } else {
            session.setAttribute(CSRF_TOKEN_ATTR_NAME, token);
        }
    }

    @Override
    public CsrfToken loadToken(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        return (session != null) ? (CsrfToken) session.getAttribute(CSRF_TOKEN_ATTR_NAME) : null;
    }
}
