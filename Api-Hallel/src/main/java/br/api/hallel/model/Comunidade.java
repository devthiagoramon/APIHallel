package br.api.hallel.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.repository.Aggregation;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

@Document
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Comunidade {

    @Id
    private String id;

    private String nome = "Comunidade Hallel";
    private double caixaTotal;
    private double lucroMensal;
    private double gastoMensal;
    private Date date;
    private ArrayList<Doacao> doacaoTotal;
    private ArrayList<Doacao> doacaoMensais;
    private ArrayList<Transacao> transacoes;
    private ArrayList<Transacao> transacoesMensais;
    private ArrayList<Double> lucroDoacao;
    private ArrayList<Double> lucroEventos;
    private ArrayList<Double> despesaEventos;
    private ArrayList<Double> lucroTransacao;

    public void setDoacaoMensais(ArrayList<Doacao> doacaoMensais) {
        this.doacaoMensais = doacaoMensais;
    }

    public void setTransacoesMensais(ArrayList<Transacao> transacoesMensais) {
        this.transacoesMensais = transacoesMensais;
    }


}
