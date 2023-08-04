package br.api.hallel.moduloAPI.financeiroNovo.payload.response;

import br.api.hallel.moduloAPI.financeiroNovo.model.Doacoes;
import br.api.hallel.moduloAPI.model.Membro;
import lombok.Data;

@Data
public class DoacoesResponse extends EntradaFinanceiroResponse{
    private Membro usuarioDoador;

    public DoacoesResponse toDoacaoResponseList(Doacoes doacoes){
        if(doacoes == null){
            return null;
        }
        DoacoesResponse response = new DoacoesResponse();
        response.setId(doacoes.getId());
        response.setCodigo(doacoes.getCodigo());
        response.setData(doacoes.getData());
        response.setValor(doacoes.getValor());
        response.setMetodoPagamento(doacoes.getMetodoPagamento());
        response.setUsuarioDoador(doacoes.getUsuarioDoador());
        return response;
    }

}
