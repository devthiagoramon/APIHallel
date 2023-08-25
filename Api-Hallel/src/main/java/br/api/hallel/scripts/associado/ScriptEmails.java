package br.api.hallel.scripts.associado;

import br.api.hallel.moduloAPI.payload.requerimento.EmailRequest;
import br.api.hallel.moduloAPI.service.main.EmailService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Log
public class ScriptEmails {

    @Autowired
    private EmailService service;

    @Scheduled(cron = "0 0 0 ? * *")
    public void createMail(){
        EmailRequest emailRequest = new EmailRequest();
        //CORPO DO EMAIL VAI AQUI
        emailRequest.setAnexo("Parabéns Amigos pelo seu aniverário!!");
        this.service.sendMail(emailRequest);

    }

}
