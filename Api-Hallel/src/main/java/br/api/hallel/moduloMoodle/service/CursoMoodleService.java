package br.api.hallel.moduloMoodle.service;

import br.api.hallel.moduloMoodle.model.CursoMoodle;
import br.api.hallel.moduloMoodle.payload.CursoMoodleReq;
import br.api.hallel.moduloMoodle.repository.CursoMoodleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CursoMoodleService {

    @Autowired
    private CursoMoodleRepository repository;

    public CursoMoodle createCursoMoodle(CursoMoodleReq cursoMoodleReq){
        return this.repository.save(cursoMoodleReq.toCursoMoodle());
    }

    public List<CursoMoodle> listCursoMoodle(){
        return this.repository.findAll();
    }

}
