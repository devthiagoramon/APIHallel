package br.api.hallel.scripts.eventos;

import br.api.hallel.moduloAPI.service.eventos.EventoArquivadoService;
import br.api.hallel.moduloAPI.service.eventos.EventosService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Log
public class EventosScripts {

    @Autowired
    private EventoArquivadoService arquivadoService;
    @Autowired
    private EventosService eventosService;

    @Scheduled(cron = "00 00 00 * * *")
    public void arquivarEvento() {

        log.info("RODOU AS 00H");
        for (String ids : this.eventosService.listAllEventosId()) {
            this.arquivadoService.addEventoArquivado(ids);
        }

    }

}
