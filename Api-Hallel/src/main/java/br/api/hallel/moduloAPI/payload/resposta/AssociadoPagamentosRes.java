package br.api.hallel.moduloAPI.payload.resposta;

import br.api.hallel.moduloAPI.model.AssociadoStatus;
import br.api.hallel.moduloAPI.model.Transacao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssociadoPagamentosRes {
    private String nome;
    private String email;
    private AssociadoStatus situacao;

}
