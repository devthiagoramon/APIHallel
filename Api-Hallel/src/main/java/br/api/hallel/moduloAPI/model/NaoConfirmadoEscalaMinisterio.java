package br.api.hallel.moduloAPI.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NaoConfirmadoEscalaMinisterio {
    private String id;
    private String idMembroMinisterio;
    private String idEscalaMinisterio;
    private String motivo;

    public NaoConfirmadoEscalaMinisterio(String idMembroMinisterio,
                                         String idEscalaMinisterio,
                                         String motivo) {
        this.idMembroMinisterio = idMembroMinisterio;
        this.idEscalaMinisterio = idEscalaMinisterio;
        this.motivo = motivo;
    }
}
