package br.api.hallel.moduloAPI.financeiroNovo.payload.request;

import br.api.hallel.moduloAPI.financeiroNovo.model.CodigoEntradaFinanceiro;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CodigoEntradaFinanceiroRequest {
    private Double numeroCodigo;
    private String nomeCodigo;

    public CodigoEntradaFinanceiro toCodigoSaidaFinanceiro(){
        CodigoEntradaFinanceiro codigoEntradaFinanceiro = new CodigoEntradaFinanceiro();
        codigoEntradaFinanceiro.setNumeroCodigo(this.getNumeroCodigo());
        codigoEntradaFinanceiro.setNomeCodigo(this.getNomeCodigo());
        return codigoEntradaFinanceiro;
    }
}
