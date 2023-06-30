package br.api.hallel.moduloAPI.payload.requerimento;

import br.api.hallel.moduloAPI.model.LocalEvento;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocalEventoReq {

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
    private String localizacao;
    private String imagem;

    public LocalEvento toLocalEvento() {
        LocalEvento localEvento = new LocalEvento();
        localEvento.setLocalizacao(this.getLocalizacao());
        localEvento.setImagem(this.getImagem());
        localEvento.setDataCadastrada(simpleDateFormat.format(new Date()));
        return localEvento;
    }

}
