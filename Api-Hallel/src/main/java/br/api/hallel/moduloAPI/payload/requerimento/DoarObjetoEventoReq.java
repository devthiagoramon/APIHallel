package br.api.hallel.moduloAPI.payload.requerimento;

import br.api.hallel.moduloAPI.model.Doacao;
import br.api.hallel.moduloAPI.model.StatusDoacao;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
public class DoarObjetoEventoReq {

    @NotBlank(message = "Id do membro obrigatório!")
    private String idMembro;

    @NotBlank(message = "Id do evento obrigatório!")
    private String idEvento;

    @NotBlank(message = "Nome do objeto obrigatório!")
    private String nameObjeto;


    @NotNull(message = "Insira a quantidade de objetos!")
    private Double valor;

    public Doacao toDoacao(){
        Doacao doacao = new Doacao();
        doacao.setIdDonator(idMembro);
        doacao.setStatus(StatusDoacao.PENDENTE);
        doacao.setObjeto(true);
        doacao.setIdEvento(getIdEvento());
        doacao.setNameObjeto(getNameObjeto());
        doacao.setDate(new Date());
        doacao.setValor(getValor());
        return doacao;
    }
}
