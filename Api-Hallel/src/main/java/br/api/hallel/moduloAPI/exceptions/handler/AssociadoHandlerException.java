package br.api.hallel.moduloAPI.exceptions.handler;

import br.api.hallel.moduloAPI.exceptions.ApiError;
import br.api.hallel.moduloAPI.exceptions.AssociadoNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@ControllerAdvice
@RestController
public class AssociadoHandlerException {

    @ExceptionHandler(value = AssociadoNotFoundException.class)
    public ResponseEntity<ApiError> handlerAssociadoNotFound() {
        ApiError error = new ApiError(404, "Usuário não encontrado", new Date());

        return new ResponseEntity(error, HttpStatus.NOT_FOUND);
    }



}
