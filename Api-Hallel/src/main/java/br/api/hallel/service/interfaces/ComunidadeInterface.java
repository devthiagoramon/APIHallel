package br.api.hallel.service.interfaces;

import br.api.hallel.model.Comunidade;
import br.api.hallel.model.Doacao;

import java.util.List;

public interface ComunidadeInterface {

    void atualizarDoacao(Doacao doacao);

    Comunidade getComunidade();

    List<Comunidade> visualizarLucroMensal();

    List<Comunidade> visualizarGastoMensal();

}
