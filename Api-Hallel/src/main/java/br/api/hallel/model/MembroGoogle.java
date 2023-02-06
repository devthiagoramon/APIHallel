package br.api.hallel.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class MembroGoogle extends  Membro{

    private String nomeGoogle;
    private String emailGoogle;

    public MembroGoogle(){

    }

    public String getNomeGoogle() {
        return nomeGoogle;
    }

    public void setNomeGoogle(String nomeGoogle) {
        this.nomeGoogle = nomeGoogle;
    }

    public String getEmailGoogle() {
        return emailGoogle;
    }

    public void setEmailGoogle(String emailGoogle) {
        this.emailGoogle = emailGoogle;
    }
}
