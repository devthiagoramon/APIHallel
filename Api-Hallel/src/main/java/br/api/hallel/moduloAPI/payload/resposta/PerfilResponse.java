package br.api.hallel.moduloAPI.payload.resposta;

import br.api.hallel.moduloAPI.model.Membro;
import br.api.hallel.moduloAPI.model.StatusMembro;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PerfilResponse {

    private String id;
    private String nome;
    private Date dataNascimento;
    private String email;
    private StatusMembro status;
    private Integer idade;
    private String image;
    private String cpf;
    private String Telefone;

    public PerfilResponse toPerfilResponse(Membro membro) {
        PerfilResponse response = new PerfilResponse();
        response.setId(membro.getId());
        response.setImage(membro.getImage());
        response.setIdade(membro.getIdade());
        response.setEmail(membro.getEmail());
        response.setCpf(membro.getCpf());
        response.setStatus(membro.getStatusMembro());
        response.setDataNascimento(membro.getDataNascimento());
        response.setTelefone(membro.getTelefone());
        return response;
    }
}
