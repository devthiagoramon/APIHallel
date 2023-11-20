package br.api.hallel.moduloAPI.payload.requerimento;

import br.api.hallel.moduloAPI.model.DoacaoObjeto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoacaoObjetoReq {

    private String emailDoador;
    private String nomeDoador;
    private String tipoDoacao;
    private Integer quantidade;

    public DoacaoObjeto toDoacaoObjeto() {
        Date dataDoacao = new Date();
        return new DoacaoObjeto(getEmailDoador(),getNomeDoador(), getTipoDoacao(), dataDoacao, getQuantidade(), false);
    }
}
