package br.api.hallel.moduloAPI.controller.administrador;

import br.api.hallel.moduloAPI.exceptions.ApiError;
import br.api.hallel.moduloAPI.exceptions.associado.AssociadoNotFoundException;
import br.api.hallel.moduloAPI.financeiroNovo.payload.response.PagamentoAssociadoResponse;
import br.api.hallel.moduloAPI.model.Associado;
import br.api.hallel.moduloAPI.model.Transacao;
import br.api.hallel.moduloAPI.payload.resposta.AssociadoPagamentosRes;
import br.api.hallel.moduloAPI.payload.resposta.AssociadoResponseList;
import br.api.hallel.moduloAPI.service.financeiro.AssociadoService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/administrador/associado")
@Log
public class AdmAssociadoController {

    //CLASSE DE CONTROLLER COM TODAS AS FUNÇÕES DE ADM NA PARTE DE ASSOCIAÇÃO

    @Autowired
    private AssociadoService service;

    //LISTAR TODOS OS ASSOCIADOS
    @GetMapping("/listAll")
    public ResponseEntity<?> listAllAssociados() {

        System.out.println("oi");

        if (this.service.listAllAssociado() != null) {
            return ResponseEntity.accepted().body(this.service.listAllAssociado());

        }
        return new ResponseEntity<>(new ApiError(400,"Lista de associado nula!1", new Date()), HttpStatus.BAD_REQUEST);
    }


    //LISTAR TODOS OS ASSOCIADOS POR MES E ANO PAGOS
    @GetMapping("/listByMesAno")
    public List<AssociadoResponseList> listAllAssociadosByMesAnoPagos(@RequestParam(value = "mes") String mes,
                                                                      @RequestParam(value = "ano") String ano) {
        return this.service.listAllAssociadoByMesAno(mes, ano);
    }

    //LISTAR UM ASSOCIADO POR ID
    @GetMapping("/{id}")
    public Associado listAssociadoById(@PathVariable String id) throws AssociadoNotFoundException {
        return this.service.listAssociadoById(id);
    }

    //REMOVER UM ASSOCIADO POR SEU ID
    @GetMapping("/delete/{id}")
    public void deleteAssociadById(@PathVariable String id) {
        try {
            this.service.deleteAssociado(id);
        } catch (AssociadoNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    //ATUALIZAR INFORMAÇÕES DE UM ASSOCIADO POR ID
    @GetMapping("/update/{id}")
    public Associado updateAssociado(@PathVariable String id, @RequestBody Associado associado) {
        try {
            return this.service.updateAssociadoById(id, associado);
        } catch (AssociadoNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    //LISTAR TODOS OS PAGAMENTOS
    @GetMapping("/getAllPagamentos")
    public List<AssociadoPagamentosRes> getAssociadosPagamento() {
        return this.service.getAllPagamentosAssociados();
    }

    //LISTAR PAGAMENTOS DE UM ASSOCIADO
    @GetMapping("/getPagamentoAssociado/{id}")
    public AssociadoPagamentosRes getAssociadoPagamentoById(@PathVariable String id) {
        try {
            return this.service.getAssociadoPagamentoById(id);
        } catch (AssociadoNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    //LISTAR ASSOCIADOS PAGOS (STATUS DO ASSOCIADO)
    @GetMapping("/pagos")
    public ResponseEntity<List<Associado>> listAssociadosPago() {
        return ResponseEntity.accepted().body(this.service.listAssociadosByPago());
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
    public ResponseEntity<List<Date>> listarDatasPagasAssociados(@PathVariable String idAssociado) {
        return ResponseEntity.status(200).body(this.service.listarDatasPagas(idAssociado));
    }

    //LISTAR LISTAR PAGAMENTOS DE UM ASSOCIADO (MES E ANO)
    @GetMapping("/pagamento/{idAssociado}")
    public ResponseEntity<PagamentoAssociadoResponse> listarPagamentoAssociadoByMesAndAno
    (@PathVariable String idAssociado,
     @RequestParam(value = "mes") String mes,
     @RequestParam(value = "ano") String ano) {
        return ResponseEntity.status(200).body(this.service.listarPagamentoByMesAno(idAssociado, mes, ano));
    }
}
