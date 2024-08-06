package br.api.hallel.moduloAPI.payload.requerimento;

import br.api.hallel.moduloAPI.model.Doacao;
import br.api.hallel.moduloAPI.model.StatusDoacao;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DoarReq {

    @NotNull(message = "Nome é obrigatório")
    @NotBlank(message = "Nome é obrigatório")
    private String nomeDonator;

    @NotNull(message = "Telefone é obrigatório")
    @NotBlank(message = "Telefone é obrigatório")
    private String telefoneDonator;

    @NotNull(message = "E-mail é obrigatório!")
    @NotBlank(message = "E-mail é obrigatório!")
    @Email(message = "Digite um e-mail valido!")
    private String emailDonator;

    @NotNull(message = "Insira um valor!")
    private Double valor;

    public Doacao toDoacao() {
        Doacao doacao = new Doacao();
        doacao.setDate(new Date());
        doacao.setAnonimo(true);
        doacao.setValor(getValor());
        doacao.setNameDonator(getNomeDonator());
        doacao.setTelefoneDonator(getTelefoneDonator());
        doacao.setEmailDonator(getEmailDonator());
        doacao.setStatus(StatusDoacao.PENDENTE);
        return doacao;
    }

}
