package br.api.hallel.service.interfaces;

import br.api.hallel.model.Curso;
import br.api.hallel.model.ModulosCurso;
import br.api.hallel.payload.requerimento.AddCursoReq;

import java.util.List;

public interface CursoInterface {
    Curso createCurso(AddCursoReq cursoReq);
    List<Curso> listAllCursos();
    Curso listCursoById(String id);
    Curso updateCurso(String id, Curso curso);
    void deleteCurso(String id);

    List<Curso> listCursoByUser(String idUsuario);

    List<ModulosCurso> listModuloByIdCurso(String id);
}
