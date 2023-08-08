package br.api.hallel.moduloAPI.controller.membro;

import br.api.hallel.moduloAPI.model.Doacao;
import br.api.hallel.moduloAPI.model.DoacaoObjeto;
import br.api.hallel.moduloAPI.model.DoadorReq;
import br.api.hallel.moduloAPI.payload.requerimento.DoacaoObjetoReq;
import br.api.hallel.moduloAPI.payload.requerimento.DoacaoReq;
import br.api.hallel.moduloAPI.service.DoacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/doacao")
@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
public class DoacaoController {

    @Autowired
    private DoacaoService service;

    @PostMapping("/doar")
    private Doacao doar(@RequestBody DoacaoReq doacaoReq) {
        return this.service.doar(doacaoReq);
    }

    @PostMapping("/doarObjeto")
    private DoacaoObjeto doarObjeto(@RequestBody DoacaoObjetoReq doacaoObjetoReq) {
        return this.service.doarObjeto(doacaoObjetoReq);
    }

    @PostMapping("/virarDoador")
    private String virarDoador(@RequestBody DoadorReq doadorReq) {

        return "Error em virar doador";
    }

}

