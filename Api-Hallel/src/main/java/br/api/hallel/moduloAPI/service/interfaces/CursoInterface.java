package br.api.hallel.moduloAPI.service.interfaces;

import br.api.hallel.moduloAPI.exceptions.AssociadoNotFoundException;
import br.api.hallel.moduloAPI.model.Associado;
import br.api.hallel.moduloAPI.model.AtividadesCurso;
import br.api.hallel.moduloAPI.model.Curso;
import br.api.hallel.moduloAPI.model.ModulosCurso;
import br.api.hallel.moduloAPI.payload.requerimento.AddCursoReq;
import br.api.hallel.moduloAPI.payload.resposta.CursosAssociadoRes;
import br.api.hallel.moduloAPI.payload.resposta.DescricaoCursoRes;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public interface CursoInterface {
    Curso createCurso(AddCursoReq cursoReq);
    List<Curso> listAllCursos();
    Curso listCursoById(String id);
    Curso updateCurso(String id, Curso curso);
    void deleteCurso(String id);
    void addAssociadoCurso(String idAssociado, String idCurso) throws AssociadoNotFoundException;
    List<CursosAssociadoRes> listCursoByAssociado(String idUsuario);
    List<Associado> listUserContainsCurso(String id);
    List<AtividadesCurso> listAllAtividadesByCurso(String id);
    String desempenhoDoCurso(String idAssociado, String idCurso);
    DescricaoCursoRes descCursoById(String id);

    Associado concluirCurso(String idCurso, String idAssociado);
    Associado concluirAtividade(String tituloAtividade, String idAssociado, String idCurso);
    Double desempenhoCurso( String idAssociado);
    Associado favoriteCurso(String idAssociado, String idCurso);
    Associado concluirModuloCurso(ModulosCurso modulosCurso, String idAssociado);
    void removeAssociadoCurso(String idAssociado, String idCurso) throws AssociadoNotFoundException;

    void generatePDF(HttpServletResponse response) throws IOException;
}
