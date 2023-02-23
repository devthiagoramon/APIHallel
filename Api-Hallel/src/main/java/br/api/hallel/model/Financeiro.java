package br.api.hallel.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Document
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Financeiro {

    @Id
    private String id;
    private ArrayList<GastoFinanceiro> gastos;
    private ArrayList<ReceitaFinanceira> receita;
    private ArrayList<Double> valorGastos;
    private ArrayList<Double> valorReceitas;
    private Double gastoProvisorio;
    private Double receitaProvisoria;
    private Double lucro;

}
