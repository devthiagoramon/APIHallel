package br.api.hallel.service;

import br.api.hallel.model.Associado;
import br.api.hallel.model.Transacao;
import br.api.hallel.payload.requerimento.TransacaoRequerimento;
import br.api.hallel.repository.AssociadoRepository;
import br.api.hallel.repository.TransacaoRepository;
import br.api.hallel.service.interfaces.TransacaoInterface;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

public class TransacaoService implements TransacaoInterface {

    private TransacaoRepository repository;
    private AssociadoRepository associadoRepository;

    @Override
    public Transacao transacao(TransacaoRequerimento requerimento){
        return this.repository.insert(requerimento.toTransacao());
    }

    @Override
    public Associado createAssociado(Associado associado) {


        if(associado.getMensalidadePaga()) {

        if(compareDates(associado)){

            System.out.println("Mensalidade paga");

            Optional<Associado> optional = this.associadoRepository.findById(associado.getId());

            if (optional.isPresent()) {
                Associado associadoOptional = optional.get();
                associado.setIsAssociado(true);
                System.out.println("Mantém como Associado");

            } else {

                System.out.println("Inserir como novo Associado");
                return this.associadoRepository.insert(associado);

            }
        }else{
            System.out.println("Mensalidade em pendência");
        }

        }

        associado.setIsAssociado(false);
        System.out.println("Mensalidade não paga");
        return null;

    }

    private Boolean compareDates(Associado associado){

        if(associado.getTransacao().getDataExp().compareTo(getDataAtual()) == 1){
            return false;
        }else {
            return true;
        }

    }

    private String getDataAtual(){
        String dataFormatada = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
        return dataFormatada;
    }

}
