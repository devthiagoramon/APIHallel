package br.api.hallel.scripts.sorteio;

import br.api.hallel.moduloAPI.service.main.SorteioService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Log
public class ScriptSorteio {

    @Autowired
    private SorteioService sorteioService;

    @Scheduled(cron = "00 00 00 ? * *")
    public void adicionarAssociadoAoSorteio(){
        this.sorteioService.adicionarAssociadoAoSorteio();
    }

}
