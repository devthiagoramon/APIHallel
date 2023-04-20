package br.api.hallel.service;

import br.api.hallel.model.Curso;
import br.api.hallel.payload.requerimento.AddCursoReq;
import br.api.hallel.repository.CursoRepository;
import br.api.hallel.service.interfaces.CursoInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CursoService implements CursoInterface {

    @Autowired
    CursoRepository repository;

    @Override
    public Curso createCurso(AddCursoReq cursoReq) {
        return this.repository.insert(cursoReq.toCurso());
    }

    @Override
    public List<Curso> listAllCursos() {
        return this.repository.findAll();
    }

    @Override
    public Curso listCursoById(String id) {
        return this.repository.findById(id).orElseThrow(() ->
                new RuntimeException("Curso com id " + id + " não existe!"));
    }

    @Override
    public Curso updateCurso(String id, Curso cursoNew) {

        Curso cursoOld = listCursoById(id);
        cursoOld.setNome(cursoNew.getNome());
        cursoOld.setImage(cursoOld.getImage());
        cursoOld.setRequisitos(cursoNew.getRequisitos());
        cursoOld.setModulos(cursoNew.getModulos());

        return this.repository.save(cursoOld);
    }

    @Override
    public void deleteCurso(String id) {
        Optional<Curso> optional = this.repository.findById(id);

        if(optional.isPresent()){
            this.repository.deleteById(id);
        }else{
            throw new RuntimeException("Curso não criado!");
        }

    }
}
