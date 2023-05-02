package br.api.hallel;

import br.api.hallel.model.ReceitaFinanceira;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class Generics {

    @Test
    void inspectType(){
        ArrayList<ReceitaFinanceira> arrayTeste = new ArrayList<>();

        arrayTeste.stream().forEach(item -> System.out.println(item.getClass()));

    }

}
