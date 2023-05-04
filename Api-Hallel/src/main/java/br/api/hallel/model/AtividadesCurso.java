package br.api.hallel.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AtividadesCurso {
    private Integer numAtividade;
    private String nomeAtividade;
    private String linkAtividade;
}
