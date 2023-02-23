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

    @GetMapping("/financeiro/criar")
    public Financeiro createFinanceiro(@RequestBody Financeiro financeiro) {
        return this.financeiroService.createFinanceiro(financeiro);
    }

    @GetMapping("/financeiro/delete/{id}")
    public void deleteFinanceiro(@PathVariable String id) {
        this.financeiroService.deleteFinanceiro(id);
    }

    @GetMapping("/financeiro/update")
    public Financeiro update(@RequestBody Financeiro financeiro) {
        return this.financeiroService.update(financeiro);
    }
    @GetMapping("/financeiro/lucro")
    public Double lucro(){
        return this.financeiroService.lucro();
    }

    //GASTOS

    @GetMapping("/gastos")
    public List<GastoFinanceiro> listGastos() {
        return this.gastoService.listAll();
    }

    @GetMapping("/gasto/criar")
    public GastoFinanceiro createGasto(@RequestBody GastoFinanceiro gastoFinanceiro) {
        return this.gastoService.createGasto(gastoFinanceiro);
    }

    @GetMapping("/gasto/deletar/{id}")
    public void deleteGasto(@PathVariable String id) {
        this.gastoService.deleteGasto(id);
    }

    @GetMapping("/gasto/{id}")
    public GastoFinanceiro listGastoById(@PathVariable String id) {
        return this.gastoService.listById(id);
    }

    @GetMapping("/gasto/update/{id}")
    public GastoFinanceiro updateGasto(@PathVariable String id, @RequestBody GastoReq gasto) {
        return this.gastoService.update(id, gasto);
    }


    @GetMapping("/receita")
    public List<ReceitaFinanceira> listReceitas() {
        return this.receitaService.listAll();
    }

    @GetMapping("/receita/criar")
    public ReceitaFinanceira createReceita(@RequestBody ReceitaFinanceira receitaFinanceira) {
        return this.receitaService.createReceita(receitaFinanceira);
    }

    @GetMapping("/receita/deletar/{id}")
    public void deleteReceita(@PathVariable String id) {
        this.receitaService.deleteReceita(id);
    }

    @GetMapping("/receita/{id}")
    public ReceitaFinanceira listReceitaById(@PathVariable String id) {
        return this.receitaService.listById(id);
    }

    @GetMapping("/receita/update/{id}")
    public ReceitaFinanceira updateReceita(@PathVariable String id, @RequestBody ReceitaReq receita) {
        return this.receitaService.update(id, receita);
    }

    public Double somaReceita() {

        List<Financeiro> financeiros = financeiroService.getReceitas();
        List<Double> receita = new ArrayList<>();

        Double soma = 0.0;
        for (Financeiro f : financeiros) {
            receita.addAll(f.getValorReceitas());
        }

        for (int i = 0; i < receita.size(); i++) {
            soma += receita.get(i);
        }

        Financeiro financeiro = new Financeiro();
        financeiro.setId("1");
        financeiro.setReceitaProvisoria(soma);

        this.financeiroService.update(financeiro);
        return soma;

    }

    public Double somaGasto() {

        List<Financeiro> financeiros = financeiroService.getGastos();
        List<Double> gasto = new ArrayList<>();

        Double soma = 0.0;
        for (Financeiro f : financeiros) {
            gasto.addAll(f.getValorGastos());
        }

        for (int i = 0; i < gasto.size(); i++) {
            soma += gasto.get(i);
        }

        Financeiro financeiro = new Financeiro();
        financeiro.setGastoProvisorio(soma);

        this.financeiroService.update(financeiro);
        return soma;

    }

}
