package br.api.hallel.moduloAPI.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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

    public void setDoacaoMensais(ArrayList<Doacao> doacaoMensais) {
        this.doacaoMensais = doacaoMensais;
    }

    public void setTransacoesMensais(ArrayList<Transacao> transacoesMensais) {
        this.transacoesMensais = transacoesMensais;
    }


}
