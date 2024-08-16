package br.api.hallel;
import br.api.hallel.moduloAPI.model.ReceitaFinanceira;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class Generics {

    @Test
    void inspectType(){
        ArrayList<ReceitaFinanceira> arrayTeste = new ArrayList<>();

        arrayTeste.stream().forEach(item -> System.out.println(item.getClass()));

    }

    @Test
    void isValidId(){
        String id = "63af3924d383023db80e3362";
        assert (ObjectId.isValid(id));

    }

}
