package br.api.hallel.moduloAPI.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartaoCredito {
    private String numeroCartao;
    private Date dataValidadeCartao;
    private Integer cvc;
    private String nomeTitular;
    private String endereco;
}
