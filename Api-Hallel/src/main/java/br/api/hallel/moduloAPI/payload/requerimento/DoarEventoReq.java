package br.api.hallel.moduloAPI.payload.requerimento;

import br.api.hallel.moduloAPI.model.Doacao;
import br.api.hallel.moduloAPI.model.StatusDoacao;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
public class DoarEventoReq {

    @NotBlank(message = "Id do membro obrigatório!")
    private String idMembro;

    @NotBlank(message = "Id do evento obrigatório!")
    private String idEvento;

    @NotNull(message = "Insira um valor!")
    private Double valor;

    public Doacao toDoacao(){
        Doacao doacao = new Doacao();
        doacao.setIdDonator(idMembro);
        doacao.setStatus(StatusDoacao.PENDENTE);
        doacao.setValor(getValor());
        doacao.setIdEvento(getIdEvento());
        doacao.setDate(new Date());
        return doacao;
    }
}
