package br.api.hallel.moduloAPI.controller;

import br.api.hallel.moduloAPI.model.Transacao;
import br.api.hallel.moduloAPI.payload.requerimento.TransacaoRequerimento;
import br.api.hallel.moduloAPI.service.financeiro.TransacaoService;
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
