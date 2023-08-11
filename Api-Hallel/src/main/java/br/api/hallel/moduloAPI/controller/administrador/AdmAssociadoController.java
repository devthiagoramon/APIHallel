package br.api.hallel.moduloAPI.controller.administrador;

import br.api.hallel.moduloAPI.financeiroNovo.payload.response.PagamentoAssociadoResponse;
import br.api.hallel.moduloAPI.model.Associado;
import br.api.hallel.moduloAPI.model.Transacao;
import br.api.hallel.moduloAPI.payload.requerimento.PagamentoAssociadoRequest;
import br.api.hallel.moduloAPI.payload.requerimento.VirarAssociadoRequest;
import br.api.hallel.moduloAPI.payload.resposta.AssociadoPagamentosRes;
import br.api.hallel.moduloAPI.payload.resposta.AssociadoResponseList;
import br.api.hallel.moduloAPI.service.AssociadoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/administrador/associado")
@Slf4j
public class AdmAssociadoController {

    //CLASSE DE CONTROLLER COM TODAS AS FUNÇÕES DE ADM NA PARTE DE ASSOCIAÇÃO

    @Autowired
    private AssociadoService service;

    //LISTAR TODOS OS ASSOCIADOS
    @GetMapping("/listAll")
    public List<Associado> listAllAssociados() {
        return this.service.listAllAssociado();
    }


    //LISTAR TODOS OS ASSOCIADOS POR MES E ANO PAGOS
    @GetMapping("/listByMesAno")
    public List<AssociadoResponseList> listAllAssociadosByMesAnoPagos(@RequestParam(value = "mes") String mes,
                                                                      @RequestParam(value = "ano") String ano) {
        return this.service.listAllAssociadoByMesAno(mes, ano);
    }

    //CRIAR/ADICIONAR UM ASSOCIADO
    @PostMapping("/criar")
    public ResponseEntity<Boolean> createAssociado(@RequestBody VirarAssociadoRequest virarAssociadoRequest) {
        Boolean booleanResposta = this.service.criarAssociado(virarAssociadoRequest);
        if (booleanResposta) {
            return ResponseEntity.status(200).body(true);
        } else {
            return ResponseEntity.status(402).body(false);
        }
    }

    //LISTAR UM ASSOCIADO POR ID
    @GetMapping("/{id}")
    public Associado listAssociadoById(@PathVariable String id) {
        return this.service.listAssociadoById(id);
    }

    //REMOVER UM ASSOCIADO POR SEU ID
    @GetMapping("/delete/{id}")
    public void deleteAssociadById(@PathVariable String id) {
        this.service.deleteAssociado(id);
    }

    //ATUALIZAR INFORMAÇÕES DE UM ASSOCIADO POR ID
    @GetMapping("/update/{id}")
    public Associado updateAssociado(@PathVariable String id, @RequestBody Associado associado) {
        return this.service.updateAssociadoById(id, associado);
    }

    //LISTAR TODOS OS PAGAMENTOS
    @GetMapping("/getAllPagamentos")
    public List<AssociadoPagamentosRes> getAssociadosPagamento() {
        return this.service.getAllPagamentosAssociados();
    }

    //LISTAR PAGAMENTOS DE UM ASSOCIADO
    @GetMapping("/getPagamentoAssociado/{id}")
    public AssociadoPagamentosRes getAssociadoPagamentoById(@PathVariable String id) {
        return this.service.getAssociadoPagamentoById(id);
    }

    //LISTAR ASSOCIADOS PAGOS (STATUS DO ASSOCIADO)
    @GetMapping("/pagos")
    public ResponseEntity<List<Associado>> listAssociadosPago() {
        return ResponseEntity.status(201).body(this.service.listAssociadosByPago());
    }

    //LISTAR ASSOCIADOS PENDENTES (STATUS DO ASSOCIADO)
    @GetMapping("/pendentes")
    public ResponseEntity<List<Associado>> listAssociadosPendentes() {
        return ResponseEntity.status(201).body(this.service.listAssociadosByPendente());
    }

    //LISTAR ASSOCIADOS NAO PAGOS (STATUS DO ASSOCIADO)
    @GetMapping("/naopagos")
    public ResponseEntity<List<Associado>> listAssociadosNaoPago() {
        return ResponseEntity.status(201).body(this.service.listAssociadosByNaoPago());
    }

    //LISTAR PAGAMENTOS FEITOS NO CREDITO
    @GetMapping("/metodo/credito")
    public ResponseEntity<List<Transacao>> listMetodoPagamentoCredito() {
        return ResponseEntity.status(201).body(this.service.listPagamentoCredito());
    }

    //LISTAR PAGAMENTOS FEITOS NO DEBITO
    @GetMapping("/metodo/debito")
    public ResponseEntity<List<Transacao>> listMetodoPagamentoDebito() {
        return ResponseEntity.status(201).body(this.service.listPagamentoDebito());
    }

    //LISTAR PAGAMENTOS FEITOS NO DINHEIRO
    @GetMapping("/metodo/dinheiro")
    public ResponseEntity<List<Transacao>> listMetodoPagamentoDinheiro() {
        return ResponseEntity.status(201).body(this.service.listPagamentoDinheiro());
    }

    //LISTAR DATAS DE PAGAMENTO
    @GetMapping("/datasPagas/{idAssociado}")
    public ResponseEntity<List<Date>> listarDatasPagasAssociados(@PathVariable String idAssociado){
        return ResponseEntity.status(200).body(this.service.listarDatasPagas(idAssociado));
    }

    //LISTAR LISTAR PAGAMENTOS DE UM ASSOCIADO (MES E ANO)
    @GetMapping("/pagamento/{idAssociado}")
    public ResponseEntity<PagamentoAssociadoResponse> listarPagamentoAssociadoByMesAndAno
            (@PathVariable String idAssociado,
             @RequestParam(value = "mes") String mes,
             @RequestParam(value = "ano") String ano){
        return ResponseEntity.status(200).body(this.service.listarPagamentoByMesAno(idAssociado, mes, ano));
    }
}
