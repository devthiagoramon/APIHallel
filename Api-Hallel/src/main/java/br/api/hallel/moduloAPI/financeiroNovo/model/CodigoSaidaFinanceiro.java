package br.api.hallel.moduloAPI.financeiroNovo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Document
@AllArgsConstructor
public class CodigoSaidaFinanceiro extends CodigosFinanceiro{
    @Id
    private String id;

}
