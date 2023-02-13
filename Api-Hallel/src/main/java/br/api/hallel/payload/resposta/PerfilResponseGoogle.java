package br.api.hallel.payload.resposta;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PerfilResponseGoogle {
    private String nome;
    private String email;
    private String senha;
    private String image;
}
