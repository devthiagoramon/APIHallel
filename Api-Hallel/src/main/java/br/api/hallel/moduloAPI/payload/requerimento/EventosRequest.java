package br.api.hallel.moduloAPI.payload.requerimento;

import br.api.hallel.moduloAPI.model.*;
import br.api.hallel.moduloAPI.payload.resposta.LocalEventoLocalizacaoResponse;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class EventosRequest {
    private String descricao;
    private String titulo;

    private String date;
    private LocalEventoLocalizacaoRequest localEventoRequest;
    private LocalEvento localEvento;
    private String horario;
    private String imagem;

    private List<String> palestrantes;


    public Eventos toCreateRequest(LocalEvento localEventoAtualizado){
        return getEventos(localEventoAtualizado);
    }

    public Eventos toEvento(LocalEvento localEventoAtualizado){

        return getEventos(localEventoAtualizado);
    }

    @NotNull
    private Eventos getEventos(LocalEvento localEventoAtualizado) {
        Eventos evento = new Eventos();
        evento.setTitulo(getTitulo());
        evento.setDescricao(getDescricao());

        if(getPalestrantes() != null) {
            ArrayList<String> palestrantesList = new ArrayList<>();

            for (String nome :
                    getPalestrantes()) {
                palestrantesList.add(nome);
            }

            evento.setPalestrantes(palestrantesList);
        }

        evento.setImagem(getImagem());
        evento.setHorario(getHorario());
        evento.setDate(getDate());
        evento.setLocalEvento(localEventoAtualizado);
        return evento;
    }

    public Eventos toEventoInEdit(LocalEvento localEventoAtualizado) {
        Eventos evento = new Eventos();
        evento.setTitulo(getTitulo());
        evento.setDescricao(getDescricao());

        if(getPalestrantes() != null) {
            ArrayList<String> palestrantesList = new ArrayList<>();

            palestrantesList.addAll(getPalestrantes());

            evento.setPalestrantes(palestrantesList);
        }

        evento.setImagem(getImagem());
        evento.setHorario(getHorario());
        evento.setDate(getDate());
        evento.setLocalEvento(localEventoAtualizado);
        return evento;
    }
}
