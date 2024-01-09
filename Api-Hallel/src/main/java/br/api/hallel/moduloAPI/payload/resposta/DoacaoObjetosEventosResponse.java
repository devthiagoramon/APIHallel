package br.api.hallel.moduloAPI.payload.resposta;

import br.api.hallel.moduloAPI.model.DoacaoObjetosEventos;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoacaoObjetosEventosResponse {


    private String id;
    private String nomeDoObjeto;
    private Integer quantidade;

    public DoacaoObjetosEventosResponse toResponse (DoacaoObjetosEventos doacaoObjetosEventos){
        return new DoacaoObjetosEventosResponse(doacaoObjetosEventos.getId(),doacaoObjetosEventos.getNomeDoObjeto(),
                doacaoObjetosEventos.getQuantidade());

    }

}
