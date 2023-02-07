package br.api.hallel.service;

import br.api.hallel.model.Associado;
import br.api.hallel.model.Transacao;
import br.api.hallel.payload.requerimento.TransacaoRequerimento;
import br.api.hallel.repository.AssociadoRepository;
import br.api.hallel.repository.TransacaoRepository;
import br.api.hallel.service.interfaces.TransacaoInterface;
import org.springframework.beans.factory.annotation.Autowired;

public class TransacaoService implements TransacaoInterface {

    @Autowired
    private TransacaoRepository repository;
    @Autowired
    private AssociadoRepository associadoRepository;

    @Override
    public Transacao transacao(TransacaoRequerimento requerimento){
        return this.repository.insert(requerimento.toTransacao());
    }

    @Override
    public Associado createAssociado(Associado associado) {

        if(associado.getMensalidadePaga()){
            System.out.println("Mensalidade paga");
            return this.associadoRepository.insert(associado);
        }
        System.out.println("Mensalidade não paga");
        return null;

    }


}
