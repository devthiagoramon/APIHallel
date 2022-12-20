package br.api.hallel.model;

import java.sql.Date;

public class Usuario {
    
    public String id;
    public Date dataAcesso;

    public Usuario(){

    }

    public Usuario(Date dataAcesso) {
        this.dataAcesso = dataAcesso;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public Date getDataAcesso() {
        return dataAcesso;
    }
    public void setDataAcesso(Date dataAcesso) {
        this.dataAcesso = dataAcesso;
    }

    

}
