package br.api.hallel.moduloAPI.financeiroNovo.payload.response;

import java.util.Comparator;

public class SaidaFinanceiroResponseComparator implements Comparator<SaidaFinanceiroResponse> {


    @Override
    public int compare(SaidaFinanceiroResponse o1, SaidaFinanceiroResponse o2) {
        return o1.getData().compareTo(o2.getData());
    }
}
