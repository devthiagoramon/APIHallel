package br.api.hallel.service;

import br.api.hallel.model.Doacao;
import br.api.hallel.payload.requerimento.DoacaoReq;
import br.api.hallel.repository.DoacaoRepository;
import br.api.hallel.service.interfaces.DoacaoInterface;
import lombok.RequiredArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class DoacaoService implements DoacaoInterface {

    @Autowired
    private DoacaoRepository repository;
    @Autowired
    private ComunidadeService doacaoService;

    Logger logger =(Logger) LoggerFactory.getLogger(DoacaoService.class);


    //ADICIONA OU ATUALIZA UMA DOAÇÃO
    @Override
    public Doacao doar(DoacaoReq doacaoReq) {
        logger.info("DOAÇÃO SALVA!");

        doacaoService.atualizarDoacao(doacaoReq.toDoacao());
        return repository.insert(doacaoReq.toDoacao());
    }



}
