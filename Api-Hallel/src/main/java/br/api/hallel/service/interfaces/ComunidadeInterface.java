package br.api.hallel.service.interfaces;

import br.api.hallel.model.Comunidade;
import br.api.hallel.model.Doacao;
import br.api.hallel.model.Eventos;
import com.mongodb.BasicDBObject;

import java.util.List;

public interface ComunidadeInterface {

    public void atualizarDoacao(Doacao doacao);

    public Comunidade getComunidade();

    void salvarLucroEventos(Eventos eventos);

    void salvarDespesaEventos(Eventos eventos);

    List<Comunidade> getLucroEvento();

    List<Comunidade> getDepesaEventos();
    List<Comunidade> getDoacaoTotal();

}
