package br.api.hallel.service;

import br.api.hallel.exceptions.AssociadoNotFoundException;
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
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CursoService implements CursoInterface {

    @Autowired
    private CursoRepository repository;
    @Autowired
    private AssociadoRepository associadoRepository;

    @Autowired
    private AssociadoService associadoService;

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
        cursoOld.setAprendizado(cursoNew.getAprendizado());
        cursoOld.setAtividades(cursoNew.getAtividades());

        return this.repository.save(cursoOld);
    }

    public Associado updateCursoAndAssociado(String idCurso, Curso cursoNew, Associado associado) {

        Curso cursoOld = listCursoById(idCurso);

        cursoOld.setNome(cursoNew.getNome());
        cursoOld.setImage(cursoNew.getImage());
        cursoOld.setDescricao(cursoNew.getDescricao());
        cursoOld.setRequisitos(cursoNew.getRequisitos());
        cursoOld.setModulos(cursoNew.getModulos());
        cursoOld.setAprendizado(cursoNew.getAprendizado());
        cursoOld.setAtividades(cursoNew.getAtividades());
        cursoOld.setParticipantes(cursoNew.getParticipantes());

        Associado associadoVal = inscrever(associado, cursoOld);

        return this.associadoService.updateAssociadoById(associado.getId(), associadoVal);
    }

    @Override
    public void deleteCurso(String id) {
        Optional<Curso> optional = this.repository.findById(id);

        if (optional.isPresent()) {
            this.repository.deleteById(id);
        } else {
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
    public void addAssociadoCurso(String idAssociado, String idCurso) throws AssociadoNotFoundException {

        var curso = this.repository.findById(idCurso).get();
        boolean isExists = false;

        Associado associado = this.associadoService.listAssociadoById(idAssociado);

        if (associado == null) {
            log.warn("Associado com id " + idAssociado + " não encontrado");
            throw new AssociadoNotFoundException("Você não é associado");
        }


        if (curso.getParticipantes() != null) {

            for (Associado participantes : curso.getParticipantes()) {
                if (participantes.getEmail().equals(associado.getEmail())) {
                    System.out.println(participantes.getEmail());
                    isExists = true;
                }
            }


            if (isExists) {
            } else {
                if (!associado.getIsAssociado().equals(AssociadoRole.PAGO)) {

                } else {
                    curso.getParticipantes().add(associado);
                }
            }

        } else {
            ArrayList<Associado> listaParticipantesAssociado = new ArrayList<Associado>();

            listaParticipantesAssociado.add(associado);
            curso.setParticipantes(listaParticipantesAssociado);


        }

        log.info("User salvo!");
        this.updateCursoAndAssociado(curso.getId(), curso, associado );
    }

    private Associado inscrever(Associado associado, Curso curso) {
        Boolean ifExists = false;

        if (associado.getCursosInscritos() == null) {
            ArrayList<Curso> inscritos = new ArrayList<>();
            inscritos.add(curso);
            associado.setCursosInscritos(inscritos);
        } else {

            for (Curso cursos: associado.getCursosInscritos()) {
                if(cursos.getId() == curso.getId()){
                    ifExists = true;
                }
            }

            if(ifExists){
                log.warn("Curso já existente");
            }else{

            associado.getCursosInscritos().add(curso);
            }

        }
        return associado;

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
    public void removeAssociadoCurso(String idAssociado, String idCurso) {
        Curso curso = this.listCursoById(idCurso);
        Associado associado = this.associadoService.listAssociadoById(idAssociado);

        curso.getParticipantes().remove(associado);
//        associado.getCursosInscritos().remove(curso);

        updateCurso(idCurso, curso);
        this.associadoService.updateAssociadoById(idAssociado, associado);
    }


}
