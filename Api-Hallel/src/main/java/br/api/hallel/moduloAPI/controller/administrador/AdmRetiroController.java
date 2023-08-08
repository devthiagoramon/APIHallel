package br.api.hallel.moduloAPI.controller.administrador;

import br.api.hallel.moduloAPI.model.Retiro;
import br.api.hallel.moduloAPI.payload.requerimento.AlimentoReq;
import br.api.hallel.moduloAPI.payload.requerimento.RetiroRequest;
import br.api.hallel.moduloAPI.payload.resposta.AlimentoResponse;
import br.api.hallel.moduloAPI.payload.resposta.RetiroResponse;
import br.api.hallel.moduloAPI.service.RetiroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/administrador/retiros")
@CrossOrigin("*")
public class AdmRetiroController {

    @Autowired
    private RetiroService retiroService;

    @PostMapping("/create")
    public ResponseEntity<Retiro> createRetiro(@RequestBody RetiroRequest request) {
        return ResponseEntity.status(200).body(this.retiroService.createRetiro(request));
    }

    @GetMapping("")
    public ResponseEntity<List<RetiroResponse>> listAllRetiros() {
        return ResponseEntity.status(201).body(this.retiroService.listAllRetiros());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RetiroResponse> listRetiroById(@PathVariable(value = "id") String id) {
        return ResponseEntity.status(201).body(this.retiroService.listRetiroById(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<RetiroResponse> updateRetiroById(@RequestBody RetiroRequest request,
                                                           @PathVariable(value = "id") String id) {
        return ResponseEntity.status(200).body(this.retiroService.updateRetiroById(request, id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Retiro> deleteRetiroById(@PathVariable(value = "id") String id) {
        this.retiroService.deleteRetiroById(id);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/adicionar/alimentos/{idRetiro}")
    public ResponseEntity<AlimentoReq> addAlimentosRetiro(@PathVariable(value = "idRetiro") String idRetiro,
                                                          @RequestBody AlimentoReq req) {

        return ResponseEntity.ok().body(this.retiroService.addAlimentosRetiro(idRetiro, req));
    }

    @DeleteMapping("/remover/alimentos/{idRetiro}")
    public ResponseEntity<AlimentoReq> removerAlimentosRetiro(@PathVariable(value = "idRetiro") String idRetiro, @RequestBody AlimentoReq req) {

        return ResponseEntity.ok().body(this.retiroService.removeAlimentoRetiro(idRetiro, req));
    }

    @GetMapping("/alimentos/{idRetiro}")
    public ResponseEntity<List<AlimentoResponse>> listarAlimentosRetiro(@PathVariable(value = "idRetiro") String idRetiro){
        return ResponseEntity.ok().body(this.retiroService.listAllAlimentosByRetiro(idRetiro));
    }

    @PutMapping("/atualizar/alimentos/{idRetiro}")
    public ResponseEntity<AlimentoReq> updateAlimentoRetiro(@PathVariable (value = "idRetiro") String idRetiro,
                                                            @RequestBody AlimentoReq alimentoReq){
        return ResponseEntity.ok().body(this.retiroService.atualizarAlimentoRetiro(idRetiro,alimentoReq));
    }

}
