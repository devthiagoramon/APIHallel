package br.api.hallel.model;

import java.sql.Date;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Membro extends Usuario{
    
    private String nome;
    private String senha;
    private String email;
    private String dataNascimento;
    private StatusMembro status;
    private Integer idade;
    private String image;
    private String funcao;
    private Boolean doador;
    private Integer quantidadeDoacoes;

    public Membro(String nome, String senha, String email, String dataNascimento, StatusMembro status) {
        this.nome = nome;
        this.senha = senha;
        this.email = email;
        this.dataNascimento = dataNascimento;
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public StatusMembro getStatus() {
        return status;
    }

    public void setStatus(StatusMembro status) {
        this.status = status;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public Boolean getDoador() {
        return doador;
    }

    public void setDoador(Boolean doador) {
        this.doador = doador;
    }

    public Integer getQuantidadeDoacoes() {
        return quantidadeDoacoes;
    }

    public void setQuantidadeDoacoes(Integer quantidadeDoacoes) {
        this.quantidadeDoacoes = quantidadeDoacoes;
    }

    public String getDataNascimento() {
        return this.dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Membro(){}

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

    
}
