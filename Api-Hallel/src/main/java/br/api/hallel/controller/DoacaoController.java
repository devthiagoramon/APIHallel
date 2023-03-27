package br.api.hallel.controller;

import br.api.hallel.model.Doacao;
import br.api.hallel.payload.requerimento.DoacaoReq;
import br.api.hallel.payload.resposta.DoacoesDinheiroListaAdmResponse;
import br.api.hallel.service.DoacaoService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/doacao")
@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
public class DoacaoController {

    @Autowired
    private DoacaoService service;

    @PostMapping("/doar")
    private Doacao doar(DoacaoReq doacaoReq){
        return this.service.doar(doacaoReq);
    }

    @GetMapping("/list")
    private List<DoacoesDinheiroListaAdmResponse> doacaoList() {return this.service.listAllDoacoes();}

    @GetMapping("/{id}")
    private Doacao listDoacaoById(@PathVariable String id){return this.service.listDoacaoById(id);}

}
