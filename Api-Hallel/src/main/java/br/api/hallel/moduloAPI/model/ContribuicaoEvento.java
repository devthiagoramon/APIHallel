package br.api.hallel.moduloAPI.model;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class ContribuicaoEvento {
    @Id
    private String id;
    private String nome;
    private String emailPagador;
    private String tipoContribuicao;
    private Integer quantidade;
    private Eventos eventos;
}
