package br.api.hallel.controller;

import br.api.hallel.model.Sorteio;
import br.api.hallel.payload.requerimento.RecompensaRequest;
import br.api.hallel.payload.requerimento.SorteioRequest;
import br.api.hallel.payload.resposta.AssociadoResponse;
import br.api.hallel.payload.resposta.AssociadoSorteioResponse;
import br.api.hallel.payload.resposta.SorteioResponse;
import br.api.hallel.service.RecompensaService;
import br.api.hallel.service.SorteioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sorteio")
@CrossOrigin("*")
public class SorteioController {

    @Autowired
    private SorteioService service;
    @Autowired
    private RecompensaService recompensaService;


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

    @PostMapping("/adicionarAssociado/{idSorteio}/{idAssociado}")
    public ResponseEntity<SorteioResponse> sortToRecompensa(@PathVariable(value = "idSorteio") String idSorteio,
                                                          @PathVariable(value = "idAssociado") String idAssociado ) {

        return ResponseEntity.status(201).body(this.recompensaService.addToSort(idSorteio, idAssociado));
    }

    @PostMapping("/enviarRecompensa/{idSorteio}")
    public ResponseEntity<AssociadoSorteioResponse> addRecompensa (@RequestBody RecompensaRequest request,
                                                                   @PathVariable (value = "idSorteio") String idSorteio){
        return ResponseEntity.status(201).body(this.recompensaService.sendRecompensa(idSorteio, request));
    }

}
