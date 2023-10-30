package br.api.hallel.moduloAPI.service.eventos;

import br.api.hallel.moduloAPI.repository.EventosProgramadosRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class EventosProgramadosService {
    @Autowired
    private EventosProgramadosRepository repository;


}
