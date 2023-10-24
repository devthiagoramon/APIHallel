package br.api.hallel.moduloAPI.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Doacao {

    @Id
    private String id;
    private String emailDoador;
    private String descricao;
    private TipoDoacao tipo;
    private String dataDoacao;
    private Double valorDoacao;
    private Double totalDoacao;

}
