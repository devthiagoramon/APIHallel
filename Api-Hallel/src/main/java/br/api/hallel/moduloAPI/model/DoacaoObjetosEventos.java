package br.api.hallel.moduloAPI.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoacaoObjetosEventos {


    private String id;
    private String nomeDoObjeto;
    private Integer quantidade;
    private String emailDoador;
    private boolean isRecebido;
    private String dataRecebida;
    private Date dataDoacao;
    private String nomeDoador;

    public DoacaoObjetosEventos(String nomeDoObjeto,Integer quantidade,String emailDoador,
                                String dataRecebida,Date dataDoacao,boolean isRecebido,String nomeDoador) {
        this.nomeDoObjeto = nomeDoObjeto;
        this.quantidade = quantidade;
        this.emailDoador = emailDoador;
        this.dataRecebida = dataRecebida;
        this.dataDoacao = dataDoacao;
        this.isRecebido = isRecebido;
        this.nomeDoador = nomeDoador;
    }
}
