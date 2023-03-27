package br.api.hallel.controller;


import br.api.hallel.model.Financeiro;
import br.api.hallel.model.GastoFinanceiro;
import br.api.hallel.model.ReceitaFinanceira;
import br.api.hallel.payload.requerimento.GastoReq;
import br.api.hallel.payload.requerimento.ReceitaReq;
import br.api.hallel.service.FinanceiroService;
import br.api.hallel.service.GastoService;
import br.api.hallel.service.ReceitaService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/api/financeiro")
@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
public class FinanceiroController {

    @Autowired
    GastoService gastoService;
    @Autowired
    ReceitaService receitaService;
    @Autowired
    FinanceiroService financeiroService;

    //FINANCEIRO

    @PostMapping("/criar")
    public Financeiro createFinanceiro(@RequestBody Financeiro financeiro) {
        return this.financeiroService.createFinanceiro(financeiro);
    }
    @GetMapping("/delete/{id}")
    public void deleteFinanceiro(@PathVariable String id) {
        this.financeiroService.deleteFinanceiro(id);
    }

    @GetMapping("/update")
    public Financeiro update(@RequestBody Financeiro financeiro) {
        return this.financeiroService.update(financeiro);
    }
    @GetMapping("/lucro")
    public Double lucro(){
        return this.financeiroService.lucro();
    }
    @GetMapping("/lucroMensal")
    public Double lucroMensal(){
        return this.financeiroService.lucroMensal();
    }
    @GetMapping("/gastoMensal")
    public Double gastoMensal(){
        return this.financeiroService.gastoMensal();
    }

    //GASTOS

    //LISTA DESPESAS
    @GetMapping("/gastos")
    public List<GastoFinanceiro> listGastos() {
        return this.gastoService.listAll();
    }

    //CRIA DESPESAS
    @PostMapping("/gasto/criar")
    public GastoFinanceiro createGasto(@RequestBody GastoFinanceiro gastoFinanceiro) {
        return this.gastoService.createGasto(gastoFinanceiro);
    }

    //DELETA DESPESAS
    @GetMapping("/gasto/deletar/{id}")
    public void deleteGasto(@PathVariable String id) {
        this.gastoService.deleteGasto(id);
    }

    //SELECIONA SOMENTE UMA DESPESA
    @GetMapping("/gasto/{id}")
    public GastoFinanceiro listGastoById(@PathVariable String id) {
        return this.gastoService.listById(id);
    }

    //ATUALIZA INFORMAÇÕES DESSA DESPESA
    @PostMapping("/gasto/update/{id}")
    public GastoFinanceiro updateGasto(@PathVariable String id, @RequestBody GastoReq gasto) {
        return this.gastoService.update(id, gasto);
    }

    // Receitas

    //LISTA RECEITAS
    @GetMapping("/receita")
    public List<ReceitaFinanceira> listReceitas() {
        return this.receitaService.listAll();
    }

    @GetMapping("/ultimasReceitas")
    public List<ReceitaFinanceira> listUltimasReceitas(){
        return this.receitaService.listUltimasReceitas();
    }

    //CRIA RECEITAS
    @PostMapping("/receita/criar")
    public ReceitaFinanceira createReceita(@RequestBody ReceitaFinanceira receitaFinanceira) {
        return this.receitaService.createReceita(receitaFinanceira);
    }

    //DELETE RECEITAS
    @GetMapping("/receita/deletar/{id}")
    public void deleteReceita(@PathVariable String id) {
        this.receitaService.deleteReceita(id);
    }

    //SELECIONA SOMENTE UMA RECEITA
    @GetMapping("/receita/{id}")
    public ReceitaFinanceira listReceitaById(@PathVariable String id) {
        return this.receitaService.listById(id);
    }

    //ATUALIZA INFORMAÇÕES DESSA RECEITA
    @GetMapping("/receita/update/{id}")
    public ReceitaFinanceira updateReceita(@PathVariable String id, @RequestBody ReceitaReq receita) {
        return this.receitaService.update(id, receita);
    }


}
