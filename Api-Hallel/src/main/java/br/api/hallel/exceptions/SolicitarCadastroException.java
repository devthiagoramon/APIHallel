package br.api.hallel.exceptions;

public class SolicitarCadastroException extends RuntimeException{

    public SolicitarCadastroException(String message){
        super(message);
    }

    public SolicitarCadastroException (Throwable t){
        super(t);
    }

}
