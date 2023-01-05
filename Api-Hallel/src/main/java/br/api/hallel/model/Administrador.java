package br.api.hallel.model;

import java.sql.Date;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Administrador extends Membro{

    private String senhaAcesso;
    private String cargo;

    public Administrador(String nome, String senha, String email, String dataAniversario, StatusMembro status,
            String senhaAcesso, String cargo) {
        super(nome, senha, email, dataAniversario, status);
        this.senhaAcesso = senhaAcesso;
        this.cargo = cargo;
    }

    public Administrador(){

    }

    public String getSenhaAcesso() {
        return senhaAcesso;
    }
    public void setSenhaAcesso(String senhaAcesso) {
        this.senhaAcesso = senhaAcesso;
    }
    public String getCargo() {
        return cargo;
    }
    public void setCargo(String cargo) {
        this.cargo = cargo;
    }


}
