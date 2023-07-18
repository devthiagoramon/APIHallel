package br.api.hallel.moduloAPI.financeiroNovo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@NoArgsConstructor
@Data
@AllArgsConstructor
@Document
public class SaidaFinanceiro {

    private String id;
    private CodigoSaidaFinanceiro codigo;
    private Date data;
    private Double valor;
    private MetodosPagamentosFinanceiro metodoPagamento;

}
