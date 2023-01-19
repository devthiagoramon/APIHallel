package br.api.hallel.payload.requerimento;

import br.api.hallel.model.Eventos;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.Base64;

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

    public Eventos toEventos(){
        Eventos eventos = new Eventos();
        eventos.setTitulo(this.getTitulo());
        eventos.setDescricao(this.getDescricao());
        eventos.setLocalidade(this.getLocal());
        imagem = imagem.substring(23);
        byte[] imageBytes = Base64.getDecoder().decode(imagem);
        String imageB64 = Base64.getEncoder().encodeToString(imageBytes);
        eventos.setImagem(imageB64);
        return eventos;
    }

}
