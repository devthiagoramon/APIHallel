package br.api.hallel.moduloAPI.payload.requerimento;

import br.api.hallel.moduloAPI.model.DespesaEvento;
import br.api.hallel.moduloAPI.model.TipoDespesa;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DespesaEventoRequest {
    private String nome;
    private String descricao;

    /**
     1 - Dinheiro
     2 - Alimento
     3 - Limpeza
     4 - Acessorios
     5 - Roupas
    */

    private int num_tipoDespesa;

    // Em caso do tipo da despesa for igual aos demais tipos
    private int quantidade;

    // Em caso do tipo da despesa for igual a dinheiro
    private double valor;

    public DespesaEvento toDespesaEvento(){
        DespesaEvento despesaEvento = new DespesaEvento();
        despesaEvento.setNome(nome);
        setDescricao(descricao);
        switch (num_tipoDespesa){
            case 1:
                despesaEvento.setTipoDespesa(TipoDespesa.DINHEIRO);
                despesaEvento.setValor(valor);
                break;
            case 2:
                despesaEvento.setTipoDespesa(TipoDespesa.ALIMENTO);
                despesaEvento.setQuantidade(quantidade);
                break;
            case 3:
                despesaEvento.setTipoDespesa(TipoDespesa.LIMPEZA);
                despesaEvento.setQuantidade(quantidade);
                break;
            case 4:
                despesaEvento.setTipoDespesa(TipoDespesa.ACESSORIO);
                despesaEvento.setQuantidade(quantidade);
                break;
            case 5:
                despesaEvento.setTipoDespesa(TipoDespesa.ROUPAS);
                despesaEvento.setQuantidade(quantidade);
        }
        return despesaEvento;
    }

}
