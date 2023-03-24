package br.api.hallel.payload.resposta;

import br.api.hallel.model.AssociadoRole;
import br.api.hallel.model.Transacao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssociadoPagamentosRes {
    private String nome;
    private String email;
    private Boolean isPago;
    private AssociadoRole situacao;
    private Transacao transacao;

}
