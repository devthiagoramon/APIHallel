package br.api.hallel.payload.requerimento;

import br.api.hallel.model.Eventos;
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

    public Eventos toEventos(){
        Eventos eventos = new Eventos();
        eventos.setTitulo(this.getTitulo());
        eventos.setDescricao(this.getDescricao());
        eventos.setLocalidade(this.getLocal());
        return eventos;
    }

}
