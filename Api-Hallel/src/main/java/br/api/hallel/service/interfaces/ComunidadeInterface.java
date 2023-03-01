package br.api.hallel.service.interfaces;

import br.api.hallel.model.Comunidade;
import br.api.hallel.model.Doacao;
import br.api.hallel.model.Eventos;
import com.mongodb.client.DistinctIterable;

import java.util.List;

public interface ComunidadeInterface {

     void atualizarDoacao(Doacao doacao);

    Comunidade getComunidade();



}
