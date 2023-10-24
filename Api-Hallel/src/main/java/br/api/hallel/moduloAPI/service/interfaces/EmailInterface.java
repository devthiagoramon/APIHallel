package br.api.hallel.moduloAPI.service.interfaces;

import br.api.hallel.moduloAPI.payload.requerimento.EmailRequest;
import jakarta.mail.MessagingException;

public interface EmailInterface {

    void sendMail(EmailRequest response);
    void sendMailAttachment(EmailRequest response) throws MessagingException;
}
