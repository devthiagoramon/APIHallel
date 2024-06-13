package br.api.hallel.moduloAPI.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
@Data
@NoArgsConstructor
@ToString
public class DoacaoDinheiroEvento {

    private String id;
    private String emailDoador;
    private Date dataDoacao;
    private Double valorDoado;
    private String formaDePagamento;
    private String dataRecebida;
    private boolean isRecebido;
    private String nomeDoador;
    private Boolean Mensalmente;
    private Boolean Anualmente;
    private String dia;




    public DoacaoDinheiroEvento(Double valorDoado,String emailDoador,Date dataDoacao,String formaDePagamento,
                                String dataRecebida,boolean isRecebido, String nomeDoador, Boolean Mensalmente,
                                Boolean Anualmente, String dia) {
        this.valorDoado = valorDoado;
        this.emailDoador = emailDoador;
        this.dataDoacao = dataDoacao;
        this.formaDePagamento = formaDePagamento;
        this.dataRecebida = dataRecebida;
        this.isRecebido = isRecebido;
        this.nomeDoador = nomeDoador;
        this.Mensalmente = Mensalmente;
        this.Anualmente = Anualmente;
        this.dia = dia;


    }


}
