package br.api.hallel.controller;

import br.api.hallel.model.Associado;
import br.api.hallel.model.Membro;
import br.api.hallel.model.MetodoPagamento;
import br.api.hallel.model.Transacao;
import br.api.hallel.repository.MembroRepository;
import br.api.hallel.service.AssociadoService;
import br.api.hallel.service.MembroService;
import br.api.hallel.service.TransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/associado")
@CrossOrigin("*")
public class AssociadoController {


    @Autowired
    private AssociadoService service;
    @Autowired
    private TransacaoService transacaoService;
    @Autowired
    private MembroRepository repository;

    @GetMapping("/criar/{email}")
    public String createAssociado(@RequestBody Associado associado, @RequestBody Transacao transacao,
                                  @PathVariable String email ) {

        //PARA OCORRER A CRIAÇÃO DE ASSOCIADO, DEVE TER UMA TRANSAÇÃO

        //EXEMPLO DE COMO TAVA FUNCIONANDO

    /*

        Transacao transacao = new Transacao();
        transacao.setNomeTransacao("Transação muhaha");
        transacao.setMetodoPagamento(MetodoPagamento.CARTAO_CREDITO);
        transacao.setDataExp("08/02/2023");


        Associado associado = new Associado();
        Optional<Membro> optional = this.repository.findByEmail("miguel@gmail.com");

        if(optional.isPresent()){
            Membro membro = optional.get();
            associado.setId(membro.getId());
            associado.setNome(membro.getNome());
            associado.setEmail(membro.getEmail());
            associado.setStatus(membro.getStatus());

            associado.setTransacao(transacao);
            associado.setMensalidadePaga(true);
            associado.getTransacao().setDataExp(transacao.getDataExp());
            this.transacaoService.createAssociado(associado);

        }
    */

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
