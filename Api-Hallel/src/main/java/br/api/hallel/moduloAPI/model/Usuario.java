package br.api.hallel.moduloAPI.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Usuario {
    
    @Id
    public String id;
    public String dataAcesso;

    public Usuario(){
    }


    public Usuario(String dataAcesso, String ip) {
        this.dataAcesso = dataAcesso;
    }


    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getDataAcesso() {
        return dataAcesso;
    }
    public void setDataAcesso(String dataAcesso) {
        this.dataAcesso = dataAcesso;
    }

    

}
