package br.api.hallel.payload.requerimento;

import br.api.hallel.model.ReceitaFinanceira;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ReceitaReq {
    @NotNull(message = "O campo 'descricao' deve ser preenchido")
    @NotBlank(message = "O campo 'descricao' deve ser preenchido")
    private String descricaoReceita;
    @NotNull(message = "O campo 'valor' deve ser preenchido")
    @NotBlank(message = "O campo 'valor' deve ser preenchido")
    @DecimalMin(value = "0.10", message = "Valor da doação tem que superior a 0.10 centavos")
    private Double valor;
    @NotNull(message = "O campo 'data' deve ser preenchido")
    @NotBlank(message = "O campo 'data' deve ser preenchido")
    private String dataGasto;
    @NotNull(message = "O campo deve ser preenchido")
    @NotBlank(message = "O campo deve ser preenchido")
    private String usuarioReceita;
    @NotNull(message = "O campo 'para' deve ser preenchido")
    @NotBlank(message = "O campo 'para' deve ser preenchido")
    private String finalidadeReceita;

    public ReceitaFinanceira toGasto(){
        ReceitaFinanceira receitaDTO = new ReceitaFinanceira();

        receitaDTO.setValor(this.getValor());
        receitaDTO.setDescricaoReceita(this.getDescricaoReceita());
        receitaDTO.setUsuarioReceita(this.getUsuarioReceita());
        receitaDTO.setDataReceita(this.getDataGasto());

        return receitaDTO;
    }
}
