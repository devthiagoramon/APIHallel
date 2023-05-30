package br.api.hallel.payload.resposta;

import br.api.hallel.model.Associado;
import br.api.hallel.model.Recompensa;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;

@Data
@NoArgsConstructor
public class RecompensaResponse {

    private String nome;
    private String descricao;
    private Boolean isObjeto;
    private List<Associado> sortAssociadosRecompensa;

    public Recompensa toRecompensaRes(){
        Recompensa recompensa = new Recompensa();
        recompensa.setNome(nome);
        recompensa.setDescricao(descricao);
        recompensa.setIsObjeto(isObjeto);
        return recompensa;
    }

}
