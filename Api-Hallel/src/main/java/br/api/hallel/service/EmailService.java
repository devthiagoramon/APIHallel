package br.api.hallel.service;

import br.api.hallel.model.Email;
import br.api.hallel.payload.requerimento.EmailRequest;
import br.api.hallel.service.interfaces.EmailInterface;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmailService implements EmailInterface {

    private final JavaMailSender javaMailSender;

    public EmailService(final JavaMailSender javaMailSender){
        this.javaMailSender = javaMailSender;
    }

    @Override
    public Email sendMail(EmailRequest request) {
        var mensagem = new SimpleMailMessage();

        mensagem.setTo(request.getDest());
        mensagem.setSubject(request.getTitulo());
        mensagem.setText(request.getConteudo());

        javaMailSender.send(mensagem);
        log.info("EMAIL ENVIADO!");
        return request.toEmail();
    }

    @Override
    public Email sendMailAttachment(EmailRequest request) throws MessagingException {
        var mensagem = javaMailSender.createMimeMessage();

        var helper = new MimeMessageHelper(mensagem, true);

        helper.setTo(request.getDest());
        helper.setSubject(request.getTitulo());
        helper.setText(request.getConteudo());
        helper.addAttachment(request.getAnexo(), new ClassPathResource(request.getAnexo()));

        javaMailSender.send(mensagem);
        log.info("EMAIL ENVIADO COM ANEXO!");
        return request.toEmail();
    }

}

