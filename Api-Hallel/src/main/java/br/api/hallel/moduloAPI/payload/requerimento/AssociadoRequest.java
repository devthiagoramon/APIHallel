package br.api.hallel.moduloAPI.payload.requerimento;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AssociadoRequest {
    private String nome;
    private String email;
    private String cpf;
    private String telefone;
    private Date dataNascimento;
    private String num_cartao;
    private Date data_validade_cartao;
    private Integer cvc_cartao;
    private String nome_titular_cartao;
    private String endereco_cartao;
}
