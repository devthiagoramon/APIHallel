package br.api.hallel.moduloAPI.service.eventos;

import br.api.hallel.moduloAPI.model.VoluntarioEvento;
import br.api.hallel.moduloAPI.payload.requerimento.SeVoluntariarEventoReq;
import br.api.hallel.moduloAPI.payload.resposta.SeVoluntariarEventoResponse;
import br.api.hallel.moduloAPI.repository.VoluntarioRepository;
import br.api.hallel.moduloAPI.service.interfaces.VoluntarioEventonterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class VoluntarioService implements VoluntarioEventonterface {

    @Autowired
    private VoluntarioRepository repository;

    @Override
    public VoluntarioEvento createVoluntario(SeVoluntariarEventoReq req) {
        return this.repository.insert(req.toVoluntarioEvento());
    }

    @Override
    public List<SeVoluntariarEventoResponse> listAllVoluntarios() {
        List<SeVoluntariarEventoResponse> responseList = new ArrayList<>();
        this.repository.findAll().forEach(voluntario -> {
            responseList.add(new SeVoluntariarEventoResponse().toResponse(voluntario));
        });
        return responseList;
    }

    @Override
    public List<SeVoluntariarEventoResponse> listVoluntariosById(String id) {
        // Implemente a lógica para retornar voluntários por ID
        return null;
    }

    @Override
    public SeVoluntariarEventoResponse updateVoluntarioById(String id, SeVoluntariarEventoReq req) {
        VoluntarioEvento voluntarioRequest = req.toVoluntarioEvento();
        voluntarioRequest.setId(id);

        VoluntarioEvento response = this.repository.findById(id)
                .map(existingVoluntario -> this.repository.save(voluntarioRequest))
                .orElse(null);

        return new SeVoluntariarEventoResponse().toResponse(response);
    }

    @Override
    public void deleteVoluntarioById(String id) {
        if (this.listVoluntariosById(id) != null) {
            this.repository.deleteById(id);
        } else {
            log.warn("Voluntário com id " + id + " não existente!");
        }
    }

    @Override
    public void deleteVoluntarioByObjeto(SeVoluntariarEventoReq req) {
        this.repository.findAll().forEach(voluntario -> {
            SeVoluntariarEventoReq voluntarioVal = new SeVoluntariarEventoReq().toSeVoluntariarEventoRequest(voluntario);
            if (voluntarioVal.equals(req)) {
                this.repository.delete(voluntario);
            }
        });
    }
}