package br.api.hallel.scripts.eventos;

import br.api.hallel.moduloAPI.model.Eventos;
import br.api.hallel.moduloAPI.repository.EventosRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Component
@Log
public class DoacaoEventoRecorrente {

    @Autowired
    private EventosRepository repository;

    @Scheduled(cron = "00 00 00 * * *")
    public void RenovaDoacao() {
        log.info("RODOU AS 00H");

        List<Eventos> listaEventos = repository.findAll();

        for (Eventos evento : listaEventos) {
            for (DoacaoDinheiroEvento doacao : evento.getDoacaoDinheiroEvento()) {
                Date dataAtual = new Date();
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(dataAtual);

                if (doacao.getMensalmente()) {
                    calendar.add(Calendar.MONTH, 1);
                    calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(doacao.getDia()));
                } else if (doacao.getAnualmente()) {
                    calendar.add(Calendar.YEAR, 1);
                    calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(doacao.getDia()));
                } else {
                    continue;
                }

                Date proximaData = calendar.getTime();

                DoacaoDinheiroEvento novaDoacao = new DoacaoDinheiroEvento(
                        doacao.getValorDoado(),
                        doacao.getEmailDoador(),
                        proximaData,
                        doacao.getFormaDePagamento(),
                        doacao.getId(),
                        false,
                        doacao.getNomeDoador(),
                        doacao.getMensalmente(),
                        doacao.getAnualmente(),
                        doacao.getDia()
                );

                evento.getDoacaoDinheiroEvento().add(novaDoacao);
                repository.save(evento);
            }
        }
    }
}
