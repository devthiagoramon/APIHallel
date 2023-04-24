package br.api.hallel.model;

import lombok.Data;

@Data
public class Recompensa {
    private String nome;
    private String descricao;
    private Boolean isObjeto;
    private Associado associado;

}
