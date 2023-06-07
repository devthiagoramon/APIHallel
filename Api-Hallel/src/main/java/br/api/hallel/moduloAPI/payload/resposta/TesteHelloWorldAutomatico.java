package br.api.hallel.moduloAPI.payload.resposta;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class TesteHelloWorldAutomatico {
    @PostConstruct
    public void olamundoAutomatico(){
        for (int i = 0; i < 5; i++) {
            System.out.println("oi vasco ["+(i+1)+"]");
        }
    }
}
