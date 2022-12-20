package br.api.hallel.model;

import java.sql.Date;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Administrador extends MembroMarketing{

    private String especialidade;

    public Administrador(String ip, String nome, String senha, Date dataAniversario, String filiacao,
            String senhaAcesso, Integer quantidadeAnuncio, String especialidade) {
        super(ip, nome, senha, dataAniversario, filiacao, senhaAcesso, quantidadeAnuncio);
        this.especialidade = especialidade;
    }

    public Administrador() {
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

}
