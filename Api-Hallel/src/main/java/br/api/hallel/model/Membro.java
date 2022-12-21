package br.api.hallel.model;

import java.sql.Date;

import javax.swing.ImageIcon;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Membro extends Usuario{
    
    private String nome;
    private String senha;
    private String email;
    private Date dataAniversario;
    private StatusMembro status;
    private Integer idade;
    private ImageIcon image;
    private String funcao;
    private Boolean doador;
    private Integer quantidadeDoacoes;

    public Membro(String nome, String senha, String email, Date dataAniversario, StatusMembro status) {
        this.nome = nome;
        this.senha = senha;
        this.email = email;
        this.dataAniversario = dataAniversario;
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

    public ImageIcon getImage() {
        return image;
    }

    public void setImage(ImageIcon image) {
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

    public Date getDataAniversario() {
        return this.dataAniversario;
    }

    public void setDataAniversario(Date dataAniversario) {
        this.dataAniversario = dataAniversario;
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
