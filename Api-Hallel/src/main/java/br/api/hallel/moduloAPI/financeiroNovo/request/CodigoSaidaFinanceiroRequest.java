package br.api.hallel.moduloAPI.financeiroNovo.request;


import br.api.hallel.moduloAPI.financeiroNovo.model.CodigoSaidaFinanceiro;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CodigoSaidaFinanceiroRequest {
    private Double numeroCodigo;
    private String nomeCodigo;

    public CodigoSaidaFinanceiro toCodigoSaidaFinanceiro(){
        CodigoSaidaFinanceiro codigoSaidaFinanceiro = new CodigoSaidaFinanceiro();
        codigoSaidaFinanceiro.setNumeroCodigo(this.getNumeroCodigo());
        codigoSaidaFinanceiro.setNomeCodigo(this.getNomeCodigo());
        return codigoSaidaFinanceiro;
    }
}
