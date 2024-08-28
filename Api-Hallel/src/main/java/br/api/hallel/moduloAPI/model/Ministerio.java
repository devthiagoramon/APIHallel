package br.api.hallel.moduloAPI.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Ministerio {

    @Id
    private String id;
    private String nome;
    private String descricao;
    private String imagem;
    private List<String> objetivos;
    private String coordenadorId;
    private String viceCoordenadorId;

    public Ministerio(String nome, String coordenadorId,
                      String viceCoordenadorId) {
        this.nome = nome;
        this.coordenadorId = coordenadorId;
        this.viceCoordenadorId = viceCoordenadorId;
    }
}
