package br.api.hallel.moduloAPI.service.interfaces;


import br.api.hallel.moduloAPI.model.Financeiro;
import br.api.hallel.moduloAPI.model.GastoFinanceiro;
import br.api.hallel.moduloAPI.model.ReceitaFinanceira;

import java.util.List;

public interface FinanceiroInterface {

    Financeiro createFinanceiro(Financeiro financeiro);
    Financeiro update(Financeiro financeiro);
    void deleteFinanceiro (String id);
    Financeiro getFinanceiro();
    void salvarGasto(GastoFinanceiro gastoFinanceiro);
    void salvarReceita(ReceitaFinanceira receitaFinanceira);
    List<Financeiro> getReceitas();
    List<Financeiro> getGastos();
    Double lucro();
    Double lucroMensal();

    Double gastoMensal();
}