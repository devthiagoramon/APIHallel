package br.api.hallel.moduloAPI.payload.requerimento;

import br.api.hallel.moduloAPI.model.Alimentos;
import br.api.hallel.moduloAPI.model.LocalRetiro;
import br.api.hallel.moduloAPI.model.Membro;
import br.api.hallel.moduloAPI.model.Retiro;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RetiroRequest {

    private String nome;
    private String descricao;
    private List<Membro> participantes;
    private Long maxParticipantes;
    private List<Alimentos> alimentos;
    private LocalRetiro localRetiro;
    private String dataRetiro;
    private String horaRetiro;

    public Retiro toRetiro() {

        return new Retiro(getNome(), getDescricao(), getParticipantes(), getMaxParticipantes(),
                getAlimentos(), getLocalRetiro(), getDataRetiro(), getHoraRetiro());
    }

}
