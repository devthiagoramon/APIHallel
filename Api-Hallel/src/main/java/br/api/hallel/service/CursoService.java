package br.api.hallel.service;

import br.api.hallel.model.Associado;
import br.api.hallel.model.AtividadesCurso;
import br.api.hallel.model.Curso;
import br.api.hallel.model.ModulosCurso;
import br.api.hallel.payload.requerimento.AddCursoReq;
import br.api.hallel.payload.requerimento.AssociadoRequest;
import br.api.hallel.repository.CursoRepository;
import br.api.hallel.service.interfaces.CursoInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
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
        cursoOld.setImage(cursoNew.getImage());
        cursoOld.setDescricao(cursoNew.getDescricao());
        cursoOld.setRequisitos(cursoNew.getRequisitos());
        cursoOld.setModulos(cursoNew.getModulos());
        cursoOld.setAtividadesCursos(cursoNew.getAtividadesCursos());

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

    @Override
    public List<Curso> listCursoByUser(String idUsuario) {

        List<Curso> todosCursos = listAllCursos();
        List<Curso> cursosDoUser = new ArrayList<>();

        todosCursos.forEach(curso -> {
            curso.getParticipantes().forEach(participante -> {
                if (participante.getId().equals(idUsuario)) {
                    cursosDoUser.add(curso);
                }
            });
        });

        return cursosDoUser;
    }
    @Override
    public void addAssociadoCurso(Associado associado, String idCurso) {

        var curso = this.repository.findById(idCurso).get();

        if (curso.getParticipantes() != null) {

            curso.getParticipantes().forEach(participantes -> {
                if (participantes == associado) {
                    var associadoRequest = new AssociadoRequest();
                    associadoRequest.setNome(associado.getNome());
                    associadoRequest.setEmail(associado.getEmail());
                    associadoRequest.setDataNascimento(associado.getDataNascimentoAssociado());
                    associadoRequest.setIsAssociado(associado.getIsAssociado());

                    curso.getParticipantes().add(associadoRequest.toAssociado());
                    log.info("Participante " + associado.getNome() + " adicionado com sucesso!");

                }else{
                    log.info("Associado " + associado.getNome() + " já está inscrito!");
                    throw new RuntimeException();
                }
            });

        }else{
            var listaParticipantes = new ArrayList<Associado>();
            listaParticipantes.add(associado);
            curso.setParticipantes(listaParticipantes);

            log.info("Participante inscrito no Curso");
        }

        this.repository.save(curso);
    }
    public List<ModulosCurso> listModuloByIdCurso(String id) {

        var curso = listCursoById(id);

        return curso.getModulos().stream().collect(Collectors.toList());
    }

    @Override
    public List<Associado> listUserContainsCurso(String id) {
        var curso = this.repository.findById(id).get();

        return curso.getParticipantes().stream().collect(Collectors.toList());
    }

    @Override
    public List<AtividadesCurso> listAtividadeByCurso(String id) {
        var curso = this.repository.findById(id).get();

        return curso.getAtividadesCursos().stream().collect(Collectors.toList());
    }

}
