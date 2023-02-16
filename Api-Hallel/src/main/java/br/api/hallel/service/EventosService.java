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

@Service
public class EventosService implements EventosInterface {

    @Autowired
    private EventosRepository repository;
    @Autowired
    private MembroService membroService;
    @Autowired
    private ComunidadeService comunidadeService;

    @Override
    public List<Eventos> listarAllEventos() {
        System.out.println("Listando Eventos...");
        return this.repository.findAll();
    }

    @Override
    public Eventos listarEventoById(String id) {
        Optional<Eventos> optional = this.repository.findById(id);

        if (optional.isPresent()) {
            Eventos eventos = optional.get();
            return eventos;
        } else {
            System.out.println("Evento não encontrado!");
            return null;
        }
    }

    @Override
    public Eventos listarEventosByNome(String nome) {
        Optional<Eventos> optional = this.repository.findByTitulo(nome);

        if (optional.isPresent()) {
            Eventos eventos = optional.get();
            return eventos;
        } else {
            System.out.println("Evento não encontrado!");
            return null;
        }

    }

    @Override
    public Eventos createEvento(Eventos evento) {
        return this.repository.insert(evento);
    }

    @Override
    public Eventos updateEventoById(String id) {

        Optional<Eventos> optional = this.repository.findById(id);

        if (optional.isPresent()) {
            Eventos eventos = optional.get();

            return this.repository.save(eventos);
        } else {
            System.out.println("Evento não encontrado!");
            return null;
        }

    }

    @Override
    public void deleteEventoById(String id) {
        Optional<Eventos> optional = this.repository.findById(id);

        if (optional.isPresent()) {
            Eventos eventos = optional.get();
            this.repository.deleteById(id);
        } else {
            System.out.println("Evento não encontrado!");
        }
    }

    @Override
    public Double getDespesaMensal() {
        return this.repository.getDespesas();
    }

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
            this.repository.save(evento);
            return "Membro cadastrado no evento com sucesso";
        }

        return "Evento não encontrado";
    }

    @Override
    public Eventos updateValorTotal(String id) {

        Optional<Eventos> optional = this.repository.findById(id);

        if (optional.isPresent()) {
            Eventos eventos = optional.get();
            eventos.setTotalDespesas(this.repository.getDespesas());
            return this.repository.save(eventos);

        } else {
            System.out.println("NÃO EXISTE ESSA BOMBA");

            return null;
        }
    }

    @Override
    public void despesasEvento(String id, Double despesa) {
        Optional<Eventos> optional = this.repository.findById(id);

        if (optional.isPresent()) {
            Eventos eventos = optional.get();

            eventos.setDespesas(despesa);
            this.comunidadeService.salvarDespesaEventos(eventos);

            this.repository.save(eventos);
        }
    }

    @Override
    public void lucroEvento(String id, Double lucro) {
        Optional<Eventos> optional = this.repository.findById(id);

        if (optional.isPresent()) {
            Eventos eventos = optional.get();

            eventos.setLucro(lucro);
            this.comunidadeService.salvarLucroEventos(eventos);

            this.repository.save(eventos);
        }

    }

}
