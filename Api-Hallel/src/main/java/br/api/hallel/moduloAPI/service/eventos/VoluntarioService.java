package br.api.hallel.moduloAPI.service.eventos;

import br.api.hallel.moduloAPI.exceptions.eventos.EventoNotFoundException;
import br.api.hallel.moduloAPI.model.Eventos;
import br.api.hallel.moduloAPI.model.VoluntarioEvento;
import br.api.hallel.moduloAPI.payload.requerimento.SeVoluntariarEventoReq;
import br.api.hallel.moduloAPI.payload.resposta.SeVoluntariarEventoResponse;
import br.api.hallel.moduloAPI.repository.EventosRepository;
import br.api.hallel.moduloAPI.repository.VoluntarioRepository;
import br.api.hallel.moduloAPI.service.interfaces.VoluntarioEventonterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class VoluntarioService implements VoluntarioEventonterface {

    @Autowired
    private VoluntarioRepository repository;

    @Autowired
    private EventosRepository eventorepository;



    @Override
    public VoluntarioEvento createVoluntario(SeVoluntariarEventoReq req) {
        System.out.println("voluntario criado : "+req.toString());
        return this.repository.insert(req.toVoluntarioEvento());
    }

    @Override
    public List<SeVoluntariarEventoResponse> listAllVoluntarios(String idEvento) {
        Optional<Eventos> eventoOptional = eventorepository.findById(idEvento);
        if (eventoOptional.isPresent()) {
            Eventos evento = eventoOptional.get();
            List<SeVoluntariarEventoResponse> voluntarios = evento.getVoluntarios().stream()
                    .map(voluntario -> new SeVoluntariarEventoResponse().toResponse(voluntario))
                    .collect(Collectors.toList());
            return voluntarios;
        }
        throw new EventoNotFoundException("Evento não encontrado.");
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