package br.api.hallel.service;

import br.api.hallel.model.*;
import br.api.hallel.payload.requerimento.CursoReq;
import br.api.hallel.repository.CursoRepository;
import br.api.hallel.service.interfaces.CursoInterface;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
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
    public Curso createCurso(CursoReq cursoReq) {
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
    public Curso updateCurso(String id, @NotNull Curso cursoNew) {

        Curso cursoOld = listCursoById(id);
        cursoOld.setNome(cursoNew.getNome());
        cursoOld.setImage(cursoNew.getImage());
        cursoOld.setDescricao(cursoNew.getDescricao());
        cursoOld.setRequisitos(cursoNew.getRequisitos());
        cursoOld.setModulos(cursoNew.getModulos());
        cursoOld.setAtividades(cursoNew.getAtividades());

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
        boolean isExists = false;

        if (curso.getParticipantes() != null) {

            for (Associado participantes : curso.getParticipantes()) {
                if (participantes.getEmail().equals(associado.getEmail())) {
                    System.out.println(participantes.getEmail());
                    isExists = true;
                }
            }

            if (isExists) {
                log.info("Associado já inscrito no curso!");
            } else {
                if (associado.getIsAssociado().equals(AssociadoRole.PAGO) || associado.getIsAssociado().equals(AssociadoRole.PENDENTE)) {
                    log.warn("Associado não está ativo!");
                } else {
                    curso.getParticipantes().add(associado);
                    log.info("Associado " + associado.getNome() + " inscrito com sucesso!");
                }
            }

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
    public List<AtividadesCurso> listAllAtividadesByCurso(String id) {
        var curso = this.repository.findById(id).get();

        return curso.getAtividades().stream().collect(Collectors.toList());
    }


}
