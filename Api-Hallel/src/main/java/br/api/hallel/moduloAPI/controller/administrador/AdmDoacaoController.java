package br.api.hallel.moduloAPI.controller.administrador;

import br.api.hallel.moduloAPI.model.Doacao;
import br.api.hallel.moduloAPI.model.DoacaoObjeto;
import br.api.hallel.moduloAPI.payload.resposta.DoacoesDinheiroListaAdmResponse;
import br.api.hallel.moduloAPI.payload.resposta.DoacoesObjetoListaAdmResponse;
import br.api.hallel.moduloAPI.service.financeiro.DoacaoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/administrador/doacao")
@Slf4j
public class AdmDoacaoController {

    @Autowired
    private DoacaoService service;

    //ALTERA O STATUS DA DOAÇÃO DE OBJETO PARA 'RECEBIDO'
    @PostMapping("/{id}/recebido")
    public DoacaoObjeto objetoRecebido(@PathVariable String id) {
        return this.service.objetoRecebido(id);
    }

    //ALTERA O STATUS DA DOAÇÃO DE OBJETO PARA 'NÃO RECEBIDO'
    @PostMapping("/{id}/naoRecebido")
    public DoacaoObjeto objetoNaoRecebido(@PathVariable String id) {
        return this.service.objetoNaoRecebido(id);
    }

     //LISTA TODAS AS DOAÇÕES
    @GetMapping("/list")
    private List<DoacoesDinheiroListaAdmResponse> doacaoList(@RequestParam(name = "mes") String mes,
                                                             @RequestParam(name = "ano") String ano) {
        return this.service.listAllDoacoes(mes, ano);
    }

    //LISTA TODAS AS DOAÇÕES DE OBJETO
    @GetMapping("/listObjetos")
    private List<DoacoesObjetoListaAdmResponse> doacoesObjList() {
        return this.service.listAllDoacoesObjeto();
    }

    //LISTA UMA DOAÇÃO POR ID
    @GetMapping("/{id}")
    private Doacao listDoacaoById(@PathVariable String id) {
        return this.service.listDoacaoById(id);
    }

    //LISTA UMA DOAÇÃO DE OBJETO POR ID
    @GetMapping("/objeto/{id}")
    private DoacaoObjeto listDoacaoObjetoById(@PathVariable String id) {
        return this.service.listDoacaoObjetoById(id);
    }

    //LISTA AS DOAÇÕES REALIZADAS NO DIA
    @GetMapping("/list/dia")
    private List<DoacoesDinheiroListaAdmResponse> doacoesThisDay() {
        return this.service.listAllDoacaoDinheiroByThisDay();
    }

    //LISTA AS DOAÇÕES REALIZADAS NA SEMANA
    @GetMapping("/list/semana")
    private List<DoacoesDinheiroListaAdmResponse> doacoesThisWeek() {
        return this.service.listAllDoacaoDinheiroByThisWeek();
    }
}
