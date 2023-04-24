package br.api.hallel.controller;

import br.api.hallel.model.Email;
import br.api.hallel.payload.requerimento.EmailRequest;
import br.api.hallel.payload.resposta.EmailResponse;
import br.api.hallel.service.EmailService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/email")
@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
public class EmailController {

    @Autowired
    private EmailService service;

    @PostMapping("/create")
    public ResponseEntity<Email> createMail(@RequestBody EmailRequest emailRequest){

        return ResponseEntity.ok().body(this.service.sendMail(emailRequest));
    }

    @PostMapping("/create/anexo")
    public ResponseEntity<Email> createMailAnexo(@RequestBody EmailRequest emailRequest){
        try {
            return ResponseEntity.ok().body(this.service.sendMailAttachment(emailRequest));
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
