package br.api.hallel.moduloAPI.service.cursos;

import br.api.hallel.moduloAPI.exceptions.associado.AssociadoNotFoundException;
import br.api.hallel.moduloAPI.model.*;
import br.api.hallel.moduloAPI.payload.requerimento.AddCursoReq;
import br.api.hallel.moduloAPI.payload.requerimento.AssociadoReq;
import br.api.hallel.moduloAPI.payload.resposta.AssociadoCursoResponse;
import br.api.hallel.moduloAPI.payload.resposta.CursosAssociadoRes;
import br.api.hallel.moduloAPI.payload.resposta.DescricaoCursoRes;
import br.api.hallel.moduloAPI.repository.AssociadoRepository;
import br.api.hallel.moduloAPI.repository.CursoRepository;
import br.api.hallel.moduloAPI.service.financeiro.AssociadoService;
import br.api.hallel.moduloAPI.service.interfaces.CursoInterface;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
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
@Log4j2
public class CursoService implements CursoInterface {

    @Autowired
    private CursoRepository cursoRepository;
    @Autowired
    private AssociadoRepository associadoRepository;
    @Autowired
    private AssociadoService associadoService;

    //CRUD para criar curso
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
        cursoNew.setId(id);
        return this.cursoRepository.save(cursoNew);
    }

    public void updateCursoAndAssociado(String idCurso, Curso cursoOld, Associado associado) {

        Curso cursoNew = cursoOld;

        boolean ifExists = false;

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

    //Listar informações de um curso através da inscrição de um usuário
    @Override
    public List<CursosAssociadoRes> listCursoByAssociado(String idUsuario) {

        List<Curso> todosCursos = listAllCursos();
        List<CursosAssociadoRes> cursosDoUser = new ArrayList<>();

        todosCursos.forEach(curso -> {
            if (curso.getParticipantes() != null) {
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

    //Adiciona usuário ao curso
    //Como parâmetro Id do associado e do Curso
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
                if (!associado.getIsAssociado().equals(AssociadoStatus.PAGO)) {

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

    //lista os módulos do curso
    public List<ModulosCurso> listModuloByIdCurso(String id) {

        var curso = listCursoById(id);

        return curso.getModulos().stream().collect(Collectors.toList());
    }

    //Lista os integrantes de um curso, como parâmetro Id do curso
    @Override
    public List<Associado> listUserContainsCurso(String id) {
        Curso curso = this.cursoRepository.findById(id).get();
        return curso.getParticipantes().stream().collect(Collectors.toList());
    }

    //Lista todas as atividades de um curso, como parâmetro Id do curso
    @Override
    public List<AtividadesCurso> listAllAtividadesByCurso(String id) {
        Curso curso = this.cursoRepository.findById(id).get();

        return curso.getAtividades().stream().collect(Collectors.toList());
    }

    //Retorna o desempenho de um usuário no curso
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

    //Por enquanto, inutilizável
    @Override
    public void generatePDF(HttpServletResponse response) throws IOException {

    }

    //Descrição de um curso
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

    //Associado concluir curso
    @Override
    public Associado concluirCurso(String idCurso, String idAssociado) throws AssociadoNotFoundException {
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

    //Associado concluir atividade de um curso
    @Override
    public Associado concluirAtividade(String tituloAtividade, String idAssociado, String idCurso) throws AssociadoNotFoundException {
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

    //Desempenho total de cursos
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

    //Associado favoritar um curso
    @Override
    public Associado favoriteCurso(String idAssociado, String idCurso) throws AssociadoNotFoundException {

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

    //Usuário concluir um módulo do curso
    @Override
    public Associado concluirModuloCurso(ModulosCurso modulosCurso, String idAssociado) throws AssociadoNotFoundException {
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

    //Remover um associado do curso
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
                if (!cursosIncritos.getId().equals(curso.getId())) {
                    indexCursoInscrito++;
                }
            }

            associado.getCursosInscritos().remove(indexCursoInscrito);

        } else {
            log.warn("Associado de id " + idAssociado + " não está inscrito");
            throw new AssociadoNotFoundException("Associado não encontrado");
        }

        log.info("Usuário removido!");
        this.updateCurso(idCurso, curso);
        this.updateCursoAndAssociado(idCurso, curso, associado);
    }

}
