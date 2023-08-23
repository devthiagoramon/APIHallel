package br.api.hallel.moduloAPI.financeiroNovo.controller;

import br.api.hallel.moduloAPI.financeiroNovo.payload.request.SaidaFinanceiroRequest;
import br.api.hallel.moduloAPI.financeiroNovo.payload.response.SaidaFinanceiroResponse;
import br.api.hallel.moduloAPI.financeiroNovo.service.SaidaFinanceiroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/administrador/financeiro/saidas")
public class AdmSaidaEntradaFinanceiro {

    @Autowired
    private SaidaFinanceiroService saidaService;

    @PostMapping("/adicionar")
    public ResponseEntity<?> adicionarSaida(@RequestBody SaidaFinanceiroRequest request) {
        if (this.saidaService.cadastrar(request) != null) {

            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/adicionar/multi")
    public ResponseEntity<?> adicionarMultiSaidas(@RequestBody List<SaidaFinanceiroRequest> requestList) {
        return ResponseEntity.accepted().body(this.saidaService.cadastrarMultiValores(requestList));
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<?> editarSaida(@PathVariable String id,
                                         @RequestBody SaidaFinanceiroRequest request) {
        if (this.saidaService.editar(id, request)) {

            return ResponseEntity.ok().build();
        } else {

            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/remover/{id}")
    public ResponseEntity<?> removerSaida(@PathVariable String id) {
        if (this.saidaService.deletar(id)) {

            return ResponseEntity.ok().build();
        } else {

            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/list")
    public ResponseEntity<List<SaidaFinanceiroResponse>> listarTodasSaidas() {
        return ResponseEntity.ok().body(this.saidaService.listarAll());
    }

    @GetMapping("/pagina/{pagina}")
    public ResponseEntity<List<SaidaFinanceiroResponse>> listarSaidaPage(@PathVariable
                                                                         int pagina) {
        return ResponseEntity.ok().body(this.saidaService.listByPage(pagina));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SaidaFinanceiroResponse> listarSaidaById(@PathVariable String id) {
        return ResponseEntity.ok().body(this.saidaService.listarPorId(id));
    }

    @GetMapping("/ultimasSaidas")
    public ResponseEntity<List<SaidaFinanceiroResponse>> listUltimasSaidas(){
        return ResponseEntity.ok().body(this.saidaService.listarUltimasSaidas());
    }
}
