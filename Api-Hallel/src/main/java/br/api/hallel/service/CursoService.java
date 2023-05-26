package br.api.hallel.service;

import br.api.hallel.exceptions.AssociadoNotFoundException;
import br.api.hallel.model.*;
import br.api.hallel.payload.requerimento.AddCursoReq;
import br.api.hallel.payload.requerimento.AssociadoReq;
import br.api.hallel.payload.resposta.AssociadoCursoResponse;
import br.api.hallel.payload.resposta.CursosAssociadoRes;
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
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CursoService implements CursoInterface {

    @Autowired
    private CursoRepository cursoRepository;
    @Autowired
    private AssociadoRepository associadoRepository;

    @Autowired
    private AssociadoService associadoService;

    @Override
    public Curso createCurso(AddCursoReq cursoReq) {
        return this.cursoRepository.insert(cursoReq.toCurso());
    }

    @Override
    public List<Curso> listAllCursos() {
        return this.cursoRepository.findAll();
    }

    @Override
    public Curso listCursoById(String id) {
        return this.cursoRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Curso com id " + id + " não existe!"));
    }

    @Override
    public Curso updateCurso(String id, Curso cursoOld) {

        Curso cursoNew = cursoOld;

        return this.cursoRepository.save(cursoNew);
    }

    public void updateCursoAndAssociado(String idCurso, Curso cursoOld, Associado associado) {

        Curso cursoNew = cursoOld;

        Boolean ifExists = false;

        if (associado.getCursosInscritos() != null) {

            for (Curso cursosInscritos : associado.getCursosInscritos()) {
                    System.out.println("opa mermao");
                    ifExists = true;
                }


            if (ifExists) {
                log.warn("Curso já inscrito!");
            } else {
                associado.getCursosInscritos().add(cursoNew);
            }

        } else {
            ArrayList<Curso> cursosInscritos = new ArrayList<>();
            cursosInscritos.add(cursoNew);
            associado.setCursosInscritos(cursosInscritos);
        }

        this.associadoRepository.save(associado);
    }

    @Override
    public void deleteCurso(String id) {
        Optional<Curso> optional = this.cursoRepository.findById(id);

        if (optional.isPresent()) {
            this.cursoRepository.deleteById(id);
        } else {
            throw new RuntimeException("Curso não criado!");
        }

    }

    @Override
    public List<CursosAssociadoRes> listCursoByAssociado(String idUsuario) {

        List<Curso> todosCursos = listAllCursos();
        List<CursosAssociadoRes> cursosDoUser = new ArrayList<>();

        todosCursos.forEach(curso -> {
            if(curso.getParticipantes()!=null){
                curso.getParticipantes().forEach(participante -> {
                    if (participante.getId().equals(idUsuario)) {
                        CursosAssociadoRes cursosProv = new CursosAssociadoRes(
                                curso.getId(),
                                curso.getNome(),
                                curso.getImage());
                        cursosDoUser.add(cursosProv);
                    }
                });
            }
        });

        return cursosDoUser;
    }

    @Override
    public void addAssociadoCurso(String idAssociado, String idCurso) throws AssociadoNotFoundException {

        var curso = this.cursoRepository.findById(idCurso).get();
        boolean isExists = false;

        Associado associado = this.associadoService.listAssociadoById(idAssociado);

        if (associado == null) {
            log.warn("Associado com id " + idAssociado + " não encontrado");
            throw new AssociadoNotFoundException("Você não é associado");
        }

        AssociadoReq associadoReq = new AssociadoReq();

        if (curso.getParticipantes() != null) {

            for (Associado participantes : curso.getParticipantes()) {
                if (participantes.getEmail().equals(associado.getEmail())) {
                    isExists = true;
                }
            }


            if (isExists) {
            } else {
                if (!associado.getIsAssociado().equals(AssociadoRole.PAGO)) {

                } else {
                    curso.getParticipantes().add(associadoReq.toAssociado(associado));
                }
            }

        } else {
            ArrayList<Associado> listaParticipantesAssociado = new ArrayList<Associado>();

            listaParticipantesAssociado.add(associadoReq.toAssociado(associado));
            curso.setParticipantes(listaParticipantesAssociado);

        }

        log.info("User salvo!");
        this.updateCurso(curso.getId(), curso);
        this.updateCursoAndAssociado(idCurso, curso, associado);
    }

    public List<ModulosCurso> listModuloByIdCurso(String id) {

        var curso = listCursoById(id);

        return curso.getModulos().stream().collect(Collectors.toList());
    }

    @Override
    public List<Associado> listUserContainsCurso(String id) {
        var curso = this.cursoRepository.findById(id).get();
        return curso.getParticipantes().stream().collect(Collectors.toList());
    }

    @Override
    public List<AtividadesCurso> listAllAtividadesByCurso(String id) {
        var curso = this.cursoRepository.findById(id).get();

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

        if (curso.getModulos() != null) {
            for (ModulosCurso modulo :
                    curso.getModulos()) {
                if (modulo.getAtividadesModulo() != null) {
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

    @Override
    public Associado concluirCurso(String idCurso, String idAssociado) {
        var curso = this.listCursoById(idCurso);
        var associado = this.associadoService.listAssociadoById(idAssociado);

        curso.setCursoCompleted(true);

        if (associado.getHistoricoCurso() == null) {
            ArrayList<Curso> historico = new ArrayList();
            historico.add(curso);
            associado.setHistoricoCurso(historico);

        } else {
            associado.getHistoricoCurso().add(curso);
        }

        log.info("Curso concluido");
        return this.associadoRepository.save(associado);
    }

    @Override
    public Associado concluirAtividade(String tituloAtividade, String idAssociado, String idCurso) {
        var curso = this.listCursoById(idCurso);
        var associado = associadoService.listAssociadoById(idAssociado);

        for (AtividadesCurso atividades : curso.getAtividades()) {
            if (atividades.getTituloAtividade().equals(tituloAtividade)) {

                if (associado.getAssociadoAtividadesCurso() == null) {
                    HashMap hashMap = new HashMap();
                    hashMap.put(atividades, true);
                    associado.setAssociadoAtividadesCurso(hashMap);

                } else {
                    associado.getAssociadoAtividadesCurso().put(atividades, true);
                }
            }
        }

        return this.associadoRepository.save(associado);
    }

    @Override
    public Double desempenhoCurso(String idAssociado) {
        var associado = this.associadoRepository.findById(idAssociado).get();


        Double quantidade = Double.valueOf(associado.getCursosInscritos().size());
        Double completeds = Double.valueOf(associado.getHistoricoCurso().size());

        Double porcentagem = (completeds / quantidade);

        associado.setDesempenhoTotalCursos(porcentagem);

        this.associadoRepository.save(associado);
        return associado.getDesempenhoTotalCursos();
    }

    @Override
    public Associado favoriteCurso(String idAssociado, String idCurso) {

        var curso = this.listCursoById(idCurso);
        var associado = this.associadoService.listAssociadoById(idAssociado);

        if (associado.getCursosFavoritos() == null) {
            ArrayList<Curso> cursosFavoritos = new ArrayList<>();
            cursosFavoritos.add(curso);
            associado.setCursosFavoritos(cursosFavoritos);

        } else {
            associado.getCursosFavoritos().add(curso);
        }

        return this.associadoRepository.save(associado);
    }

    @Override
    public Associado concluirModuloCurso(ModulosCurso modulosCurso, String idAssociado) {
        var associado = this.associadoService.listAssociadoById(idAssociado);

        if (associado.getModulosCursosCompletos() == null) {
            ArrayList<ModulosCurso> modulos = new ArrayList();
            modulos.add(modulosCurso);
            associado.setModulosCursosCompletos(modulos);
        } else {
            associado.getModulosCursosCompletos().add(modulosCurso);
        }

        return this.associadoRepository.save(associado);
    }

    @Override
    public void removeAssociadoCurso(String idAssociado, String idCurso) throws AssociadoNotFoundException {
        Curso curso = this.listCursoById(idCurso);

        Associado associado = this.associadoService.listAssociadoById(idAssociado);
        AssociadoCursoResponse associadoResponse = new AssociadoCursoResponse();

        int index = 0;
        int indexCursoInscrito = 0;

        if (curso.getParticipantes().contains(associadoResponse.toAssociado(associado))) {
            for (Associado associadosParticipando :
                    curso.getParticipantes()) {

                if (!associadosParticipando.getEmail().equals(associado.getEmail())) {
                    index++;
                }

            }

            curso.getParticipantes().remove(index);

            for (Curso cursosIncritos :
                    associado.getCursosInscritos()) {
                if (cursosIncritos.getId() != curso.getId()) {
                    indexCursoInscrito++;
                }
            }

            associado.getCursosInscritos().remove(indexCursoInscrito);

        } else {
            log.warn("Associado de id " + idAssociado + " não está inscrito");
            throw new AssociadoNotFoundException("Associado não encontrado");
        }

        log.info("Usuário removido!");
        updateCurso(idCurso, curso);
        updateCursoAndAssociado(idCurso, curso, associado);
    }


}
