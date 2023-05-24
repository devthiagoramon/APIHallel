package br.api.hallel.payload.resposta;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CursosAssociadoRes {

    private String id;
    private String nome;
    private String imagem;

}
