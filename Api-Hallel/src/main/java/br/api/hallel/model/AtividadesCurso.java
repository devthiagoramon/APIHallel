package br.api.hallel.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AtividadesCurso {

    private String titulo;
    private String descricao;
    private File arquivo;
    private String link;
    private Boolean isCompleted;

}
