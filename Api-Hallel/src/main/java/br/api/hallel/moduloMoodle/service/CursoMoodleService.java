package br.api.hallel.moduloMoodle.service;

import br.api.hallel.moduloAPI.model.Produto;
import br.api.hallel.moduloAPI.payload.resposta.ProdutoResponse;
import br.api.hallel.moduloMoodle.model.CursoMoodle;
import br.api.hallel.moduloMoodle.payload.request.CursoMoodleReq;
import br.api.hallel.moduloMoodle.payload.response.CourseMoodleResponse;
import br.api.hallel.moduloMoodle.repository.CursoMoodleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CursoMoodleService {

    @Autowired
    private CursoMoodleRepository repository;

    public void createCursoMoodle(CursoMoodleReq cursoMoodleReq){
        log.info("Curso Criado!");
        this.repository.save(cursoMoodleReq.toCursoMoodle());
    }

    public List<CourseMoodleResponse> listCursoMoodle(){

        List<CourseMoodleResponse> responsesList = new ArrayList<>();
        this.repository.findAll().forEach(cursos ->{
            CourseMoodleResponse responseObj = new CourseMoodleResponse();
            responsesList.add(responseObj.toResponse(cursos));
        });
        log.info("Cursos Listado!");
        return responsesList;
    }

    public CourseMoodleResponse listCourseById(Long id){

        Optional<CursoMoodle> optional = this.repository.findById(id);

        CursoMoodle curso = optional.isPresent() ? optional.get() : null;
        CourseMoodleResponse response = new CourseMoodleResponse();

        log.info(curso == null ? "Curso listado!" : "Curso com id "+id+" não encontrado!");

        return response.toResponse(curso);
    }

    public CourseMoodleResponse updateCourseById(Long id, CursoMoodleReq cursoReq){

        CursoMoodle cursoOld = cursoReq.toCursoMoodle();
        cursoOld.setId(id);
        CursoMoodle cursoResponse = this.listCourseById(id) != null ? this.repository.save(cursoOld) : null;

        return new CourseMoodleResponse().toResponse(cursoResponse);
    }

    public void deleteCourseById(Long id){
        log.info(listCourseById(id) != null ? "Curso deletado!" : "Curso com id "+id+" não encontrado!");
    }

}
