package br.api.hallel.moduloAPI.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MembroMinisterio {
    @Id
    private String id;
    private String membroId;
    private String ministerioId;
    private List<String> funcaoMinisterioIds;

    public MembroMinisterio(String membroId, String ministerioId,
                            List<String> funcaoMinisterioIds) {
        this.membroId = membroId;
        this.ministerioId = ministerioId;
        this.funcaoMinisterioIds = funcaoMinisterioIds;
    }
}