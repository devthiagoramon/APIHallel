package br.api.hallel.moduloAPI.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@NoArgsConstructor
@AllArgsConstructor
@Data
public class EscalaMinisterio {
    private String id;
    private String ministerioId;
    private String eventoId;
    private List<String> membrosMinisterioConvidadosIds;
    private List<String> membrosMinisterioConfimadoIds;
    private List<String> membrosMinisterioNaoConfirmadoIds;

    public EscalaMinisterio(String ministerioId, String eventoId) {
        this.ministerioId = ministerioId;
        this.eventoId = eventoId;
    }


    public EscalaMinisterio(String id, String ministerioId,
                            String eventoId,
                            List<String> membrosMinisterioConvidadosIds) {
        this.id = id;
        this.ministerioId = ministerioId;
        this.eventoId = eventoId;
        this.membrosMinisterioConvidadosIds = membrosMinisterioConvidadosIds;
    }

    public EscalaMinisterio(String ministerioId, String eventoId,
                            List<String> membrosMinisterioConvidadosIds) {
        this.ministerioId = ministerioId;
        this.eventoId = eventoId;
        this.membrosMinisterioConvidadosIds = membrosMinisterioConvidadosIds;
    }

    public EscalaMinisterio(String id, String ministerioId,
                            String eventoId,
                            List<String> membrosMinisterioConfimadoIds,
                            List<String> membrosMinisterioNaoConfirmadoIds) {
        this.id = id;
        this.ministerioId = ministerioId;
        this.eventoId = eventoId;
        this.membrosMinisterioConfimadoIds = membrosMinisterioConfimadoIds;
        this.membrosMinisterioNaoConfirmadoIds = membrosMinisterioNaoConfirmadoIds;
    }
}
