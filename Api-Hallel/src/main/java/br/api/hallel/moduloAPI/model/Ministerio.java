package br.api.hallel.moduloAPI.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ministerio {
    @Id
    private String id;
    private String nome;
    private String coordenadorId;
    private String viceCoordenadorId;

    public Ministerio(String nome, String coordenadorId,
                      String viceCoordenadorId) {
        this.nome = nome;
        this.coordenadorId = coordenadorId;
        this.viceCoordenadorId = viceCoordenadorId;
    }
}
