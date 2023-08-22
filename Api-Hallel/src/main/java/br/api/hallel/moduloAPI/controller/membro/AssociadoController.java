package br.api.hallel.moduloAPI.controller.membro;

import br.api.hallel.moduloAPI.model.CartaoAssociado;
import br.api.hallel.moduloAPI.payload.requerimento.PagarAssociacaoRequest;
import br.api.hallel.moduloAPI.payload.resposta.AssociadoPerfilResponse;
import br.api.hallel.moduloAPI.payload.resposta.CursosAssociadoRes;
import br.api.hallel.moduloAPI.payload.resposta.PagamentoAssociadoPerfilResponse;
import br.api.hallel.moduloAPI.service.AssociadoService;
import br.api.hallel.moduloAPI.service.CursoService;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Data
@RestController
@RequestMapping("/api/associados")
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
        if (this.service.pagarAssociacao(pagarAssociacaoRequest)) {
            return ResponseEntity.status(200).body(true);
        } else {
            return ResponseEntity.status(402).body(false);
        }
    }

    @GetMapping("/perfil/{idAssociado}")
    public ResponseEntity<AssociadoPerfilResponse> visualizarPerfilAssociado(@PathVariable String idAssociado){
        return ResponseEntity.status(200).body(this.service.visualizarPerfilAssociado(idAssociado));
    }

    @GetMapping("/perfil/pagamento/{idAssociado}")
    public ResponseEntity<PagamentoAssociadoPerfilResponse> listarPagamentoAssociadoPerfilByMesAndAno
            (@PathVariable String idAssociado,
             @RequestParam(value = "mes") String mes,
             @RequestParam(value = "ano") String ano){
        return ResponseEntity.status(200).body(this.service.listarPagamentoPerfilByMesAno(idAssociado, mes, ano));
    }

    @GetMapping("/cartaoAssociado/{idAssociado}")
    public ResponseEntity<CartaoAssociado> listarCartaoAssociado(@PathVariable String idAssociado){
        return ResponseEntity.status(200).body(this.service.cartaoAssociado(idAssociado));
    }

}
