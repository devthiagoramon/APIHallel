package br.api.hallel.moduloAPI.service.interfaces;

import br.api.hallel.moduloAPI.model.GastoFinanceiro;
import br.api.hallel.moduloAPI.model.SaidaFinanceiraResponseUltimas;
import br.api.hallel.moduloAPI.payload.requerimento.GastoReq;

import java.util.List;

public interface GastoInterface {

    GastoFinanceiro createGasto(GastoFinanceiro gastoFinanceiro);
    GastoFinanceiro listById(String id);
    List<GastoFinanceiro> listAll();
    GastoFinanceiro update(String id, GastoReq gasto);
    void deleteGasto (String id);

    List<SaidaFinanceiraResponseUltimas> listUltimasSaidas();
}
