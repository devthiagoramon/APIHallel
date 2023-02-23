package br.api.hallel.payload.requerimento;

import br.api.hallel.model.GastoFinanceiro;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class GastoReq {

    @NotNull(message = "O campo 'descricao' deve ser preenchido")
    @NotBlank(message = "O campo 'descricao' deve ser preenchido")
    private String descricaoGasto;
    @NotNull(message = "O campo 'valor' deve ser preenchido")
    @NotBlank(message = "O campo 'valor' deve ser preenchido")
    @DecimalMin(value = "0.10", message = "Valor da doação tem que superior a 0.10 centavos")
    private Double valor;
    @NotNull(message = "O campo 'para' deve ser preenchido")
    @NotBlank(message = "O campo 'para' deve ser preenchido")
    private String finalidadeGasto;
    @NotNull(message = "O campo 'data' deve ser preenchido")
    @NotBlank(message = "O campo 'data' deve ser preenchido")
    private String dataGasto;
    @NotNull(message = "O campo deve ser preenchido")
    @NotBlank(message = "O campo deve ser preenchido")
    private String usuarioGasto;

    public GastoFinanceiro toGasto(){
        GastoFinanceiro gastoDTO = new GastoFinanceiro();
        gastoDTO.setValor(this.getValor());
        gastoDTO.setDescricaoGasto(this.getDescricaoGasto());
        gastoDTO.setUsuarioGasto(this.getUsuarioGasto());
        gastoDTO.setDataGasto(this.getDataGasto());
        gastoDTO.setFinalidadeGasto(this.getFinalidadeGasto());

        return gastoDTO;
    }

}
