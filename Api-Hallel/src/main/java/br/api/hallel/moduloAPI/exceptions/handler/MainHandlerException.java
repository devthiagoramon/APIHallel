package br.api.hallel.moduloAPI.exceptions.handler;

import br.api.hallel.moduloAPI.exceptions.ApiError;
import br.api.hallel.moduloAPI.exceptions.SolicitarCadastroException;
import br.api.hallel.moduloAPI.exceptions.SolicitarLoginException;
import br.api.hallel.moduloAPI.security.exception.AccessDeniedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@RestControllerAdvice
public class MainHandlerException {

    @ExceptionHandler(SolicitarLoginException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiError> handleLoginException(SolicitarLoginException ex){
        ApiError error = new ApiError(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), new Date());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmailJaCadastradoException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiError> handleEmailJaCadastradoException(EmailJaCadastradoException ex){
        ApiError error = new ApiError(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), new Date());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SolicitarCadastroException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiError> handleCadastroException(EmailJaCadastradoException ex){
        ApiError error = new ApiError(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), new Date());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity<ApiError> handleSecurityException(EmailJaCadastradoException ex){
        ApiError error = new ApiError(HttpStatus.FORBIDDEN.value(), ex.getMessage(), new Date());
        return new ResponseEntity<>(error, HttpStatus.FORBIDDEN);
    }
}
