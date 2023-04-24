package br.api.hallel.service;

import br.api.hallel.model.Associado;
import br.api.hallel.payload.requerimento.RecompensaRequest;
import br.api.hallel.service.interfaces.RecompensaInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class RecompensaService implements RecompensaInterface {

    private Logger logger = LoggerFactory.getLogger(RecompensaService.class);
    @Autowired
    private AssociadoService associadoService;

    @Override
    public Associado sendRecompensa(RecompensaRequest recompensa, Associado associado) {


        if(compareDates(associado)){

            String dia =String.valueOf(getDataAtual()).substring(0,2);

            if(Integer.parseInt(dia) < 8){
                associado.setRecompensa(recompensa.toRecompensa());
                logger.info("ASSOCIADO COM RECOMPENSA");
            }

        }

        logger.info("FIM DO MÉTODO!");
        return this.associadoService.updateAssociadoById("63a3b4b7e406ee6f97314f27", associado);
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

    //PEGA A DATA ATUAL DO SISTEMA
    private String getDataAtual(){
        Date dataAtual = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        return format.format(dataAtual);
    }

}
