package br.api.hallel;

import br.api.hallel.moduloAPI.service.main.MainService;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TesteData {

    @Test
    void dataFormatadaString(){
        Date date1 = null;

        try {
            date1 = new SimpleDateFormat("YYYY/MM/dd").parse("2005/12/23");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        Date date2 = MainService.getDataAtual();

        if(MainService.comparateDatas(date1,date2)){
            System.out.println("Retornou verdadeiro!");
        }else{
            System.out.println("Retornou falso");
        }

    }


}
