package br.api.hallel.controller;

import br.api.hallel.model.Doacao;
import br.api.hallel.model.DoacaoObjeto;
import br.api.hallel.model.DoadorReq;
import br.api.hallel.payload.requerimento.DoacaoObjetoReq;
import br.api.hallel.payload.requerimento.DoacaoReq;
import br.api.hallel.payload.resposta.DoacoesDinheiroListaAdmResponse;
import br.api.hallel.payload.resposta.DoacoesObjetoListaAdmResponse;
import br.api.hallel.service.DoacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/doacao")
@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
public class DoacaoController {

    @Autowired
    private DoacaoService service;

    @PostMapping("/doar")
    private Doacao doar(@RequestBody DoacaoReq doacaoReq){
        return this.service.doar(doacaoReq);
    }

    @PostMapping("/doarObjeto")
    private DoacaoObjeto doarObjeto(@RequestBody DoacaoObjetoReq doacaoObjetoReq){
        return this.service.doarObjeto(doacaoObjetoReq);
    }

    @PostMapping("{id}/recebido")
    public DoacaoObjeto objetoRecebido(@PathVariable String id){
        return this.service.objetoRecebido(id);
    }

    @PostMapping("{id}/naoRecebido")
    public DoacaoObjeto objetoNaoRecebido(@PathVariable String id){
        return this.service.objetoNaoRecebido(id);
    }

    @GetMapping("/list")
    private List<DoacoesDinheiroListaAdmResponse> doacaoList() {return this.service.listAllDoacoes();}

    @GetMapping("/listObjetos")
    private List<DoacoesObjetoListaAdmResponse> doacoesObjList(){return this.service.listAllDoacoesObjeto();};

    @GetMapping("/{id}")
    private Doacao listDoacaoById(@PathVariable String id){return this.service.listDoacaoById(id);}

    @GetMapping("/objeto/{id}")
    private DoacaoObjeto listDoacaoObjetoById(@PathVariable String id){return this.service.listDoacaoObjetoById(id);};

    @GetMapping("/list/thisDay")
    private List<DoacoesDinheiroListaAdmResponse> doacoesThisDay(){
        return this.service.listAllDoacaoDinheiroByThisDay();
    }

    @GetMapping("/list/thisWeek")
    private List<DoacoesDinheiroListaAdmResponse> doacoesThisWeek(){
        return this.service.listAllDoacaoDinheiroByThisWeek();
    }

    @PostMapping("/virarDoador")
    private String virarDoador(@RequestBody DoadorReq doadorReq){

        return "Error em virar doador";
    }

}

