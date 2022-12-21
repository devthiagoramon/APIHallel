package br.api.hallel.model;

import java.sql.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Usuario {
    
    @Id
    public String id;
    public Date dataAcesso;

    public Usuario(){
    }


    public Usuario(Date dataAcesso, String ip) {
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
