package br.api.hallel.controller;

import br.api.hallel.model.Transacao;
import br.api.hallel.payload.requerimento.TransacaoRequerimento;
import br.api.hallel.service.TransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transacao")
@CrossOrigin("*")

public class TransacaoController {

    @Autowired
    private TransacaoService service;

    @PostMapping("/criar")
    public Transacao createTransacao(@RequestBody TransacaoRequerimento transacao){
        return this.service.transacao(transacao);
    }

}
