package br.api.hallel.moduloAPI.exceptions.handler;

import br.api.hallel.moduloAPI.exceptions.ApiError;
import br.api.hallel.moduloAPI.exceptions.associado.AssociadoIllegalArgumentException;
import br.api.hallel.moduloAPI.exceptions.associado.AssociadoNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@RestControllerAdvice
public class AssociadoHandlerException {

    @ExceptionHandler(value = AssociadoNotFoundException.class)
    public ResponseEntity<ApiError> handlerAssociadoNotFound(AssociadoNotFoundException ex) {
        ApiError error = new ApiError(404, ex.getMessage(), new Date());
        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = AssociadoIllegalArgumentException.class)
    public ResponseEntity<ApiError> handlerAssociadoIllegal(AssociadoIllegalArgumentException ex) {
        ApiError error = new ApiError(400, ex.getMessage(), new Date());
        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }

}
