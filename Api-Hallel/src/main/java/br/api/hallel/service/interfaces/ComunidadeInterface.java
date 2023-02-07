package br.api.hallel.service.interfaces;

import br.api.hallel.model.Comunidade;
import br.api.hallel.model.Doacao;

public interface ComunidadeInterface {

    public void atualizarDoacao(Doacao doacao);

    public Comunidade getComunidade();
}
