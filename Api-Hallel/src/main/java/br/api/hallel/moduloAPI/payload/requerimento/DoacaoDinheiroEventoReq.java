package br.api.hallel.moduloAPI.payload.requerimento;

import br.api.hallel.moduloAPI.model.DoacaoDinheiroEvento;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoacaoDinheiroEventoReq {

    private String emailDoador;
    private Double valorDoado;
    private String formaDePagamento;
    private String nomeDoador;




    public DoacaoDinheiroEvento toDoacaoDinheiroEvento(){
        Date data = new Date();
        return new DoacaoDinheiroEvento(getValorDoado(),getEmailDoador(),data,getFormaDePagamento(),null,false,getNomeDoador());

    }


}
