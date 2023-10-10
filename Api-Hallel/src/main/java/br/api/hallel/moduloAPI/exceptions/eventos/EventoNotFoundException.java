package br.api.hallel.moduloAPI.exceptions.eventos;

public class EventoNotFoundException extends RuntimeException{
    public EventoNotFoundException(String message) {
        super(message);
    }
}
