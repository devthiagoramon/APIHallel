package br.api.hallel.payload.requerimento;

import jakarta.validation.constraints.NotBlank;

public class LoginRequerimentoGoogle {

    @NotBlank
    private String email;
    @NotBlank
    private String nome;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
