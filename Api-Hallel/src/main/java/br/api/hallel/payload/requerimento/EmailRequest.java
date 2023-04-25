package br.api.hallel.payload.requerimento;

import br.api.hallel.model.Email;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmailRequest {

    private String dest;
    private String titulo;
    private String anexo;
    private String conteudo;

    public Email toEmail() {
        Email email = new Email();
        email.setDestinatario(dest);
        email.setConteudo(conteudo);
        email.setTitulo(titulo);
        if (anexo != null) {
            email.setAnexo(anexo);
        } else {
            email.setAnexo("");
        }
        return email;
    }

}

