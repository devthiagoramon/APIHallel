package br.api.hallel.moduloAPI.payload.requerimento;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.Set;

public class SolicitarCadastroRequerimento {

    @NotBlank
    private String nome;
    @NotBlank
    private String senha;
    @NotBlank
    @Email
    private String email;

    private Set<String> roles;

    public SolicitarCadastroRequerimento(String nome, String senha,
                                         String email) {
        this.nome = nome;
        this.senha = senha;
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }


}
