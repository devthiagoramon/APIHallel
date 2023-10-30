package br.api.hallel.moduloAPI.payload.requerimento;

import br.api.hallel.moduloAPI.model.ItensSorteio;
import br.api.hallel.moduloAPI.model.Sorteio;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class SorteioRequest {
    private String titulo;
    private Date data;
    private String descricao;
    private List<ItensSorteio> itensSorteio;

    public Sorteio toSorteio(){
        Sorteio sorteio = new Sorteio();
        sorteio.setTitulo(getTitulo());
        sorteio.setData(getData());
        sorteio.setDescricao(getDescricao());
        sorteio.setItensSorteios(getItensSorteio());
        return sorteio;
    }
}
