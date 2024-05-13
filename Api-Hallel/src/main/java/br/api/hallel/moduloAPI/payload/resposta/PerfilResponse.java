package br.api.hallel.moduloAPI.payload.resposta;

import br.api.hallel.moduloAPI.model.StatusMembro;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PerfilResponse {

    private String nome;
    private Date dataAniversario;
    private String email;
    private StatusMembro status;
    private Integer idade;
    private String image;
    private String cpf;
    private String Telefone;

}
