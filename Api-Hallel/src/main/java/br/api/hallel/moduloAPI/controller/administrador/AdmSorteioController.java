package br.api.hallel.moduloAPI.controller.administrador;

import br.api.hallel.moduloAPI.model.Sorteio;
import br.api.hallel.moduloAPI.payload.requerimento.RecompensaRequest;
import br.api.hallel.moduloAPI.payload.requerimento.SorteioRequest;
import br.api.hallel.moduloAPI.payload.resposta.AssociadoSorteioResponse;
import br.api.hallel.moduloAPI.payload.resposta.SorteioResponse;
import br.api.hallel.moduloAPI.service.main.RecompensaService;
import br.api.hallel.moduloAPI.service.main.SorteioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/administrador/sorteios")
@CrossOrigin("*")
public class AdmSorteioController {

    @Autowired
    private SorteioService service;
    @Autowired
    private RecompensaService recompensaService;


    @PostMapping("/create")
    public ResponseEntity<Sorteio> createSorteio(@RequestBody SorteioRequest request){
        return ResponseEntity.status(200).body(this.service.createSorteio(request));
    }

    @GetMapping("/mesAtual")
    public ResponseEntity<List<SorteioResponse>> listSorteioMes(){
        this.service.getSorteioDoMes();
        return null;
        //return ResponseEntity.status(200).body(this.service.listAllSorteio());
    }

    @GetMapping("")
    public ResponseEntity<List<SorteioResponse>> listAllSorteios(){
        return ResponseEntity.ok().body(this.service.listAllSorteio());
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

    @PostMapping("/adicionarAssociado")
    public ResponseEntity<SorteioResponse> sortToRecompensa() {
        return ResponseEntity.status(201).body(this.service.adicionarAssociadoAoSorteio());
    }

    @PostMapping("/enviarRecompensa/{idSorteio}")
    public ResponseEntity<AssociadoSorteioResponse> addRecompensa (@RequestBody RecompensaRequest request,
                                                                   @PathVariable (value = "idSorteio") String idSorteio){
        return ResponseEntity.status(201).body(this.service.realizarSorteio(idSorteio, request));
    }

}
