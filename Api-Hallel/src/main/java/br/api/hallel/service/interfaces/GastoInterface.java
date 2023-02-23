package br.api.hallel.service.interfaces;

import br.api.hallel.model.GastoFinanceiro;
import br.api.hallel.payload.requerimento.GastoReq;

import java.util.List;

public interface GastoInterface {

    GastoFinanceiro createGasto(GastoFinanceiro gastoFinanceiro);
    GastoFinanceiro listById(String id);
    List<GastoFinanceiro> listAll();
    GastoFinanceiro update(String id, GastoReq gasto);
    void deleteGasto (String id);


}
