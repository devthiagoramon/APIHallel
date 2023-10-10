package br.api.hallel.moduloAPI.exceptions.handler;

import br.api.hallel.moduloAPI.exceptions.ApiError;
import br.api.hallel.moduloAPI.exceptions.cursos.CursoNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@RestControllerAdvice
public class CursosHandlerException {

    @ExceptionHandler(value = CursoNotFoundException.class)
    public ResponseEntity handlerCursoNotFound(CursoNotFoundException ex) {

        ApiError error = new ApiError(404, ex.getMessage(), new Date());

        return new ResponseEntity(error, HttpStatus.NOT_FOUND);
    }

}
