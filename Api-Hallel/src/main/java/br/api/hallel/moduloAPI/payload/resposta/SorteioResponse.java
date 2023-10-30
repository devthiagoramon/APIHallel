package br.api.hallel.moduloAPI.payload.resposta;

import br.api.hallel.moduloAPI.model.Associado;
import br.api.hallel.moduloAPI.model.ItensSorteio;
import br.api.hallel.moduloAPI.model.Sorteio;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class SorteioResponse {
    private String id;
    private String titulo;
    private Date data;
    private String descricao;
    private List<ItensSorteio> itensSorteios;
    List<Associado> sorteioAssociados;

    public SorteioResponse toSorteioResponse(Sorteio sorteio){
        SorteioResponse response = new SorteioResponse();

        response.setId(sorteio.getId());
        response.setTitulo(sorteio.getTitulo());
        response.setSorteioAssociados(sorteio.getSorteioAssociados());
        response.setData(sorteio.getData());
        response.setDescricao(sorteio.getDescricao());
        response.setItensSorteios(sorteio.getItensSorteios());

        return response;
    }

}
