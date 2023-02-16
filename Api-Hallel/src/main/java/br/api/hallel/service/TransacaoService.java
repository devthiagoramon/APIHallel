package br.api.hallel.service;

import br.api.hallel.model.Associado;
import br.api.hallel.model.Comunidade;
import br.api.hallel.model.Transacao;
import br.api.hallel.payload.requerimento.TransacaoRequerimento;
import br.api.hallel.repository.AssociadoRepository;
import br.api.hallel.repository.TransacaoRepository;
import br.api.hallel.service.interfaces.TransacaoInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Service
public class TransacaoService implements TransacaoInterface {

    @Autowired
    private TransacaoRepository repository;
    @Autowired
    private AssociadoRepository associadoRepository;
    @Autowired
    private ComunidadeService comunidadeService;

    @Override
    public Transacao transacao(TransacaoRequerimento requerimento){


        return this.repository.insert(requerimento.toTransacao());
    }

    @Override
    public Associado createAssociado(Associado associado) {


        if (associado.getMensalidadePaga()) {

            System.out.println("Mensalidade paga");

            Optional<Associado> optional = this.associadoRepository.findById(associado.getId());

            if(compareDates(associado)) {

                if (optional.isPresent()) {

                    Associado associadoOptional = optional.get();
                    associadoOptional.setIsAssociado(true);
                    associado.setIsAssociado(associadoOptional.getIsAssociado());
                    System.out.println("" + associadoOptional.getIsAssociado());
                    System.out.println("" + associado.getIsAssociado());

                    System.out.println("Mantém como Associado");

                } else {
                    associado.setIsAssociado(true);

                    System.out.println("Inserir como novo Associado");
                    return this.associadoRepository.insert(associado);

                }
            }else{
                System.out.println("Mensalidade em pendência");
                associado.setIsAssociado(false);
            }

        } else {
            System.out.println("Mensalidade não paga");
            associado.setIsAssociado(false);

        }

        System.out.println("Final do método");
        return this.associadoRepository.save(associado);
    }


    private Boolean compareDates(Associado associado){

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        try {
            Date date = format.parse(associado.getTransacao().getDataExp());
            Date dateAtual = format.parse(getDataAtual());

            if(date.compareTo(dateAtual) < 0){
                return false;
            }else {
                return true;
            }

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }

    private String getDataAtual(){
        Date dataAtual = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        return format.format(dataAtual);
    }

}
