package br.api.hallel.dto;

import br.api.hallel.model.Administrador;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public class AdministradorDTO {

    private String nome;
    private String email;
    private String senha;
    private String senhaAcesso;

    public AdministradorDTO() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getSenhaAcesso() {
        return senhaAcesso;
    }

    public void setSenhaAcesso(String senhaAcesso) {
        this.senhaAcesso = senhaAcesso;
    }

    public Administrador toAdministrador(){
        Administrador administrador = new Administrador();
        administrador.setNome(this.getNome());
        administrador.setEmail(this.getEmail());
        administrador.setSenhaAcesso(this.getSenhaAcesso());
        administrador.setSenha(this.getSenha());
        return administrador;
    }
}
