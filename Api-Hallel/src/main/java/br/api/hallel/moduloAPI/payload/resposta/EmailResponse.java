package br.api.hallel.moduloAPI.payload.resposta;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmailResponse {

    private String dest;
    private String titulo;
    private String conteudo;
    private String anexo;

    public EmailResponse(String dest, String titulo, String conteudo) {
        this.dest = dest;
        this.titulo = titulo;
        this.conteudo = conteudo;
    }

    public EmailResponse(String dest, String titulo, String conteudo, String anexo) {
        this.dest = dest;
        this.titulo = titulo;
        this.conteudo = conteudo;

        if (anexo != null) {
            this.anexo = anexo;
        }

    }
}
