package br.api.hallel.moduloAPI.exceptions;

public class SolicitarLoginException extends RuntimeException {

    public SolicitarLoginException(String message) {
        super(message);
    }

    public SolicitarLoginException(Throwable t){
        super(t);
    }

}
