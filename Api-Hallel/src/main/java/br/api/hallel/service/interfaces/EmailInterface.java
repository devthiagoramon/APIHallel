package br.api.hallel.service.interfaces;

import br.api.hallel.payload.requerimento.EmailRequest;
import jakarta.mail.MessagingException;

public interface EmailInterface {

    void sendMail(EmailRequest response);
    void sendMailAttachment(EmailRequest response) throws MessagingException;
}
