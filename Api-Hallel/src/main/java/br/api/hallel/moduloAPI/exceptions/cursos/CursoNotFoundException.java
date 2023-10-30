package br.api.hallel.moduloAPI.exceptions.cursos;

public class CursoNotFoundException extends RuntimeException{
    public CursoNotFoundException(String message) {
        super(message);
    }
}
