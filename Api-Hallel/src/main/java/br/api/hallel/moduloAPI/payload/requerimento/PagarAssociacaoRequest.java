package br.api.hallel.moduloAPI.payload.requerimento;

import br.api.hallel.moduloAPI.financeiroNovo.model.MetodosPagamentosFinanceiro;
import br.api.hallel.moduloAPI.model.CartaoAssociado;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PagarAssociacaoRequest {
    private String idAssociado;
    private int numMetodoPagamento;
    @Nullable
    private CartaoAssociado cartaoAssociado;
    private String mes;
    private String ano;

}
