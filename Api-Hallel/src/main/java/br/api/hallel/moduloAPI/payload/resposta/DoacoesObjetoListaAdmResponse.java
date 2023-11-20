package br.api.hallel.moduloAPI.payload.resposta;

import br.api.hallel.moduloAPI.model.DoacaoObjeto;
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
    private String nomeDoador;
    private String tipoDoacao;
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
        return new DoacoesObjetoListaAdmResponse(doacaoObjeto.getId(),doacaoObjeto.getNomeDoador(), doacaoObjeto.getTipoDoacao(), doacaoObjeto.isRecebido(), doacaoObjeto.getQuantidade());
    }

}
