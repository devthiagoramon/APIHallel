package br.api.hallel.moduloAPI.financeiroNovo.controller;

import br.api.hallel.moduloAPI.financeiroNovo.model.EntradasFinanceiro;
import br.api.hallel.moduloAPI.financeiroNovo.model.SaidaFinanceiro;
import br.api.hallel.moduloAPI.financeiroNovo.payload.request.EntradaFinanceiroRequest;
import br.api.hallel.moduloAPI.financeiroNovo.payload.request.SaidaFinanceiroRequest;
import br.api.hallel.moduloAPI.financeiroNovo.service.EntradasFinanceiraService;
import br.api.hallel.moduloAPI.financeiroNovo.service.SaidaFinanceiroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/financeiro")

public class FinanceiroNovoController {

    @Autowired
    private EntradasFinanceiraService entradaService;
    @Autowired
    private SaidaFinanceiroService saidaService;


    /**
     *Todas Requisições Para o financeiro
     */


    /**
     * Requisições de Entrada
     */

    @PostMapping("/entrada/adicionar")
    public ResponseEntity<?> adicionarEntrada(@RequestBody EntradaFinanceiroRequest request) {
        if (this.entradaService.cadastrar(request)) {

            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/entrada/editar/{id}")
    public ResponseEntity<?> editarEntrada(@PathVariable String id,
                                           @RequestBody EntradaFinanceiroRequest request) {
        if (this.entradaService.editar(id, request)) {

            return ResponseEntity.ok().build();
        } else {

            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/entrada/remover/{id}")
    public ResponseEntity<?> removerEntrada(@PathVariable String id) {
        if (this.entradaService.deletar(id)) {

            return ResponseEntity.ok().build();
        } else {

            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/entradas")
    public ResponseEntity<List<EntradasFinanceiro>> listarTodasEntradas() {
        return ResponseEntity.ok().body(this.entradaService.listarAll());
    }

    @GetMapping("/entrada/{id}")
    public ResponseEntity<EntradasFinanceiro> listarEntradaById(@PathVariable String id) {
        return ResponseEntity.ok().body(this.entradaService.listarPorId(id));
    }

    /**
     * Requisições de Saída
     */

    @PostMapping("/saida/adicionar")
    public ResponseEntity<?> adicionarSaida(@RequestBody SaidaFinanceiroRequest request) {
        if (this.saidaService.cadastrar(request)) {

            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/saida/editar/{id}")
    public ResponseEntity<?> editarSaida(@PathVariable String id,
                                           @RequestBody SaidaFinanceiroRequest request) {
        if (this.saidaService.editar(id, request)) {

            return ResponseEntity.ok().build();
        } else {

            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/saida/remover/{id}")
    public ResponseEntity<?> removerSaida(@PathVariable String id) {
        if (this.saidaService.deletar(id)) {

            return ResponseEntity.ok().build();
        } else {

            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/saidas")
    public ResponseEntity<List<SaidaFinanceiro>> listarTodasSaidas() {
        return ResponseEntity.ok().body(this.saidaService.listarAll());
    }

    @GetMapping("/saida/{id}")
    public ResponseEntity<SaidaFinanceiro> listarSaidaById(@PathVariable String id) {
        return ResponseEntity.ok().body(this.saidaService.listarPorId(id));
    }


}
