package br.api.hallel.moduloAPI.financeiroNovo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@Data
@Document
@NoArgsConstructor
public class CodigoEntradaFinanceiro extends CodigosFinanceiro{
    @Id
    private String id;
}
