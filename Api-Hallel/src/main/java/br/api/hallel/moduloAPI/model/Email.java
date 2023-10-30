package br.api.hallel.moduloAPI.model;

import lombok.Data;


@Data
public class Email {

    private String destinatario;
    private String titulo;
    private String conteudo;
    private String anexo;

}
