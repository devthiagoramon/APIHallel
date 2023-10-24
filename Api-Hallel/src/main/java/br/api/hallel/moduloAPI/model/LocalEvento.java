package br.api.hallel.moduloAPI.model;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@AllArgsConstructor
@NoArgsConstructor
@Data
public class LocalEvento {
    @Id
    private String id;
    private String localizacao;
    private String imagem;
    private String dataCadastrada;
}
