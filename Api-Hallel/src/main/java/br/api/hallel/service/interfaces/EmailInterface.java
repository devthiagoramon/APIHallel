package br.api.hallel.service.interfaces;

import br.api.hallel.model.Email;
import br.api.hallel.payload.requerimento.EmailRequest;
import jakarta.mail.MessagingException;

public interface EmailInterface {

    Email sendMail(EmailRequest response);
    Email sendMailAttachment(EmailRequest response) throws MessagingException;

}
