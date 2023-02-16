package br.api.hallel.service.interfaces;

import br.api.hallel.model.Comunidade;
import br.api.hallel.model.Doacao;
import br.api.hallel.model.Eventos;
import com.mongodb.client.DistinctIterable;

import java.util.List;

public interface ComunidadeInterface {

     void atualizarDoacao(Doacao doacao);

    Comunidade getComunidade();

    void salvarLucroEventos(Eventos eventos);

    void salvarDespesaEventos(Eventos eventos);

    void salvarTransacao();

    void saveDoacao(Doacao doacao);
    List<Comunidade> getLucroEvento();

    List<Comunidade> getDepesaEventos();

    List<Comunidade> getDoacaoTotal();
    List<Comunidade> getLucroTransacao();


}
