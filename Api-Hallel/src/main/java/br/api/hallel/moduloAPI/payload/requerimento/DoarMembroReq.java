package br.api.hallel.moduloAPI.payload.requerimento;

import br.api.hallel.moduloAPI.model.Doacao;
import br.api.hallel.moduloAPI.model.StatusDoacao;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoarMembroReq {
    @NotBlank(message = "Id do membro obrigatório!")
    private String idMembro;

    @NotNull(message = "Insira um valor!")
    private Double valor;

    public Doacao toDoacao(){
        Doacao doacao = new Doacao();
        doacao.setIdDonator(idMembro);
        doacao.setStatus(StatusDoacao.PENDENTE);
        doacao.setValor(getValor());
        doacao.setDate(new Date());
        return doacao;
    }

}
