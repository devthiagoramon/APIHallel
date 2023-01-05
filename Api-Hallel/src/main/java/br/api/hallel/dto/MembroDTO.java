package br.api.hallel.dto;

import br.api.hallel.model.Membro;

public class MembroDTO {

    private String email;
    private String senha;
    
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return this.senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Membro toMembro(){
        Membro membro = new Membro();
        membro.setEmail(this.email);
        membro.setSenha(this.senha);
        return membro;
    }

    public MembroDTO() {
    }

    public MembroDTO(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }

}
