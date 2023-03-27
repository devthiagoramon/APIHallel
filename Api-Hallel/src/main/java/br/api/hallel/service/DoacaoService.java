package br.api.hallel.service;

import br.api.hallel.model.Doacao;
import br.api.hallel.payload.requerimento.DoacaoReq;
import br.api.hallel.payload.resposta.DoacoesDinheiroListaAdmResponse;
import br.api.hallel.repository.DoacaoRepository;
import br.api.hallel.service.interfaces.DoacaoInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DoacaoService implements DoacaoInterface {

    @Autowired
    private DoacaoRepository repository;
    @Autowired
    private ComunidadeService doacaoService;

    Logger logger = LoggerFactory.getLogger(DoacaoService.class);


    //ADICIONA OU ATUALIZA UMA DOAÇÃO
    @Override
    public Doacao doar(DoacaoReq doacaoReq) {
        logger.info("DOAÇÃO SALVA!");

        doacaoService.atualizarDoacao(doacaoReq.toDoacao());
        return repository.insert(doacaoReq.toDoacao());
    }

    @Override
    public List<DoacoesDinheiroListaAdmResponse> listAllDoacoes() {
        return new DoacoesDinheiroListaAdmResponse().toListDoacoesDinheiroListaAdm(this.repository.findAll());
    }

    @Override
    public Doacao listDoacaoById(String id) {

        Optional<Doacao> optional = this.repository.findById(id);

        if (optional.isPresent()) {
            Doacao doacao = optional.get();


            logger.info("DOAÇÃO LISTADA COM SUCESSO");

            return doacao;

        }else{
            logger.warn("ID NÃO ENCONTRADO!");
            return null;
        }

    }


}
