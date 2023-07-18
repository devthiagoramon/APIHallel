package br.api.hallel.moduloAPI.financeiroNovo.request;

import br.api.hallel.moduloAPI.financeiroNovo.model.*;
import br.api.hallel.moduloAPI.model.Membro;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoacaoRequest {

    private CodigoEntradaFinanceiro codigo;
    private Date data;
    private Double valor;
    private MetodosPagamentosFinanceiro metodoPagamento;
    private Membro usuarioDoador;

    public Doacoes toDoacoes() {
        Doacoes doacao = new Doacoes();
        doacao.setCodigo(this.getCodigo());
        doacao.setData(this.getData());
        doacao.setMetodoPagamento(this.getMetodoPagamento());
        doacao.setValor(this.getValor());
        doacao.setUsuarioDoador(this.getUsuarioDoador());
        return doacao;
    }


}
