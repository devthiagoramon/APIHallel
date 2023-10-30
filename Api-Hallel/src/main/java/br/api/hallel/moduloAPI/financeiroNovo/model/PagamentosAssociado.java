package br.api.hallel.moduloAPI.financeiroNovo.model;

import br.api.hallel.moduloAPI.model.Associado;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class PagamentosAssociado extends EntradasFinanceiro{
    @Nullable
    private List<Associado> para;

    private Date dataPaga;
    @Nullable
    private String idAssociadoPagador;
}
