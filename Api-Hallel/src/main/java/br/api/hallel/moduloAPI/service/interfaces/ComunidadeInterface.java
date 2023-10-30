package br.api.hallel.moduloAPI.service.interfaces;

import br.api.hallel.moduloAPI.model.Comunidade;
import br.api.hallel.moduloAPI.model.Doacao;

public interface ComunidadeInterface {

     void atualizarDoacao(Doacao doacao);

    Comunidade getComunidade();



}
