package br.api.hallel.moduloAPI.payload.resposta;

import br.api.hallel.moduloAPI.model.Sorteio;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PerfilAssociadoSorteiosResponse {
    private String id;
    private String titulo;
    private Date data;

    public static PerfilAssociadoSorteiosResponse toPerfilAssociadoSorteiosResponse(Sorteio sorteio){
        return new PerfilAssociadoSorteiosResponse(sorteio.getId(), sorteio.getTitulo(), sorteio.getData());
    }

}
