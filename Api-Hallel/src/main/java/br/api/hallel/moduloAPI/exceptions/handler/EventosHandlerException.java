package br.api.hallel.moduloAPI.exceptions.handler;

import br.api.hallel.moduloAPI.exceptions.ApiError;
import br.api.hallel.moduloAPI.exceptions.eventos.EventoIllegalArumentException;
import br.api.hallel.moduloAPI.exceptions.eventos.EventoNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@RestControllerAdvice
public class EventosHandlerException {


    @ExceptionHandler(value = EventoNotFoundException.class)
    public ResponseEntity<ApiError> handlerEventoNotFound(EventoNotFoundException ex) {
        ApiError error = new ApiError(404, ex.getMessage(), new Date());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = EventoIllegalArumentException.class)
    public ResponseEntity<ApiError> handlerEventoIllegalArgument(EventoIllegalArumentException ex) {
        ApiError error = new ApiError(404, ex.getMessage(), new Date());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
