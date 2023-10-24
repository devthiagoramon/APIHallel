package br.api.hallel.moduloAPI.model;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocalRetiro {
    @Id
    private String id;
    private String localizacao;
    private String imagem;
    private String dataCadastrada;

}
