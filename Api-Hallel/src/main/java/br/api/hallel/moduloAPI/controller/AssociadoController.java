package br.api.hallel.moduloAPI.controller;

import br.api.hallel.moduloAPI.financeiroNovo.payload.request.PagamentoAssociadoRequest;
import br.api.hallel.moduloAPI.model.Associado;
import br.api.hallel.moduloAPI.model.Transacao;
import br.api.hallel.moduloAPI.payload.resposta.AssociadoPagamentosRes;
import br.api.hallel.moduloAPI.payload.resposta.AssociadoResponseList;
import br.api.hallel.moduloAPI.payload.resposta.CursosAssociadoRes;
import br.api.hallel.moduloAPI.repository.MembroRepository;
import br.api.hallel.moduloAPI.repository.RoleRepository;
import br.api.hallel.moduloAPI.service.AssociadoService;
import br.api.hallel.moduloAPI.service.CursoService;
import br.api.hallel.moduloAPI.service.RecompensaService;
import br.api.hallel.moduloAPI.service.TransacaoService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/associados")
@CrossOrigin("*")
@Log4j2
public class AssociadoController {

    @Autowired
    private AssociadoService service;
    @Autowired
    private TransacaoService transacaoService;

    @Autowired
    private CursoService cursoService;
    @Autowired
    private MembroRepository repository;
    @Autowired
    private RecompensaService recompensaService;

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping("/listByMesAno")
    public List<AssociadoResponseList> listAllAssociadosByMesAnoPagos(@RequestParam(value = "mes") String mes,
                                                                      @RequestParam(value = "ano") String ano) {
        return this.service.listAllAssociadoByMesAno(mes, ano);
    }

    @GetMapping("/listAll")
    public List<Associado> listAllAssociados() {
        return this.service.listAllAssociado();
    }

    @PostMapping("/criar/{idMembro}")
    public ResponseEntity<Boolean> createAssociado(@PathVariable String idMembro,
                                                   @RequestBody PagamentoAssociadoRequest pagamentoAssociadoRequest
    ) {
//        PagamentoAssociadoRequest pagamentoAssociadoRequest = new PagamentoAssociadoRequest();
        Boolean booleanResposta = this.service.criarAssociado(idMembro, pagamentoAssociadoRequest);
        if (booleanResposta) {
            return ResponseEntity.status(200).body(true);
        } else {
            return ResponseEntity.status(402).body(false);
        }
    }

    @GetMapping("/{id}")
    public Associado listAssociadoById(@PathVariable String id) {
        return this.service.listAssociadoById(id);
    }

    @GetMapping("/delete/{id}")
    public void deleteAssociadById(@PathVariable String id) {
        this.service.deleteAssociado(id);
    }

    @GetMapping("/update/{id}")
    public Associado updateAssociado(@PathVariable String id, @RequestBody Associado associado) {
        return this.service.updateAssociadoById(id, associado);
    }

    @GetMapping("/getAllPagamentos")
    public List<AssociadoPagamentosRes> getAssociadosPagamento() {
        return this.service.getAllPagamentosAssociados();
    }

    @GetMapping("/getPagamentoAssociado/{id}")
    public AssociadoPagamentosRes getAssociadoPagamentoById(@PathVariable String id) {
        return this.service.getAssociadoPagamentoById(id);
    }

    @GetMapping("/meusCursos/{id}")
    public ResponseEntity<List<CursosAssociadoRes>> listCursoCadastradoByAssociado(@PathVariable String id) {
        return ResponseEntity.status(201).body(this.cursoService.listCursoByAssociado(id));
    }

    @GetMapping("/pagos")
    public ResponseEntity<List<Associado>> listAssociadosPago() {
        return ResponseEntity.status(201).body(this.service.listAssociadosByPago());
    }

    @GetMapping("/pendentes")
    public ResponseEntity<List<Associado>> listAssociadosPendentes() {
        return ResponseEntity.status(201).body(this.service.listAssociadosByPendente());
    }

    @GetMapping("/naopagos")
    public ResponseEntity<List<Associado>> listAssociadosNaoPago() {
        return ResponseEntity.status(201).body(this.service.listAssociadosByNaoPago());
    }

    @GetMapping("/metodo/credito")
    public ResponseEntity<List<Transacao>> listMetodoPagamentoCredito() {
        return ResponseEntity.status(201).body(this.service.listPagamentoCredito());
    }

    @GetMapping("/metodo/debito")
    public ResponseEntity<List<Transacao>> listMetodoPagamentoDebito() {
        return ResponseEntity.status(201).body(this.service.listPagamentoDebito());
    }

    @GetMapping("/metodo/dinheiro")
    public ResponseEntity<List<Transacao>> listMetodoPagamentoDinheiro() {
        return ResponseEntity.status(201).body(this.service.listPagamentoDinheiro());
    }

    @GetMapping("/pagarAssociacao/{idAssociado}")
    public ResponseEntity<Boolean> pagarAssociacao(@PathVariable String idAssociado) {
        if (this.service.pagarAssociacao(idAssociado)) {
            return ResponseEntity.status(200).body(true);
        } else {
            return ResponseEntity.status(402).body(false);
        }
    }

}
