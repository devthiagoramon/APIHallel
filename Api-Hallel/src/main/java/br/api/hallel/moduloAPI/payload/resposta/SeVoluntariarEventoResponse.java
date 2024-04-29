package br.api.hallel.moduloAPI.payload.resposta;

import br.api.hallel.moduloAPI.model.VoluntarioEvento;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SeVoluntariarEventoResponse {


    private String id;
    private String nome;
    private String email;
    private String Sexo;
    private Date dataNascimento;
    private String numeroDeTelefone;
    private String cpf;
    private String preferencia;


    public SeVoluntariarEventoResponse toResponse(VoluntarioEvento voluntarioEvento){
        return new SeVoluntariarEventoResponse(voluntarioEvento.getId(),voluntarioEvento.getNome() ,voluntarioEvento.getEmail(),
                voluntarioEvento.getSexo(),voluntarioEvento.getDataNascimento(),voluntarioEvento.getNumeroDeTelefone(),
                voluntarioEvento.getCpf(),voluntarioEvento.getPreferencia()
                );


    }


}
