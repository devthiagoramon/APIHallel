package br.api.hallel.moduloAPI.payload.requerimento;

import br.api.hallel.moduloAPI.model.VoluntarioEvento;
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
    private Date dataNascimento;
    private String idEvento;


    public SeVoluntariarEventoReq toSeVoluntariarEventoRequest( VoluntarioEvento voluntarioEvento){
        SeVoluntariarEventoReq request = new SeVoluntariarEventoReq();
        request.setNome(getNome());
        request.setIdEvento(getIdEvento());
        request.setEmail(getEmail());
        request.setSexo(getSexo());
        request.setDataNascimento(getDataNascimento());
        return request;

    }


    public VoluntarioEvento toVoluntarioEvento(){
        VoluntarioEvento voluntario = new VoluntarioEvento();
        voluntario.setNome(this.getNome());
        voluntario.setEmail(this.getEmail());
        voluntario.setSexo(this.getSexo());
        voluntario.setDataNascimento(this.getDataNascimento());
        return voluntario;

    }




}
