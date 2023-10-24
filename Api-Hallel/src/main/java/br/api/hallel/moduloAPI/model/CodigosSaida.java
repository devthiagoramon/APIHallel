package br.api.hallel.moduloAPI.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@AllArgsConstructor
@Data
@NoArgsConstructor
public class CodigosSaida {
    @Id
    private String id;
    private Double numCodigo;
    private String nomeCodigo;

}