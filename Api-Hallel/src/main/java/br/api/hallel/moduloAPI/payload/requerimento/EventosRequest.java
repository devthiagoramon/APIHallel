package br.api.hallel.moduloAPI.payload.requerimento;

import br.api.hallel.moduloAPI.financeiroNovo.model.PagamentoEntradaEvento;
import br.api.hallel.moduloAPI.model.Eventos;
import br.api.hallel.moduloAPI.model.LocalEvento;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

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
    private List<PagamentoEntradaEvento> pagamentoEntradaEventoList;



    public Eventos toCreateRequest(LocalEvento localEventoAtualizado){
        return getEventos(localEventoAtualizado);
    }

    public Eventos toEvento(LocalEvento localEventoAtualizado){

        return getEventos(localEventoAtualizado);
    }
    public EventosRequest toEventoRequest(Eventos eventos){
        EventosRequest request = new EventosRequest();
        request.setDescricao(eventos.getDescricao());
        request.setTitulo(eventos.getTitulo());
        request.setDate(eventos.getDate());
        request.setLocalEventoRequest(new LocalEventoLocalizacaoRequest().toRequest(eventos.getLocalEvento()));
        request.setLocalEvento(eventos.getLocalEvento());
        request.setHorario(eventos.getHorario());
        request.setImagem(eventos.getImagem());
        request.setPalestrantes(eventos.getPalestrantes());
        request.setPagamentoEntradaEventoList(eventos.getPagamentoEntradaEventoList());
        return request;
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
