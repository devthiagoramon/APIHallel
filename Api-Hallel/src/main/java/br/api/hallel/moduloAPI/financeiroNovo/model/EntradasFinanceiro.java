package br.api.hallel.moduloAPI.financeiroNovo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SortComparator;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
@AllArgsConstructor
@NoArgsConstructor
@Data
public class EntradasFinanceiro implements Comparable<EntradasFinanceiro>{
    private String id;
    private CodigoEntradaFinanceiro codigo;
    private Date date;
    private Double valor;
    private MetodosPagamentosFinanceiro metodoPagamento;

    @Override
    public int compareTo(@NotNull EntradasFinanceiro o) {
        if(this.date != null) {
            return o.getDate().compareTo(this.getDate());
        }else{
            return -1;
        }
    }
}
