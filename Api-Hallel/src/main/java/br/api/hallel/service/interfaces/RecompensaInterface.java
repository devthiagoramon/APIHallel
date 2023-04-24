package br.api.hallel.service.interfaces;

import br.api.hallel.model.Associado;
import br.api.hallel.payload.requerimento.RecompensaRequest;

public interface RecompensaInterface {
    Associado sendRecompensa(RecompensaRequest recompensa, Associado associado);

}
