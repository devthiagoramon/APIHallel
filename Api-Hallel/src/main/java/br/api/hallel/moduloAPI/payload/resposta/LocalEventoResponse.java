package br.api.hallel.moduloAPI.payload.resposta;

import br.api.hallel.moduloAPI.model.LocalEvento;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocalEventoResponse {
    private String localizacao;
    private String imagem;
    private String dataCadastrada;

    public LocalEventoResponse(@NotNull LocalEvento localEvento){
        this.localizacao = localEvento.getLocalizacao();
        this.imagem = localEvento.getImagem();
        this.dataCadastrada = localEvento.getDataCadastrada();
    }

    public LocalEvento toLocalEvento(){
        LocalEvento localEvento = new LocalEvento();
        localEvento.setLocalizacao(this.getLocalizacao());
        localEvento.setImagem(this.getImagem());
        localEvento.setDataCadastrada(this.getDataCadastrada());
        return localEvento;
    }

}
