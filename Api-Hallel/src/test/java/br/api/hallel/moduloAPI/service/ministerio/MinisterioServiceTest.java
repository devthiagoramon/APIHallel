package br.api.hallel.moduloAPI.service.ministerio;

import br.api.hallel.moduloAPI.dto.v1.EscalaMinisterioWithEventoInfoResponse;
import br.api.hallel.moduloAPI.model.EscalaMinisterio;
import br.api.hallel.moduloAPI.model.Eventos;
import br.api.hallel.moduloAPI.payload.requerimento.EventosRequest;
import br.api.hallel.moduloAPI.service.eventos.EventosService;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MinisterioServiceTest implements WithAssertions {

    @Autowired
    private MinisterioService ministerioService;
    @Autowired
    private EventosService eventosService;

    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

    @Test
    void listMembroMinisterioById() {
        var membro = ministerioService.listMembroMinisterioById("66bf7ccc14ada5739ba7faf4");

        assertNotNull(membro.getMinisterio());
        assertNotNull(membro.getMembro());
        assertNotNull(membro.getFuncaoMinisterio());

        System.out.println(membro.toString());
    }

    @Test
    void listEscalaMinisterio() {
        EventosRequest eventosRequest = new EventosRequest();
        eventosRequest.setTitulo("Evento teste");
        eventosRequest.setDate(new Date());
        eventosRequest.setDescricao("Descricao teste");
        eventosRequest.setValorDoEvento("R$ 18,50");
        eventosRequest.setMinisteriosAssociados(List.of("66be22c8f669ec5a41cead04"));
        Eventos eventoCreated = eventosService.createEvento(eventosRequest);
        assertEquals("Evento teste", eventoCreated.getTitulo());
        assertEquals("Descricao teste", eventoCreated.getDescricao());
        assertEquals(18.50, eventoCreated.getValorDoEvento());

        List<EscalaMinisterioWithEventoInfoResponse> escalas = ministerioService.listEscalaMinisterio();

        escalas.forEach(escala -> {
            System.out.println(escala.toString());
            if (escala.getEvento().getId()
                      .equals(eventoCreated.getId())) {
                assertEquals(escala.getEvento()
                                   .getId(), eventoCreated.getId());
            }
        });
        eventosService.deleteEventoById(eventoCreated.getId());
    }
}