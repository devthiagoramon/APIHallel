package br.api.hallel.service;

import br.api.hallel.model.Associado;
import br.api.hallel.model.AtividadesCurso;
import br.api.hallel.model.Curso;
import br.api.hallel.model.ModulosCurso;
import br.api.hallel.payload.resposta.AssociadoPagamentosRes;
import br.api.hallel.repository.AssociadoRepository;
import br.api.hallel.service.interfaces.AssociadoInterface;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class AssociadoService implements AssociadoInterface {

    @Autowired
    private AssociadoRepository associadoRepository;
    @Autowired
    private CursoService cursoService;

    @Override
    public List<Associado> listAllAssociado() {
        return this.associadoRepository.findAll();
    }

    Logger logger = LoggerFactory.getLogger(AssociadoService.class);


    //LISTA TODOS OS ASSOCIADOS
    @Override
    public Associado listAssociadoById(String id) {

        logger.info("ASSOCIADO LISTADO!");

        return this.associadoRepository.findById(id).isPresent() ? this.associadoRepository.findById(id).get() : null;
    }

    //DELETA UM ASSOCIADO PELO ID DELE
    @Override
    public void deleteAssociado(String id) {
        Optional<Associado> optional = this.associadoRepository.findById(id);

        if (optional.isPresent()) {
            Associado associado = optional.get();

            logger.info("ASSOCIADO REMOVIDO!");

            this.associadoRepository.delete(associado);
        }

    }

    //ATUALIZA INFORMAÇÕES SOBRE O ASSOCIADO
    @Override
    public Associado updateAssociadoById(String id, Associado associado) {

        Optional<Associado> optional = this.associadoRepository.findById(id);

        if (optional.isPresent()) {
            Associado associadoOptional = optional.get();

            associadoOptional = associado;

            logger.info("ASSOCIADO ATUALIZADO!");

            System.out.println("Associado atualizado");
            return this.associadoRepository.save(associado);
        } else {
            logger.warn("ASSOCIADO NÃO ENCONTRADO!");

            return null;
        }
    }

    @Override
    public List<AssociadoPagamentosRes> getAllPagamentosAssociados() {

        List<Associado> associados = this.associadoRepository.findAll();

        List<AssociadoPagamentosRes> pagamentosRes = new ArrayList<>();

        associados.forEach(associado -> {
            AssociadoPagamentosRes pagamento = new AssociadoPagamentosRes(associado.getNome(), associado.getEmail(), associado.getIsPago(), associado.getIsAssociado(), associado.getTransacao());
            pagamentosRes.add(pagamento);
        });

        return pagamentosRes;
    }

    @Override
    public AssociadoPagamentosRes getAssociadoPagamentoById(String id) {
        Associado associado = listAssociadoById(id);

        return new AssociadoPagamentosRes(associado.getNome(), associado.getEmail(), associado.getIsPago(), associado.getIsAssociado(), associado.getTransacao());
    }

    @Override
    public Associado concluirCurso(String idCurso, String idAssociado) {
        var curso = this.cursoService.listCursoById(idCurso);
        var associado = this.listAssociadoById(idAssociado);

        curso.setCursoCompleted(true);

        if (associado.getHistoricoCurso() == null) {
            HashSet historico = new HashSet();
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
        var curso = this.cursoService.listCursoById(idCurso);
        var associado = this.listAssociadoById(idAssociado);

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

        var curso = this.cursoService.listCursoById(idCurso);
        var associado = this.listAssociadoById(idAssociado);

        if (associado.getCursosFavoritos() == null) {
            HashSet<Curso> cursosFavoritos = new HashSet();
            cursosFavoritos.add(curso);
            associado.setCursosFavoritos(cursosFavoritos);

        } else {
            associado.getCursosFavoritos().add(curso);
        }

        return this.associadoRepository.save(associado);
    }

    @Override
    public Associado concluirModuloCurso(ModulosCurso modulosCurso, String idAssociado) {
        var associado = this.listAssociadoById(idAssociado);

        if (associado.getModulosCursosCompletos() == null) {
            ArrayList<ModulosCurso> modulos = new ArrayList();
            modulos.add(modulosCurso);
            associado.setModulosCursosCompletos(modulos);
        } else {
            associado.getModulosCursosCompletos().add(modulosCurso);
        }

        return this.associadoRepository.save(associado);
    }


}
