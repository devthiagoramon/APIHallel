package br.api.hallel.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@NoArgsConstructor
public class Produto {

    @Id
    private String id;
    private String nome;
    private String descricao;
    private String imagem;
    private Double preco;
    private String dataCadastrado;
    private String dataComprado;
    private Membro membro;
    private Integer estoque;
    private Double promocao;

}
