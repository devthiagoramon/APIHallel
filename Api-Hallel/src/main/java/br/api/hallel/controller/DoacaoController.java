package br.api.hallel.controller;

import br.api.hallel.model.Doacao;
import br.api.hallel.payload.requerimento.DoacaoReq;
import br.api.hallel.service.DoacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/doacao")
@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
public class DoacaoController {

    private DoacaoService service;

    @PostMapping("/doar")
    private Doacao doar(DoacaoReq doacaoReq){
        return this.service.doar(doacaoReq);
    }

}
