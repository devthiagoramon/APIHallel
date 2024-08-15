package br.api.hallel.moduloAPI.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@AllArgsConstructor
@Data
@NoArgsConstructor
public class FuncaoMinisterio {
    private String id;
    private String ministerioId;
    private String nome;
    private String descricao;
    private String icone;
    private String cor;
}
