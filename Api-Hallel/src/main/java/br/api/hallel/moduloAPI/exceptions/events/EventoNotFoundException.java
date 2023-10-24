package br.api.hallel.moduloAPI.exceptions.events;

public class EventoNotFoundException extends RuntimeException{
    public EventoNotFoundException(String message) {
        super(message);
    }
}
