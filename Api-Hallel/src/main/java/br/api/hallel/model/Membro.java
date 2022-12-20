package br.api.hallel.model;

import java.sql.Date;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Membro extends Usuario{
    
    private String nome;
    private String senha;
    private Date dataAniversario;
    private String filiacao;

    public Membro(String ip, String nome, String senha, Date dataAniversario, String filiacao) {
        super(ip);
        this.nome = nome;
        this.senha = senha;
        this.dataAniversario = dataAniversario;
        this.filiacao = filiacao;
    }

    public Date getDataAniversario() {
        return this.dataAniversario;
    }

    public void setDataAniversario(Date dataAniversario) {
        this.dataAniversario = dataAniversario;
    }

    public Membro(){}

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getFiliacao() {
        return filiacao;
    }

    public void setFiliacao(String filiacao) {
        this.filiacao = filiacao;
    }
    
}
