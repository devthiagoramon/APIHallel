package br.api.hallel.service;

import br.api.hallel.model.*;
import br.api.hallel.payload.requerimento.AddCursoReq;
import br.api.hallel.payload.resposta.DescricaoCursoRes;
import br.api.hallel.repository.AssociadoRepository;
import br.api.hallel.repository.CursoRepository;
import br.api.hallel.service.interfaces.CursoInterface;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CursoService implements CursoInterface {

    @Autowired
    private CursoRepository repository;
    @Autowired
    private AssociadoRepository associadoRepository;

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
                if (!associado.getIsAssociado().equals(AssociadoRole.PAGO)) {
                    log.warn("Associado não está ativo!");

                } else {
                    inscrever(associado, curso);
                    curso.getParticipantes().add(associado);
                    log.info("Associado " + associado.getNome() + " inscrito com sucesso!");
                }
            }

        } else {
            var listaParticipantes = new ArrayList<Associado>();
            listaParticipantes.add(associado);
            curso.setParticipantes(listaParticipantes);
            inscrever(associado, curso);
            log.info("Participante inscrito no Curso");
        }

        log.info("User salvo!");
        this.repository.save(curso);
    }

    private void inscrever(Associado associado, Curso curso) {
        if (associado.getCursosInscritos() == null) {
            HashSet inscritos = new HashSet();
            inscritos.add(curso);
            associado.setCursosInscritos(inscritos);
        } else {
            associado.getCursosInscritos().add(curso);
        }
        this.associadoRepository.save(associado);
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

    @Override
    public String desempenhoDoCurso(String idAssociado, String idCurso) {
        var associado = this.associadoRepository.findById(idAssociado).get();
        var curso = this.listCursoById(idCurso);

        Double completds = Double.valueOf(associado.getModulosCursosCompletos().size());
        Double quantidade = Double.valueOf(curso.getModulos().size());

        Double resultado = (completds / quantidade);

        for (Curso allCursos :
                associado.getCursosInscritos()) {
            if (allCursos.getId().equals(idCurso)) {
                allCursos.setDesempenhoDoCurso(new DecimalFormat("0.00").format(resultado));
            }
        }

        this.associadoRepository.save(associado);
        return new DecimalFormat("0.00").format(resultado);
    }

    @Override
    public void generatePDF(HttpServletResponse response) throws IOException {

    }

    @Override
    public DescricaoCursoRes descCursoById(String id) {
        DescricaoCursoRes descricaoCurso = new DescricaoCursoRes();
        int qtdAtividade = 0;
        int qtdMaterias = 0;

        Curso curso = listCursoById(id);

        descricaoCurso = descricaoCurso.toDescricaoCursoRes(curso);

        if(curso.getModulos()!=null) {
            for (ModulosCurso modulo :
                    curso.getModulos()) {
                if(modulo.getAtividadesModulo()!= null) {
                    for (AtividadesCurso atividade :
                            modulo.getAtividadesModulo()) {
                        qtdAtividade++;
                        if (atividade.getArquivoAtividade() != "") {
                            qtdMaterias++;
                        }
                    }
                }
            }
            descricaoCurso.setQtdAtividades(qtdAtividade);
            descricaoCurso.setQtdMateriais(qtdMaterias);
        }

        return descricaoCurso;
    }

}
