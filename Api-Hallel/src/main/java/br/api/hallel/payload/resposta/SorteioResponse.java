package br.api.hallel.payload.resposta;

import br.api.hallel.model.Associado;
import br.api.hallel.model.Sorteio;
import lombok.Data;

import java.util.List;

@Data
public class SorteioResponse {
    private String id;
    private String titulo;
    private String data;
    List<Associado> sorteioAssociados;

    public SorteioResponse toSorteioResponse(Sorteio sorteio){
        SorteioResponse response = new SorteioResponse();

        response.setId(sorteio.getId());
        response.setTitulo(sorteio.getTitulo());
        response.setSorteioAssociados(sorteio.getSorteioAssociados());
        response.setData(sorteio.getData());

        return response;
    }

}
