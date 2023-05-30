package br.api.hallel.payload.requerimento;

import br.api.hallel.model.Associado;
import br.api.hallel.model.Sorteio;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class SorteioRequest {
    private String titulo;
    private String data;
    private String descricao;

    public Sorteio toSorteio(){
        Sorteio sorteio = new Sorteio();
        sorteio.setTitulo(getTitulo());
        sorteio.setData(getData());
        sorteio.setDescricao(getDescricao());

        return sorteio;
    }
}
