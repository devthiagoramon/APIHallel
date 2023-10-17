package br.api.hallel.moduloAPI.model;

import br.api.hallel.moduloAPI.payload.requerimento.EventosRequest;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class ContribuicaoEvento {

    @Id
    private String id;
    private String emailContribuidor;
    private String tipoContribuicao;
    private Integer quantidade;
    private EventosRequest eventos;
    private Date date;
}
