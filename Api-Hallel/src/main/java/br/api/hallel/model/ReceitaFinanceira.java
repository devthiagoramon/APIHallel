package br.api.hallel.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReceitaFinanceira {

    @Id
    private String id;
    private String descricaoReceita;
    private Double valor;
    private String dataReceita;
    private String usuarioReceita;
    private boolean isObjeto;

}
