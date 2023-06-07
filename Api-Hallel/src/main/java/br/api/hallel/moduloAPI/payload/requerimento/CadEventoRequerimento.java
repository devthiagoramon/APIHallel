package br.api.hallel.moduloAPI.payload.requerimento;

import br.api.hallel.moduloAPI.model.Eventos;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class CadEventoRequerimento {

    @NotBlank
    private String titulo;
    @NotBlank
    private String descricao;
    @NotBlank
    private String local;
    @NotBlank
    private String imagem;

    @NotBlank
    private String date;
    @NotBlank
    private String horario;

    public Eventos toEventos(){
        Eventos eventos = new Eventos();
        eventos.setTitulo(this.getTitulo());
        eventos.setDescricao(this.getDescricao());
        eventos.setLocalidade(this.getLocal());
        eventos.setImagem(this.getImagem());
        eventos.setDate(this.getDate());
        eventos.setHorario(this.getHorario());
        return eventos;
    }

}
