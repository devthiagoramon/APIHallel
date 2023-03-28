package br.api.hallel.payload.requerimento;

import br.api.hallel.model.DoacaoObjeto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoacaoObjetoReq {

    private String emailDoador;
    private String descricao;
    private String dataDoacao;

    private String imagem;
    private Integer quantidade;

    public DoacaoObjeto toDoacaoObjeto() {
        return new DoacaoObjeto(getEmailDoador(), getDescricao(), getDataDoacao(), getImagem(), getQuantidade(), false);
    }
}
