package br.api.hallel.moduloAPI.model;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Alimentos {
    @Id
    private String id;
    private String tipo;
    private String dataValidade;
    private String nomeAlimento;
    private Integer quantidade;
    private Double peso;

    public Alimentos(String tipo, String dataValidade, String nomeAlimento, Integer quantidade, Double peso) {
        this.tipo = tipo;
        this.dataValidade = dataValidade;
        this.nomeAlimento = nomeAlimento;
        this.quantidade = quantidade;
        this.peso = peso;
    }
}
