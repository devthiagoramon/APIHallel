package br.api.hallel.service.interfaces;

import br.api.hallel.model.Curso;

import java.util.List;

public interface CursoInterface {
    Curso createCurso(Curso curso);
    List<Curso> listAllCursos();
    Curso listCursoById(String id);
    Curso updateCurso(Curso curso);
    void deleteCurso(String id);
}
