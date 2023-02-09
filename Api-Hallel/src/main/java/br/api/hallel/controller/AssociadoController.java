package br.api.hallel.controller;

import br.api.hallel.model.Associado;
import br.api.hallel.model.MetodoPagamento;
import br.api.hallel.model.Transacao;
import br.api.hallel.service.AssociadoService;
import br.api.hallel.service.TransacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/associado")
@CrossOrigin("*")
public class AssociadoController {


    @Autowired
    private AssociadoService service;
    @Autowired
    private TransacaoService transacaoService;

    @PostMapping("/criar")
    public String createAssociado(){

        //PARA OCORRER A CRIAÇÃO DE ASSOCIADO, DEVE TER UMA TRANSAÇÃO

        Transacao transacao = new Transacao();
        transacao.setNomeTransacao("Transação muhaha");
        transacao.setMetodoPagamento(MetodoPagamento.CARTAO_CREDITO);
        transacao.setDataExp("23/12/2023");


       Associado associado = new Associado();
        associado.setId("123");
        associado.setNome("Migas");
        associado.setTransacao(transacao);
        associado.setMensalidadePaga(true);
        associado.getTransacao().setDataExp(transacao.getDataExp());
        this.transacaoService.createAssociado(associado);


        return associado.toString();
    }

    @GetMapping("")
    public List<Associado> listAllAssociados(){
        return this.service.listAllAssociado();
    }

    @GetMapping("/{id}")
    public Associado listAssociadoById(@PathVariable String id){
        return this.service.listAssociadoById(id);
    }

    @GetMapping("/delete/{id}")
    public void deleteAssociadById(@PathVariable String id){
        this.service.deleteAssociado(id);
    }

    @GetMapping("/update/{id}")
    public Associado updateAssociado(@PathVariable String id, @RequestBody Associado associado){
        return this.service.updateAssociadoById(id, associado);
    }
}
