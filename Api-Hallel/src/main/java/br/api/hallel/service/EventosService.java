package br.api.hallel.service;

import br.api.hallel.model.Eventos;
import br.api.hallel.repository.EventosRepository;
import br.api.hallel.service.interfaces.EventosInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventosService implements EventosInterface {

    @Autowired
    private EventosRepository repository;

    private Logger logger = LoggerFactory.getLogger(EventosService.class);

    @Override
    public List<Eventos> listarAllEventos() {
        logger.info("Listando todos os eventos! -- Administrador: "+getLogado());
        return this.repository.findAll();
    }

    @Override
    public Eventos listarEventoById(String id) {
        Optional<Eventos> optional = this.repository.findById(id);

        if (optional.isPresent()) {
            Eventos eventos = optional.get();
            logger.info("Eventos: "+eventos.getNome()+" listado -- Administrador: "+getLogado());

            return eventos;
        } else {
            logger.error("Evento não encontrado! -- Administrador: "+getLogado());

            return null;
        }
    }

    @Override
    public Eventos listarEventosByNome(String nome) {
        Optional<Eventos> optional = this.repository.findByNome(nome);

        if (optional.isPresent()) {
            Eventos eventos = optional.get();
            logger.info("Eventos: "+eventos.getNome()+" listado -- Administrador: "+getLogado());

            return eventos;
        } else {
            logger.error("Evento não encontrado! -- Administrador: "+getLogado());

            return null;
        }

    }

    @Override
    public Eventos createEvento(Eventos evento) {
        logger.info("Evento criado! -- Administrador: "+getLogado());

        return this.repository.insert(evento);
    }

    @Override
    public Eventos updateEventoById(String id) {

        Optional<Eventos> optional = this.repository.findById(id);

        if (optional.isPresent()) {
            Eventos eventos = optional.get();
            logger.info("Eventos: "+eventos.getNome()+" Alterado! -- Administrador: "+getLogado());

            return this.repository.save(eventos);
        } else {
            logger.error("Eventos não encontrado listado! -- Administrador: "+getLogado());

            return null;
        }

    }

    @Override
    public void deleteEventoById(String id) {
        Optional<Eventos> optional = this.repository.findById(id);

        if (optional.isPresent()) {
            Eventos eventos = optional.get();
            logger.info("Evento: "+eventos.getNome()+" deletado -- Administrador: "+getLogado());

            this.repository.deleteById(id);
        } else {
            logger.error("Evento não encontrado! -- Administrador: "+getLogado());
        }
    }

    private String getLogado() {
        Authentication adminLogado = SecurityContextHolder.getContext().getAuthentication();
        if (!(adminLogado instanceof AnonymousAuthenticationToken)) {
            return adminLogado.getName();
        }
        return "null";

    }


}
