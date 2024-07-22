package br.api.hallel.moduloAPI.payload.resposta;

import br.api.hallel.moduloAPI.model.DoacaoDinheiroEvento;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoacaoDinheiroEventoResponse {

    private String id;
    private String formaDePagamento;
    private Double valorDoado;
    private String nomeDoador;
    private Date dataDoacao;



    public DoacaoDinheiroEventoResponse toResponse (DoacaoDinheiroEvento doacaodinheiroEvento){
        return new DoacaoDinheiroEventoResponse(doacaodinheiroEvento.getId(),
                doacaodinheiroEvento.getFormaDePagamento(),doacaodinheiroEvento.getValorDoado(),
                doacaodinheiroEvento.getNomeDoador(), doacaodinheiroEvento.getDataDoacao());
    }


}
