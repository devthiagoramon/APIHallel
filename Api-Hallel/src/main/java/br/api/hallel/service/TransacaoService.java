package br.api.hallel.service;

import br.api.hallel.model.Associado;
import br.api.hallel.model.AssociadoRole;
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

    //SALVA A TRANSACAO NO BANCO DE DADOS
    @Override
    public Transacao transacao(TransacaoRequerimento requerimento){

        return this.repository.insert(requerimento.toTransacao());
    }

    //MÉTODO PARA CRIAR UM ASSOCIADO
    @Override
    public Associado createAssociado(Associado associado) {

        // PRIMEIRA VERIFICAÇÃO DO MÉTODO

        //VERIFICA SE A MENSALIDADE DO ASSOCIADO FOI PAGA
        if (associado.getIsPago()) {

            //SE SIM, ELE VAI OCORRE UMA BUSCA DO USUÁRIO COMO ASSOCIADO

            System.out.println("Mensalidade paga");

            Optional<Associado> optional = this.associadoRepository.findById(associado.getId());

            //VERIFICAÇÃO DA DATA QUE FOI PAGA A MENSALIDADE
            if(compareDates(associado)) {

                //A BUSCA REALIZADA ANTERIORMENTE DO ASSOCIADO, VAI SER CONFIRMADO AQUI
                if (optional.isPresent()) {

                    //SE FOI ELE FOR ASSOCIADO JÁ, ELE MANTÉM COMO ASSOCIADO
                    Associado associadoOptional = optional.get();
                    associadoOptional.setIsAssociado(AssociadoRole.ATIVO);
                    associado.setIsAssociado(associadoOptional.getIsAssociado());

                    System.out.println("Mantém como Associado");

                } else {

                    //SE ELE NÃO FOR ASSOCIADO AINDA, ELE É INSERIDO COMO UM NOVO ASSOCIADO
                    associado.setIsAssociado(AssociadoRole.ATIVO);

                    System.out.println("Inserir como novo Associado");
                    return this.associadoRepository.insert(associado); //SALVO NO BD COMO NOVO ASSOCIADO

                }
            }else{
                /*
                    SE ELE NÃO PAGOU A MENSALIDADE ATÉ A DATA ESTIPULADA,
                    VAI FICAR COMO PENDENTE
                 */

                System.out.println("Mensalidade em pendência");
                associado.setIsAssociado(AssociadoRole.PENDENTE);
            }

        } else {

            /*CASO A PRIMEIRA VERIFICAÇÃO FOR FALSA, OU SEJA, NÃO FOR PAGA A MENSALIDADE,
            VAI FICAR COMO INATIVO
            */
            System.out.println("Mensalidade não paga");
            associado.setIsAssociado(AssociadoRole.INATIVO);

        }

        System.out.println("Final do método");
        return this.associadoRepository.save(associado); //VAI SALVAR TODAS AS ALTERAÇÕES FEITAS NOS IF'S ACIMA
    }


    //MÉTODO PARA A COMPARAÇÃO DE DATAS

    /*ESSE MÉTODO ESTÁ SENDO UTILIZADO NO MÉTODO ANTERIOR, PARA VERIFICAR A DATA ATUAL vs DATA
    EM QUE A MENSALIDADE FOI PAGA
     */
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

    //PEGA A DATA ATUAL DO SISTEMA
    private String getDataAtual(){
        Date dataAtual = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        return format.format(dataAtual);
    }

}
