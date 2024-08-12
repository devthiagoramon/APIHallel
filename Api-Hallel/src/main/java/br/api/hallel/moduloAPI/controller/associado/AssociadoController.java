package br.api.hallel.moduloAPI.controller.associado;

import br.api.hallel.moduloAPI.exceptions.associado.AssociadoNotFoundException;
import br.api.hallel.moduloAPI.model.CartaoCredito;
import br.api.hallel.moduloAPI.payload.requerimento.PagarAssociacaoRequest;
import br.api.hallel.moduloAPI.payload.resposta.AssociadoPerfilResponse;
import br.api.hallel.moduloAPI.payload.resposta.CursosAssociadoRes;
import br.api.hallel.moduloAPI.payload.resposta.PagamentoAssociadoPerfilResponse;
import br.api.hallel.moduloAPI.service.cursos.CursoService;
import br.api.hallel.moduloAPI.service.financeiro.AssociadoService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Data
@RestController
@RequestMapping("/api/associado")
@CrossOrigin("*")
@Log4j2
public class AssociadoController {

    @Autowired
    private AssociadoService service;

    @Autowired
    private CursoService cursoService;

    @GetMapping("/meusCursos/{id}")
    public ResponseEntity<List<CursosAssociadoRes>> listCursoCadastradoByAssociado(@PathVariable String id) {
        return ResponseEntity.status(201).body(this.cursoService.listCursoByAssociado(id));
    }


    @PostMapping("/pagarAssociacao")
    public ResponseEntity<Boolean> pagarAssociacao(@RequestBody PagarAssociacaoRequest pagarAssociacaoRequest) {
        if (this.service.pagarAssociacao(pagarAssociacaoRequest.pagarRequest())) {
            return ResponseEntity.status(200).body(true);
        } else {
            return ResponseEntity.status(402).body(false);
        }
    }

    @PostMapping("/pagarAssociacao/alguem")
    public ResponseEntity<Boolean> pagarAssociacaoParaAlguem(@RequestBody PagarAssociacaoRequest pagarAssociacaoRequest) {
        if (this.service.pagarAlguemAssociado(pagarAssociacaoRequest.pagarParaAlguem())) {
            return ResponseEntity.status(200).body(true);
        } else {
            return ResponseEntity.status(402).body(false);
        }
    }

    @GetMapping("/perfil/{idAssociado}")
    public ResponseEntity<AssociadoPerfilResponse> visualizarPerfilAssociado(@PathVariable String idAssociado){
        return ResponseEntity.status(200).body(this.service.visualizarPerfilAssociado(idAssociado));
    }

    @Operation(summary = "Retorna o perfil do associado a partir do token")
    @GetMapping("/perfil/token/{token}")
    public ResponseEntity<AssociadoPerfilResponse> visualizarPerfilAssociadoPeloToken(@PathVariable String token) throws
            AssociadoNotFoundException {
        return ResponseEntity.status(200).body(this.service.visualizarPerfilAssociadoPeloToken(token));
    }

    @GetMapping("/perfil/pagamento/{idAssociado}")
    public ResponseEntity<PagamentoAssociadoPerfilResponse> listarPagamentoAssociadoPerfilByMesAndAno
            (@PathVariable String idAssociado,
             @RequestParam(value = "mes") String mes,
             @RequestParam(value = "ano") String ano){

        return ResponseEntity.status(200).body(this.service.listarPagamentoPerfilByMesAno(idAssociado, mes, ano));
    }

    @GetMapping("/cartaoAssociado/{idAssociado}")
    public ResponseEntity<CartaoCredito> listarCartaoAssociado(@PathVariable String idAssociado){
        return ResponseEntity.status(200).body(this.service.cartaoAssociado(idAssociado));
    }

}
