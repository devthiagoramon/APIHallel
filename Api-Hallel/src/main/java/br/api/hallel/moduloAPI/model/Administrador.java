package br.api.hallel.moduloAPI.model;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
public class Administrador extends Membro{

    private String senhaAcesso;
    private String cargo;

    public Administrador(String nome, String senha, String email, Date dataAniversario, StatusMembro status,
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
