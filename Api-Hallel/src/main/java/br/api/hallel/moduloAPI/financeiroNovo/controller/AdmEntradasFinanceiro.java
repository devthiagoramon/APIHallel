package br.api.hallel.moduloAPI.financeiroNovo.controller;

import br.api.hallel.moduloAPI.financeiroNovo.payload.request.EntradaFinanceiroRequest;
import br.api.hallel.moduloAPI.financeiroNovo.payload.response.EntradaFinanceiroResponse;
import br.api.hallel.moduloAPI.financeiroNovo.service.EntradasFinanceiraService;
import br.api.hallel.moduloAPI.financeiroNovo.service.SaidaFinanceiroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/administrador/financeiro/entradas")
public class AdmEntradasFinanceiro {

    @Autowired
    private EntradasFinanceiraService entradaService;

    /**
     * Requisições de Entrada
     */

    @PostMapping("/adicionar")
    public ResponseEntity<?> adicionarEntrada(@RequestBody EntradaFinanceiroRequest request) {
        if (this.entradaService.cadastrar(request) != null) {
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<?> editarEntrada(@PathVariable String id,
                                           @RequestBody EntradaFinanceiroRequest request) {
        if (this.entradaService.editar(id, request)) {

            return ResponseEntity.ok().build();
        } else {

            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/remover/{id}")
    public ResponseEntity<?> removerEntrada(@PathVariable String id) {
        if (this.entradaService.deletar(id)) {

            return ResponseEntity.ok().build();
        } else {

            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/list")
    public ResponseEntity<List<EntradaFinanceiroResponse>> listarTodasEntradas() {
        return ResponseEntity.ok().body(this.entradaService.listarAll());
    }

    @GetMapping("/pagina/{pagina}")
    public ResponseEntity<List<EntradaFinanceiroResponse>> listEntradasPage(@PathVariable
                                                                            int pagina) {
        return ResponseEntity.ok().body(this.entradaService.listByPage(pagina));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntradaFinanceiroResponse> listarEntradaById(@PathVariable(value = "id") String id) {
        if (this.entradaService.listarPorId(id) == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().body(this.entradaService.listarPorId(id));
    }

    @GetMapping("/{pagina}")
    public ResponseEntity<List<EntradaFinanceiroResponse>> listarByPagina(@PathVariable(value = "pagina") int pagina) {
        return ResponseEntity.ok().body(this.entradaService.listByPage(pagina));
    }

    @GetMapping("/ultimasEntradas")
    public ResponseEntity<List<EntradaFinanceiroResponse>> listUltimasEntradas(){
        return ResponseEntity.ok().body(this.entradaService.listUltimasEntradas());
    }

}