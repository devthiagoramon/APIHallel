package br.api.hallel.moduloAPI.service.interfaces;


import br.api.hallel.moduloAPI.model.EntradaFinanceiraResponseUltimas;
import br.api.hallel.moduloAPI.model.ReceitaFinanceira;
import br.api.hallel.moduloAPI.payload.requerimento.ReceitaReq;
import br.api.hallel.moduloAPI.payload.resposta.ReceitasDiaAtualResponse;
import br.api.hallel.moduloAPI.payload.resposta.ReceitasSemanaAtualResponse;

import java.util.List;

public interface ReceitaInterface {

    ReceitaFinanceira createReceita(ReceitaFinanceira receitaFinanceira);
    ReceitaFinanceira listById(String id);
    List<ReceitaFinanceira> listAll(String mes, String ano);
    ReceitaFinanceira update(String id, ReceitaReq receita);
    void deleteReceita(String id);

    List<EntradaFinanceiraResponseUltimas> listUltimasEntradas();

    ReceitasDiaAtualResponse getValorTotalByThisDay();

    ReceitasSemanaAtualResponse getValorTotalByThisWeek();
}
