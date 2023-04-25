package br.api.hallel.payload.requerimento;

import br.api.hallel.model.Email;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class EmailRequest {

    private String anexo;
    private String conteudo;

    public Email toEmail(){
        Email email = new Email();
        email.setConteudo(conteudo);

        if (anexo != null ) {
            email.setAnexo(anexo);
        }

        return email;
    }

}

