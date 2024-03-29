package br.api.hallel.moduloAPI.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document
@Data
@NoArgsConstructor
public class Sorteio {
    @Id
    private String id;
    private String titulo;
    private String descricao;
    private Date data;
    private List<ItensSorteio> itensSorteios;
    private List<Associado> sorteioAssociados;
    private List<Associado> ultimosAssociados;

}
