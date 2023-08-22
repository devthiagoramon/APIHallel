package br.api.hallel.moduloAPI.service;

import br.api.hallel.moduloAPI.model.Associado;
import br.api.hallel.moduloAPI.payload.requerimento.EmailRequest;
import br.api.hallel.moduloAPI.repository.AssociadoRepository;
import br.api.hallel.moduloAPI.service.interfaces.EmailInterface;
import jakarta.mail.MessagingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Service
@Slf4j
public class EmailService implements EmailInterface {

    private final JavaMailSender javaMailSender;
    @Autowired
    private AssociadoRepository associadoRepository;

    public EmailService(final JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void sendMail(EmailRequest request) {
        var mensagem = new SimpleMailMessage();
        var allAssociados = this.associadoRepository.findAll();

        allAssociados.forEach(associado -> {
            if (verifiqAniversario(associado)) {

                mensagem.setTo(associado.getEmail());
                mensagem.setSubject("Parabéns, " + associado.getNome() + "!");
                mensagem.setText(request.getConteudo());

                javaMailSender.send(mensagem);
                log.info("EMAIL ENVIADO!");

            }
        });

    }

    @Override
    public void sendMailAttachment(EmailRequest request) throws MessagingException {

        var mimeMessage = javaMailSender.createMimeMessage();
        var helper = new MimeMessageHelper(mimeMessage, true);
        var allAssociados = this.associadoRepository.findAll();

        allAssociados.forEach(associado -> {
            if (verifiqAniversario(associado)) {
                try {
                    helper.setTo(associado.getEmail());
                    helper.setSubject("Parabéns, " + associado.getNome() + "!");
                    helper.setText(request.getConteudo(), true);

                    javaMailSender.send(mimeMessage);
                    log.info("EMAIL ENVIADO COM ANEXO!");

                } catch (MessagingException e) {
                    throw new RuntimeException(e);
                }

            }
        });

    }

    private String getDataAtual() {
        Date dataAtual = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM");

        return format.format(dataAtual);
    }

    private Boolean verifiqAniversario(Associado associado) {

        if (associado.getDataNascimento() != null) {

            LocalDate dataNascimento = associado.getDataNascimento().toInstant().
                    atZone(ZoneId.of("America/Puerto_Rico")).toLocalDate();
            LocalDate dateAtual = new Date().toInstant().
                    atZone(ZoneId.of("America/Puerto_Rico")).toLocalDate();

            log.info("Data de nascimento do usuário '"+associado.getNome()+"' : "+dataNascimento);
            log.info("Data atual: "+dateAtual);

            if (dateAtual.getDayOfMonth() == dataNascimento.getDayOfMonth()
                    && dateAtual.getMonthValue() == dataNascimento.getMonthValue()) {
                log.info("Aniversariante: " + associado.getNome());
                return true;
            }
        }

        return false;
    }
}

