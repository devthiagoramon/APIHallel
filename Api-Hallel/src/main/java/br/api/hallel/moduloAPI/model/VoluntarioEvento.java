package br.api.hallel.moduloAPI.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VoluntarioEvento {

    private String id;
    private String nome;
    private String email;
    private String Sexo;
    private Date dataNascimento;

    public VoluntarioEvento(String nome, String email, String sexo, Date dataNascimento) {

        this.nome = nome;
        this.email = email;
        Sexo = sexo;
        this.dataNascimento = dataNascimento;
    }
}
