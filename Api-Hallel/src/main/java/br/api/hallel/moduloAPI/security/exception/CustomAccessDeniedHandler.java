package br.api.hallel.moduloAPI.security.exception;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.io.IOException;
import java.util.Date;

@Log4j2
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws
            IOException, ServletException,
            br.api.hallel.moduloAPI.security.exception.AccessDeniedException {
        System.out.println(accessDeniedException.getMessage()
                + " | TIME: " + new Date(request.getSession()
                                                .getCreationTime())
                + " | TOKEN: " + request.getHeader("Authorization") +
                " | URL: " + request.getRequestURL());
        response.sendError(403, "Can't access the app");
    }
}
