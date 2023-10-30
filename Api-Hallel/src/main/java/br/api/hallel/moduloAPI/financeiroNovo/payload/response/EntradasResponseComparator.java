package br.api.hallel.moduloAPI.financeiroNovo.payload.response;

import java.util.Comparator;

public class EntradasResponseComparator implements Comparator<EntradaFinanceiroResponse> {
    @Override
    public int compare(EntradaFinanceiroResponse o1, EntradaFinanceiroResponse o2) {
        return o1.getData().compareTo(o2.getData());
    }
}
