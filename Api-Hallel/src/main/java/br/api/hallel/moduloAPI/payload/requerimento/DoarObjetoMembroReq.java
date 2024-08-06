package br.api.hallel.moduloAPI.payload.requerimento;

import br.api.hallel.moduloAPI.model.Doacao;
import br.api.hallel.moduloAPI.model.StatusDoacao;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoarObjetoMembroReq {
    @NotBlank(message = "Id do membro obrigatório!")
    private String idMembro;

    @NotBlank(message = "Nome do objeto obrigatório!")
    private String nameObjeto;

    @NotNull(message = "Insira a quantidade de objetos!")
    private Double valor;

    public Doacao toDoacao(){
        Doacao doacao = new Doacao();
        doacao.setIdDonator(idMembro);
        doacao.setStatus(StatusDoacao.PENDENTE);
        doacao.setObjeto(true);
        doacao.setNameObjeto(getNameObjeto());
        doacao.setDate(new Date());
        doacao.setValor(getValor());
        return doacao;
    }
}
