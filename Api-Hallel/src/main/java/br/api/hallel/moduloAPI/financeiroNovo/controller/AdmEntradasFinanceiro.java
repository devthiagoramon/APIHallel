package br.api.hallel.moduloAPI.financeiroNovo.controller;

import br.api.hallel.moduloAPI.financeiroNovo.model.EntradasFinanceiro;
import br.api.hallel.moduloAPI.financeiroNovo.payload.request.EntradaFinanceiroRequest;
import br.api.hallel.moduloAPI.financeiroNovo.payload.response.EntradaFinanceiroResponse;
import br.api.hallel.moduloAPI.financeiroNovo.service.EntradasFinanceiraService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/administrador/financeiro/entradas")
@Log4j2
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

    @PostMapping("/adicionar/multi")
    public ResponseEntity<List<EntradasFinanceiro>> adicionarMultiEntradas(@RequestBody List<EntradaFinanceiroRequest> financeiroList) {
        return ResponseEntity.accepted().body(this.entradaService.cadastrarMultiValores(financeiroList));
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

    @GetMapping("/totalPaginas")
    public ResponseEntity<Integer> getTotalPaginasEntradas(@RequestParam(value = "mes") String mes,
                                                           @RequestParam(value = "ano") String ano){
        return ResponseEntity.ok().body(this.entradaService.getTotalPages(mes, ano));
    }

    @GetMapping("/list/data")
    public ResponseEntity<List<EntradaFinanceiroResponse>> listByDataAndPagina(
            @RequestParam(value = "mes") String mes,
            @RequestParam(value = "ano") String ano,
            @RequestParam(value = "page") int pagina){
        System.out.println(mes+ano+pagina);
        return ResponseEntity.ok().body(this.entradaService.listEntradasByMesAndAno(pagina, mes, ano));

    }

}
