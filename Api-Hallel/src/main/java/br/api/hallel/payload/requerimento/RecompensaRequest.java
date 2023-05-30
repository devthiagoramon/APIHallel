package br.api.hallel.payload.requerimento;

import br.api.hallel.model.Associado;
import br.api.hallel.model.Recompensa;
import lombok.Data;

import java.util.List;

@Data
public class RecompensaRequest {

    private String nome;
    private String descricao;
    private Boolean isObjeto;
    private List<Associado> sortAssociadosRecompensa;

    public Recompensa toRecompensa(){
        Recompensa recompensa = new Recompensa();
        recompensa.setNome(nome);
        recompensa.setDescricao(descricao);
        recompensa.setIsObjeto(isObjeto);

        return recompensa;
    }

}
