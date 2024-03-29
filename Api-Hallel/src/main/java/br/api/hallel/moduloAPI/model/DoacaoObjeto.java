package br.api.hallel.moduloAPI.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@NoArgsConstructor
public class DoacaoObjeto {

    private String id;
    private String emailDoador;
    private String descricao;
    private String dataDoacao;
    private String imagem;
    private Integer quantidade;
    private boolean isRecebido;
    private String dataRecebida;

    public DoacaoObjeto(String emailDoador, String descricao, String dataDoacao, String imagem, Integer quantidade, boolean isRecebido) {
        this.emailDoador = emailDoador;
        this.descricao = descricao;
        this.dataDoacao = dataDoacao;
        this.imagem = imagem;
        this.quantidade = quantidade;
        this.isRecebido = isRecebido;
    }
}
