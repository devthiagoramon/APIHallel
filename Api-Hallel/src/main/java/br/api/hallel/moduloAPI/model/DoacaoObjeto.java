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
public class DoacaoObjeto {

    private String id;
    private String emailDoador;
    private Date dataDoacao;
    private String nomeDoador;
    private String tipoDoacao;
    private Integer quantidade;
    private boolean isRecebido;
    private String dataRecebida;

    public DoacaoObjeto(String emailDoador, String nomeDoador, String tipoDoacao, Date dataDoacao, Integer quantidade, boolean isRecebido) {
        this.emailDoador = emailDoador;
        this.nomeDoador = nomeDoador;
        this.dataDoacao = dataDoacao;
        this.tipoDoacao = tipoDoacao;
        this.quantidade = quantidade;
        this.isRecebido = isRecebido;
    }
}
