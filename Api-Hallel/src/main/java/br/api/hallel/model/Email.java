package br.api.hallel.model;

import lombok.Data;


@Data
public class Email {

    private String destinatario;
    private String titulo;
    private String conteudo;
    private String anexo;

}
