package br.api.hallel.moduloAPI.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoacaoObjetosEventos {

    @Id
    private String id;
    private String nomeDoObjeto;
    private Integer quantidade;
    private Membro membroDoador;

    public DoacaoObjetosEventos(String nomeDoObjeto,Integer quantidade) {
        this.nomeDoObjeto = nomeDoObjeto;
        this.quantidade = quantidade;
    }
}
