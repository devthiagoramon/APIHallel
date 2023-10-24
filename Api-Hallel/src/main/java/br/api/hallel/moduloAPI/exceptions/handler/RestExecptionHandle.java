package br.api.hallel.moduloAPI.exceptions.handler;

import br.api.hallel.moduloAPI.exceptions.ApiError;
import br.api.hallel.moduloAPI.exceptions.associado.MemberNotFoundExecption;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@ControllerAdvice
public class RestExecptionHandle {

    @ExceptionHandler(value = MemberNotFoundExecption.class)
    public ResponseEntity handleMemberNotFoundExeception(){

        ApiError error = new ApiError(404,"Membro não encontrado",new Date());

        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);

    }

}
