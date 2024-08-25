package br.api.hallel.integrationtests.ministerio.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class FuncaoMinisterioDTO {
    private String ministerioId;
    private String nome;
    private String descricao;
    private String icone;
    private String cor;
}
