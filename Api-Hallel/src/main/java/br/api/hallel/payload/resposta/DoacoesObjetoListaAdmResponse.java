package br.api.hallel.payload.resposta;

import br.api.hallel.model.DoacaoObjeto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoacoesObjetoListaAdmResponse {

    private String id;
    private String descricao;
    private String imagem;
    private boolean isRecebido;
    private Integer quantidade;

    public List<DoacoesObjetoListaAdmResponse> toDoacoesObjLista(List<DoacaoObjeto> doacoesObjeto) {
        List<DoacoesObjetoListaAdmResponse> listaObjetos = new ArrayList<>();

        doacoesObjeto.forEach(doacaoObjeto -> {
            listaObjetos.add(toDoacaoObjetoLista(doacaoObjeto));
        });

        return listaObjetos;
    }

    private DoacoesObjetoListaAdmResponse toDoacaoObjetoLista(DoacaoObjeto doacaoObjeto) {
        return new DoacoesObjetoListaAdmResponse(doacaoObjeto.getId(),doacaoObjeto.getDescricao(), doacaoObjeto.getImagem(), doacaoObjeto.isRecebido(), doacaoObjeto.getQuantidade());
    }

}
