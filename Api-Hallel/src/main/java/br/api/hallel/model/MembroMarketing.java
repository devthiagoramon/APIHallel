package br.api.hallel.model;

import java.sql.Date;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class MembroMarketing extends Membro {

    private String senhaAcesso;
    private Integer quantidadeAnuncio;

    public MembroMarketing(String ip, String nome, String senha, String filiacao, String senhaAcesso,
            Integer quantidadeAnuncio) {
        super(ip, nome, senha, filiacao);
        this.senhaAcesso = senhaAcesso;
        this.quantidadeAnuncio = quantidadeAnuncio;
    }

    public MembroMarketing() {
    }
    
    public String getSenhaAcesso() {
        return senhaAcesso;
    }

    public void setSenhaAcesso(String senhaAcesso) {
        this.senhaAcesso = senhaAcesso;
    }

    public Integer getQuantidadeAnuncio() {
        return quantidadeAnuncio;
    }

    public void setQuantidadeAnuncio(Integer quantidadeAnuncio) {
        this.quantidadeAnuncio = quantidadeAnuncio;
    }

}
