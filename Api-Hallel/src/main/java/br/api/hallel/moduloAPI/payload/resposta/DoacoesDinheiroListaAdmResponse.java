package br.api.hallel.moduloAPI.payload.resposta;

import br.api.hallel.moduloAPI.model.Doacao;
import br.api.hallel.moduloAPI.model.TipoDoacao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoacoesDinheiroListaAdmResponse {

    private String emailDoador;
    private String descricao;
    private TipoDoacao tipo;
    private String dataDoacao;
    private double valorDoacao;


    public List<DoacoesDinheiroListaAdmResponse> toListDoacoesDinheiroListaAdm(List<Doacao> doacoes){
        List<DoacoesDinheiroListaAdmResponse> doacoesDinheiro = new ArrayList<>();
        doacoes.forEach(doacao -> {
            doacoesDinheiro.add(toDoacaoDinheiro(doacao));
        });
        return doacoesDinheiro;
    }

    private DoacoesDinheiroListaAdmResponse toDoacaoDinheiro(Doacao doacao){
        return new DoacoesDinheiroListaAdmResponse(doacao.getEmailDoador(), doacao.getDescricao(),
                doacao.getTipo(), doacao.getDataDoacao(),
                doacao.getValorDoacao());
    }
}
