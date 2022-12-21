package br.api.hallel.model;

import java.sql.Date;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class MembroMarketing extends Membro {

    private String senhaAcesso;

    
    public MembroMarketing() {
    }

    public MembroMarketing(String nome, String senha, String email, Date dataAniversario,
            StatusMembro status, String senhaAcesso) {
        super(nome, senha, email, dataAniversario, status);
        this.senhaAcesso = senhaAcesso;
    }

    public String getSenhaAcesso() {
        return senhaAcesso;
    }

    public void setSenhaAcesso(String senhaAcesso) {
        this.senhaAcesso = senhaAcesso;
    }

}
