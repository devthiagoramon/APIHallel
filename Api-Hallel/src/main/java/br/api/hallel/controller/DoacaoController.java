package br.api.hallel.controller;

import br.api.hallel.model.Doacao;
import br.api.hallel.payload.requerimento.DoacaoReq;
import br.api.hallel.service.DoacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/doacao")
@CrossOrigin("*")
public class DoacaoController {

    @Autowired
    private DoacaoService service;

    @PostMapping("/doar")
    private Doacao doar(@RequestBody DoacaoReq doacaoReq){
        return this.service.doar(doacaoReq);
    }

}
