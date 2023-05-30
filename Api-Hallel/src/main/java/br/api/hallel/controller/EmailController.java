package br.api.hallel.controller;

import br.api.hallel.payload.requerimento.EmailRequest;
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
    public ResponseEntity<?> createMail(@RequestBody EmailRequest emailRequest){
        this.service.sendMail(emailRequest);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/create/anexo")
    public ResponseEntity<?> createMailAnexo(@RequestBody EmailRequest emailRequest){
        try {
            this.service.sendMailAttachment(emailRequest);
            return ResponseEntity.ok().build();
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
