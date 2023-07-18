package br.api.hallel.moduloAPI.financeiroNovo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
@AllArgsConstructor
@NoArgsConstructor
@Data
public class EntradasFinanceiro {
    private String id;
    private CodigoEntradaFinanceiro codigo;
    private Date data;
    private Double valor;
    private MetodosPagamentosFinanceiro metodoPagamento;
}
