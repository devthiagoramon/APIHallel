package br.api.hallel.service.interfaces;

import br.api.hallel.model.Associado;
import br.api.hallel.model.AtividadesCurso;
import br.api.hallel.model.Curso;
import br.api.hallel.payload.requerimento.AddCursoReq;

import java.util.List;

public interface CursoInterface {
    Curso createCurso(AddCursoReq cursoReq);
    List<Curso> listAllCursos();
    Curso listCursoById(String id);
    Curso updateCurso(String id, Curso curso);
    void deleteCurso(String id);
    void addAssociadoCurso(Associado associado, String idCurso);
    List<Curso> listCursoByUser(String idUsuario);
    List<Associado> listUserContainsCurso(String id);
    List<AtividadesCurso> listAtividadeByCurso(String id);
}
