package br.api.hallel.service.interfaces;


import br.api.hallel.model.ReceitaFinanceira;
import br.api.hallel.payload.requerimento.ReceitaReq;

import java.util.List;

public interface ReceitaInterface {

    ReceitaFinanceira createReceita(ReceitaFinanceira receitaFinanceira);
    ReceitaFinanceira listById(String id);
    List<ReceitaFinanceira> listAll();
    ReceitaFinanceira update(String id, ReceitaReq receita);
    void deleteReceita(String id);

}
