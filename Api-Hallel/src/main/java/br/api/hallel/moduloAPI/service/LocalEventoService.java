package br.api.hallel.moduloAPI.service;

import br.api.hallel.moduloAPI.model.LocalEvento;
import br.api.hallel.moduloAPI.payload.requerimento.LocalEventoReq;
import br.api.hallel.moduloAPI.payload.resposta.LocalEventoLocalizacaoResponse;
import br.api.hallel.moduloAPI.payload.resposta.LocalEventoResponse;
import br.api.hallel.moduloAPI.repository.LocalEventoRepository;
import br.api.hallel.moduloAPI.service.interfaces.LocalEventoInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LocalEventoService implements LocalEventoInterface {

    @Autowired
    private LocalEventoRepository repository;

    @Override
    public LocalEvento adicionarLocalEvento(LocalEventoReq localEventoReq) {
        return this.repository.insert(localEventoReq.toLocalEvento());
    }

    @Override
    public LocalEvento editarLocalEvento(String idLocalEvento, LocalEventoReq newLocalEvento) {
        LocalEvento localEventoAntigo = listarLocalEventoPorId(idLocalEvento).toLocalEvento();

        localEventoAntigo.setId(idLocalEvento);
        localEventoAntigo.setLocalizacao(newLocalEvento.getLocalizacao());
        localEventoAntigo.setImagem(newLocalEvento.getImagem());
        return localEventoAntigo;
    }

    @Override
    public void excluirLocalEvento(String id) {
        this.repository.deleteById(id);
    }

    @Override
    public List<LocalEventoLocalizacaoResponse> listarTodasLocalizacaoLocalEvento() {
        List<LocalEvento> localEventos = listarTodosLocalEvento();
        ArrayList<LocalEventoLocalizacaoResponse> responseList = new ArrayList<>();

        localEventos.forEach(item -> {
            responseList.add(new LocalEventoLocalizacaoResponse(item));
        });
        return responseList;
    }

    @Override
    public List<LocalEvento> listarTodosLocalEvento() {
        return this.repository.findAll();
    }

    @Override
    public LocalEventoResponse listarLocalEventoPorId(String id) {
        boolean present = this.repository.findById(id).isPresent();
        LocalEvento localEvento = this.repository.findById(id).get();
        return present ? new LocalEventoResponse(localEvento) : null;
    }

    @Override
    public LocalEventoLocalizacaoResponse listarLocalizacaoLocalEventoPorId(String id) {
        LocalEvento localEvento = listarLocalEventoPorId(id).toLocalEvento();
        return new LocalEventoLocalizacaoResponse(localEvento);
    }
}
