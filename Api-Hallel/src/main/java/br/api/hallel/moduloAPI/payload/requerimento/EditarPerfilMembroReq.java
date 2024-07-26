package br.api.hallel.moduloAPI.payload.requerimento;

import br.api.hallel.moduloAPI.model.Membro;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public class EditarPerfilMembroReq {

    @NotNull
    private String id;

    @NotNull
    @NotBlank
    private String nome;

    @NotNull
    @NotBlank
    private String email;

    private String telefone;
    private Date dataNascimento;
    private String cpf;
    private String image;

    public EditarPerfilMembroReq(String id, String nome, String email,
                                 String telefone,
                                 Date dataNascimento,
                                 String cpf, String image) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.dataNascimento = dataNascimento;
        this.cpf = cpf;
        this.image = image;
    }

    public EditarPerfilMembroReq() {
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }


    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Membro toMembro() {
        Membro membro = new Membro();
        membro.setEmail(this.getEmail());
        membro.setCpf(this.getCpf());
        membro.setTelefone(this.getTelefone());
        membro.setDataNascimento(this.getDataNascimento());
        membro.setNome(this.getNome());
        membro.setImage(this.getImage());
        return membro;
    }
}
