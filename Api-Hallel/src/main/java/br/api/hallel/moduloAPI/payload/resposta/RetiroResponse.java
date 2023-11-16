package br.api.hallel.moduloAPI.payload.resposta;

import br.api.hallel.moduloAPI.model.*;
import br.api.hallel.moduloAPI.payload.requerimento.RetiroRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RetiroResponse {

    private String id;
    private String nome;
    private String descricao;
    private List<Membro> participantes;
    private Long maxParticipantes;
    private List<Alimentos> alimentos;
    private LocalRetiro localRetiro;
    private String dataRetiro;
    private String horaRetiro;
    private List<ContribuicaoEvento> contribuicoes;


    public RetiroRequest toRetiroRequest(){

        return new RetiroRequest(getNome(),getDescricao(),getParticipantes(),getMaxParticipantes(),getAlimentos(),getLocalRetiro(),getDataRetiro(),getHoraRetiro(),getContribuicoes());
    }

    public RetiroResponse toResponse(Retiro retiro) {

        return new RetiroResponse(retiro.getId(), retiro.getNome(), retiro.getDescricao(), retiro.getParticipantes(), retiro.getMaxParticipantes(),
                retiro.getAlimentos(), retiro.getLocalRetiro(), retiro.getDataRetiro(), retiro. getHoraRetiro(), contribuicoes);
    }


}
