package br.api.hallel.service;

import br.api.hallel.model.Eventos;
import br.api.hallel.model.Membro;
import br.api.hallel.repository.EventosRepository;
import br.api.hallel.service.interfaces.EventosInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class EventosService implements EventosInterface {

    @Autowired
    private EventosRepository repository;
    @Autowired
    private MembroService membroService;
    @Autowired
    private ComunidadeService comunidadeService;
    Logger logger =  LoggerFactory.getLogger(EventosService.class);


    //LISTA OS EVENTOS JÁ CRIADOS
    @Override
    public List<Eventos> listarAllEventos() {
        logger.info("LISTANDO EVENTOS!");

        return this.repository.findAll();
    }

    //LISTA APENAS UM EVENTO PELO SEU ID
    @Override
    public Eventos listarEventoById(String id) {
        Optional<Eventos> optional = this.repository.findById(id);

        if (optional.isPresent()) {
            Eventos eventos = optional.get();

            logger.info("EVENTO LISTADO!");

            return eventos;
        } else {
            logger.warn("EVENTOS NÃO ENCONTRADO!");

            return null;
        }
    }

    //LISTA O EVENTO PELO SEU NOME
    @Override
    public Eventos listarEventosByNome(String nome) {
        Optional<Eventos> optional = this.repository.findByTitulo(nome);

        if (optional.isPresent()) {
            Eventos eventos = optional.get();

            logger.info("EVENTO LISTADO!");

            return eventos;
        } else {
            logger.warn("EVENTO NÃO ENCONTRADO!");

            return null;
        }

    }

    //CRIA EVENTOS
    @Override
    public Eventos createEvento(Eventos evento) {
        logger.info("EVENT0 CRIADO!");

        return this.repository.insert(evento);
    }


    //ATUALIZA AS INFORMAÇÕES DO EVENTO
    @Override
    public Eventos updateEventoById(String id) {

        Optional<Eventos> optional = this.repository.findById(id);

        if (optional.isPresent()) {
            Eventos eventos = optional.get();
            logger.info("EVENTO ATUALZIADO!");

            return this.repository.save(eventos);
        } else {
            logger.warn("EVENTO NÃO ENCONTRADO!");

            return null;
        }

    }

    //REMOVE UM EVENTO
    @Override
    public void deleteEventoById(String id) {
        Optional<Eventos> optional = this.repository.findById(id);

        if (optional.isPresent()) {
            Eventos eventos = optional.get();
            this.repository.deleteById(id);
            logger.info("EVENTO DELETADO!");

        } else {
            logger.warn("EVENTO NÃO ENCONTRADO, ENTÃO NAO FOI REMOVIDO!");

        }
    }


    //ADICIONA UM MEMBRO AO EVENTO
    @Override
    public String adicionarMembro(String titulo, String emailUser) {
        Optional<Eventos> optional = this.repository.findByTitulo(titulo);

        if(optional.isPresent()){
            Eventos evento = optional.get();
            if(evento.getIntegrantes() != null){
                evento.getIntegrantes().add(membroService.findByEmail(emailUser));
            }else{
                ArrayList<Membro> integrantes = new ArrayList<>();
                integrantes.add(membroService.findByEmail(emailUser));
                evento.setIntegrantes(integrantes);
            }
            logger.info("MEMBRO CADASTRADO AO EVENTO!");
            this.repository.save(evento);

            return "Membro cadastrado no evento com sucesso";
        }
        logger.info("EVENTO NÃO ECONTRADO!");

        return "Evento não encontrado";
    }




}
