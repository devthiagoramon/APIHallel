package br.api.hallel.payload.resposta;

import br.api.hallel.model.StatusMembro;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PerfilResponse {

    private String nome;
    private String dataAniversario;
    private String email;
    private StatusMembro status;
    private Integer idade;
}
