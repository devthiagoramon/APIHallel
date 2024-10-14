package br.api.hallel.moduloAPI.payload.resposta;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EditPerfilResponse {

    private String nome;
    private String email;
    private String image;
    private String cpf;
    private String Telefone;

}
