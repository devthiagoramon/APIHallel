package br.api.hallel.moduloAPI.exceptions.handler;

public class EmailJaCadastradoException extends RuntimeException{
    public EmailJaCadastradoException() {
        super();
    }

    public EmailJaCadastradoException(String message) {
        super(message);
    }

    public EmailJaCadastradoException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmailJaCadastradoException(Throwable cause) {
        super(cause);
    }
}
