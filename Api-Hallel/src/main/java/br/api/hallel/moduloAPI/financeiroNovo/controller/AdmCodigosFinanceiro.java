package br.api.hallel.moduloAPI.financeiroNovo.controller;

import br.api.hallel.moduloAPI.financeiroNovo.model.CodigoEntradaFinanceiro;
import br.api.hallel.moduloAPI.financeiroNovo.model.CodigoSaidaFinanceiro;
import br.api.hallel.moduloAPI.financeiroNovo.payload.request.CodigoEntradaFinanceiroRequest;
import br.api.hallel.moduloAPI.financeiroNovo.payload.request.CodigoSaidaFinanceiroRequest;
import br.api.hallel.moduloAPI.financeiroNovo.payload.response.CodigoEntradaFinanceiroResponse;
import br.api.hallel.moduloAPI.financeiroNovo.payload.response.CodigoSaidaFinanceiroResponse;
import br.api.hallel.moduloAPI.financeiroNovo.service.CodigoEntradaFinanceiroService;
import br.api.hallel.moduloAPI.financeiroNovo.service.CodigoSaidaFinanceiroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/administrador/financeiro/codigo")
public class AdmCodigosFinanceiro {

    @Autowired
    private CodigoEntradaFinanceiroService codigoEntradaService;
    @Autowired
    private CodigoSaidaFinanceiroService codigoSaidaService;

    @PostMapping("/entradas/criar")
    public ResponseEntity<CodigoEntradaFinanceiro> criarCodigoEntrada(@RequestBody CodigoEntradaFinanceiroRequest request) {
        return ResponseEntity.accepted().body(this.codigoEntradaService.cadastrar(request));
    }

    @GetMapping("/entradas/list")
    public ResponseEntity<List<CodigoEntradaFinanceiroResponse>> listCodigoEntrada() {
        return ResponseEntity.accepted().body(this.codigoEntradaService.listarAll());
    }

    @PutMapping("/entradas/editar/{id}")
    public ResponseEntity<?> editarCodigoEntrada(@PathVariable(value = "id") String id,
                                                 @RequestBody CodigoEntradaFinanceiroRequest request) {
        if (this.codigoEntradaService.editar(id, request)) {
            return ResponseEntity.accepted().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/entradas/deletar/{id}")
    public ResponseEntity<?> deleteCodigoEntrada(@PathVariable(value = "id") String id) {

        if (this.codigoEntradaService.deletar(id)) {
            return ResponseEntity.accepted().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/saidas/criar")
    public ResponseEntity<CodigoSaidaFinanceiro> criarCodigoSaida(@RequestBody CodigoSaidaFinanceiroRequest request) {
        return ResponseEntity.accepted().body(this.codigoSaidaService.cadastrar(request));
    }

    @GetMapping("/saidas/list")
    public ResponseEntity<List<CodigoSaidaFinanceiroResponse>> listCodigoSaida() {
        return ResponseEntity.accepted().body(this.codigoSaidaService.listarAll());
    }

    @PutMapping("/saidas/editar/{id}")
    public ResponseEntity<?> editarCodigoSaida(@PathVariable(value = "id") String id,
                                               @RequestBody CodigoSaidaFinanceiroRequest request) {
        if (this.codigoSaidaService.editar(id, request)) {
            return ResponseEntity.accepted().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/saidas/deletar/{id}")
    public ResponseEntity<?> deleteCodigoSaida(@PathVariable(value = "id") String id) {

        if (this.codigoSaidaService.deletar(id)) {
            return ResponseEntity.accepted().build();
        }
        return ResponseEntity.badRequest().build();
    }
}
