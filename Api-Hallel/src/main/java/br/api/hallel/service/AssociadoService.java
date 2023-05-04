package br.api.hallel.service;

import br.api.hallel.model.Associado;
import br.api.hallel.model.AtividadesCurso;
import br.api.hallel.payload.resposta.AssociadoPagamentosRes;
import br.api.hallel.repository.AssociadoRepository;
import br.api.hallel.service.interfaces.AssociadoInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AssociadoService implements AssociadoInterface {

    @Autowired
    private AssociadoRepository repository;
    @Autowired
    private CursoService cursoService;

    @Override
    public List<Associado> listAllAssociado() {
        return this.repository.findAll();
    }

    Logger logger = LoggerFactory.getLogger(AssociadoService.class);


    //LISTA TODOS OS ASSOCIADOS
    @Override
    public Associado listAssociadoById(String id) {

        logger.info("ASSOCIADO LISTADO!");

        return this.repository.findById(id).isPresent() ? this.repository.findById(id).get() : null;
    }

    //DELETA UM ASSOCIADO PELO ID DELE
    @Override
    public void deleteAssociado(String id) {
        Optional<Associado> optional = this.repository.findById(id);

        if (optional.isPresent()) {
            Associado associado = optional.get();

            logger.info("ASSOCIADO REMOVIDO!");

            this.repository.delete(associado);
        }

    }

    //ATUALIZA INFORMAÇÕES SOBRE O ASSOCIADO
    @Override
    public Associado updateAssociadoById(String id, Associado associado) {

        Optional<Associado> optional = this.repository.findById(id);

        if (optional.isPresent()) {
            Associado associadoOptional = optional.get();

            associadoOptional = associado;

            logger.info("ASSOCIADO ATUALIZADO!");

            System.out.println("Associado atualizado");
            return this.repository.save(associado);
        } else {
            logger.warn("ASSOCIADO NÃO ENCONTRADO!");

            return null;
        }
    }

    @Override
    public List<AssociadoPagamentosRes> getAllPagamentosAssociados() {

        List<Associado> associados = this.repository.findAll();

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
            ArrayList historico = new ArrayList();
            historico.add(curso);
            associado.setHistoricoCurso(historico);
        } else {
            associado.getHistoricoCurso().add(curso);
        }

        return this.repository.save(associado);
    }

    @Override
    public Associado concluirAtividade(String tituloAtividade, String idAssociado, String idCurso) {
        var curso = this.cursoService.listCursoById(idCurso);
        var associado = this.listAssociadoById(idAssociado);

        for (AtividadesCurso atividades : curso.getAtividades()) {
            if (atividades.getTitulo().equals(tituloAtividade)) {

                if (associado.getAssociadoAtividadesCurso() == null) {
                    HashMap hashMap = new HashMap();
                    hashMap.put(atividades, true);
                    associado.setAssociadoAtividadesCurso(hashMap);

                } else {
                    associado.getAssociadoAtividadesCurso().put(atividades, true);
                }
            }
        }

        return this.repository.save(associado);
    }

    @Override
    public Double desempenhoCurso(String idAssociado) {
        var curso = this.cursoService.listAllCursos();
        var associado = this.repository.findById(idAssociado).get();

        var completedsList = curso.stream().filter(completo -> completo.getCursoCompleted() == true).collect(Collectors.toList());

        Double quantidade = Double.valueOf(curso.size());
        Double completeds = Double.valueOf(completedsList.size());

        Double porcentagem = (completeds / quantidade);

        associado.setDesempenho(porcentagem);

        this.repository.save(associado);
        return associado.getDesempenho();
    }

}
