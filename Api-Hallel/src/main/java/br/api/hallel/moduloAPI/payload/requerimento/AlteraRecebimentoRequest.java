package br.api.hallel.moduloAPI.payload.requerimento;


import br.api.hallel.moduloAPI.model.DoacaoObjetosEventos;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlteraRecebimentoRequest {
    private DoacaoObjetosEventos doacaoObjetosEventos;
    private Boolean isRecebido;

}
