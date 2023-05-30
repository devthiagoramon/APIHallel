package br.api.hallel.controller;

import br.api.hallel.model.Sorteio;
import br.api.hallel.payload.requerimento.SorteioRequest;
import br.api.hallel.payload.resposta.SorteioResponse;
import br.api.hallel.service.SorteioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sorteio")
@CrossOrigin("*")
public class SorteioController {

    @Autowired
    private SorteioService service;

    @PostMapping("/create")
    public ResponseEntity<Sorteio> createSorteio(@RequestBody SorteioRequest request){
        return ResponseEntity.status(200).body(this.service.createSorteio(request));
    }

    @GetMapping("")
    public ResponseEntity<List<SorteioResponse>> listAllSorteios(){
        return ResponseEntity.status(200).body(this.service.listAllSorteio());
    }

    @GetMapping("/{idSorteio}")
    public ResponseEntity<SorteioResponse> listSorteiosById(@PathVariable(value = "idSorteio") String idSorteio){
        return ResponseEntity.status(200).body(this.service.listSorteioById(idSorteio));
    }

    @PostMapping("/update/{idSorteio}")
    public ResponseEntity<SorteioResponse> updateSorteioById(@PathVariable(value = "idSorteio") String idSorteio,
                                                             @RequestBody SorteioRequest request){
        return ResponseEntity.status(200).body(this.service.updateSorteioById(idSorteio, request));
    }

    @PostMapping("/delete/{idSorteio}")
    public ResponseEntity<?> deleteSorteioById(@PathVariable(value = "idSorteio") String idSorteio){
        this.service.deleteSorteioById(idSorteio);

        return ResponseEntity.ok().build();
    }

}
