package br.api.hallel.moduloAPI.payload.resposta;

import br.api.hallel.moduloAPI.model.AssociadoStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssociadoResponseList implements Comparable<AssociadoResponseList> {
    private String id;
    private String nome;
    private Date dataPagamento;
    private AssociadoStatus status;

    @Override
    public int compareTo(@NotNull AssociadoResponseList o) {
        if (this.dataPagamento == null || o.dataPagamento == null) {
            return -1;
        } else {
            return this.dataPagamento.compareTo(o.dataPagamento);
        }
    }

}
