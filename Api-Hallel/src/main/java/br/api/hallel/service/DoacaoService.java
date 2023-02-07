package br.api.hallel.service;

import br.api.hallel.model.Doacao;
import br.api.hallel.payload.requerimento.DoacaoReq;
import br.api.hallel.repository.DoacaoRepository;
import br.api.hallel.service.interfaces.DoacaoInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DoacaoService implements DoacaoInterface {

    private DoacaoRepository repository;
    private ComunidadeService doacaoService;
    @Override
    public Doacao doar(DoacaoReq doacaoReq) {
        doacaoService.atualizarDoacao(doacaoReq.toDoacao());
        return repository.insert(doacaoReq.toDoacao());
    }
}