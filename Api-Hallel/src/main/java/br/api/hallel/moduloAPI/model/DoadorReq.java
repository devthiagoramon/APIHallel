package br.api.hallel.moduloAPI.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoadorReq {

    private String id;
    private String nome;
    private String email;
    private String numTelefone;
    private String dataNascimento;
    private String sexo;

}
