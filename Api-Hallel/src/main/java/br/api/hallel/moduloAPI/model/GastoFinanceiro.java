package br.api.hallel.moduloAPI.model;

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
public class GastoFinanceiro {

    @Id
    private String id;
    private String descricaoGasto;
    private Double valor;
    private String finalidadeGasto;
    private String dataGasto;
    private String usuarioGasto;

}
