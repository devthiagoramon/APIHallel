package br.api.hallel.moduloAPI.payload.requerimento;

import br.api.hallel.moduloAPI.payload.requerimento.CustomDateDeserializer;
import br.api.hallel.moduloAPI.model.VoluntarioEvento;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SeVoluntariarEventoReq {

    private String nome;
    private String email;
    private String Sexo;
    private String numeroDeTelefone;
    private String cpf;
    private String preferencia;

    @JsonDeserialize(using = CustomDateDeserializer.class)
    private Date dataNascimento;

    private String idEvento;

    public SeVoluntariarEventoReq toSeVoluntariarEventoRequest(VoluntarioEvento voluntarioEvento) {
        SeVoluntariarEventoReq request = new SeVoluntariarEventoReq();
        request.setNome(getNome());
        request.setIdEvento(getIdEvento());
        request.setEmail(getEmail());
        request.setSexo(getSexo());
        request.setDataNascimento(getDataNascimento());
        request.setCpf(getCpf());
        request.setNumeroDeTelefone(getNumeroDeTelefone());
        request.setPreferencia(getPreferencia());

        return request;
    }

    public VoluntarioEvento toVoluntarioEvento() {
        VoluntarioEvento voluntario = new VoluntarioEvento();
        voluntario.setNome(this.getNome());
        voluntario.setEmail(this.getEmail());
        voluntario.setSexo(this.getSexo());
        voluntario.setDataNascimento(this.getDataNascimento());
        voluntario.setNumeroDeTelefone(this.getNumeroDeTelefone());
        voluntario.setCpf(this.getCpf());
        voluntario.setPreferencia(this.getPreferencia());
        return voluntario;
    }
}
