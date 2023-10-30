package br.api.hallel.moduloAPI.financeiroNovo.model;

import br.api.hallel.moduloAPI.model.Membro;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Doacoes extends EntradasFinanceiro{
    private Membro usuarioDoador;
}
