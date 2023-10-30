package br.api.hallel.moduloAPI.payload.requerimento;

import br.api.hallel.moduloAPI.model.Recompensa;
import lombok.Data;

@Data
public class RecompensaRequest {

    private String nome;
    private String descricao;
    private Boolean isObjeto;

    public Recompensa toRecompensa(){
        Recompensa recompensa = new Recompensa();
        recompensa.setNome(nome);
        recompensa.setDescricao(descricao);
        recompensa.setIsObjeto(isObjeto);

        return recompensa;
    }

}
