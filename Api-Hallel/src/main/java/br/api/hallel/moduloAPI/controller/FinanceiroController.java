package br.api.hallel.moduloAPI.controller;


import br.api.hallel.moduloAPI.model.*;
import br.api.hallel.moduloAPI.payload.requerimento.CodigosSaidaReq;
import br.api.hallel.moduloAPI.payload.requerimento.GastoReq;
import br.api.hallel.moduloAPI.payload.requerimento.ReceitaReq;
import br.api.hallel.moduloAPI.payload.resposta.ReceitasDiaAtualResponse;
import br.api.hallel.moduloAPI.payload.resposta.ReceitasSemanaAtualResponse;
import br.api.hallel.moduloAPI.service.CodigoSaidaService;
import br.api.hallel.moduloAPI.service.FinanceiroService;
import br.api.hallel.moduloAPI.service.GastoService;
import br.api.hallel.moduloAPI.service.ReceitaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/financeiro")
@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@Log4j2
public class FinanceiroController {

    @Autowired
    private GastoService gastoService;
    @Autowired
    private ReceitaService receitaService;
    @Autowired
    private FinanceiroService financeiroService;

    @Autowired
    private CodigoSaidaService codigoSaidaService;

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
    public Double lucro() {
        return this.financeiroService.lucro();
    }

    @GetMapping("/lucroMensal")
    public Double lucroMensal(@RequestParam(name = "mes") String mes, @RequestParam(name = "ano") String ano) {
        return this.financeiroService.lucroMensal(mes, ano);
    }

    @GetMapping("/gastoMensal")
    public Double gastoMensal(@RequestParam(name = "mes") String mes, @RequestParam(name = "ano") String ano) {
        return this.financeiroService.gastoMensal(mes, ano);
    }

    @GetMapping("/entradasMes/valor")
    public ResponseEntity<Double> entradasMensais(@RequestParam(name = "mes") String mes,
                                                  @RequestParam(name = "ano") String ano) {
        return ResponseEntity.ok().body(this.financeiroService.entradasMesValor(mes, ano));
    }

    @GetMapping("/saidaMes/valor")
    public ResponseEntity<Double> saidaMensais(@RequestParam(name = "mes") String mes,
                                               @RequestParam(name = "ano") String ano) {
        return ResponseEntity.ok().body(this.financeiroService.saidaMesValor(mes, ano));
    }

    //GASTOS

    //LISTA DESPESAS
    @GetMapping("/gastos")
    public List<GastoFinanceiro> listGastos(@RequestParam(name = "mes") String mes,
                                            @RequestParam(name = "ano") String ano) {
        return this.gastoService.listAll(mes, ano);
    }

    //CRIA DESPESAS
    @PostMapping("/gasto/criar")
    public GastoFinanceiro createGasto(@RequestBody GastoFinanceiro gastoFinanceiro) {
        return this.gastoService.createGasto(gastoFinanceiro);
    }

    @GetMapping("/gastos/thisDay")
    public List<GastoFinanceiro> listGastosByDay() {
        return this.gastoService.listAllByThisDay();
    }

    @GetMapping("/gastos/thisWeek")
    public List<GastoFinanceiro> listGastosByWeek() {
        return this.gastoService.listAllByThisWeek();
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

    @GetMapping("/ultimasSaida")
    public List<SaidaFinanceiraResponseUltimas> listUltimasSaidas() {
        return this.gastoService.listUltimasSaidas();
    }

    @PostMapping("/codigosSaida/adicionar")
    public ResponseEntity<Boolean> adicionarCodigoSaida(@RequestBody CodigosSaidaReq codigosSaidaReq) {
        return ResponseEntity.ok().body(this.codigoSaidaService.adicionarCodigoSaida(codigosSaidaReq));
    }

    @GetMapping("/codigosSaida/list")
    public ResponseEntity<List<CodigosSaida>> listarCodigosSaida() {
        return ResponseEntity.ok().body(this.codigoSaidaService.listarCodigosSaida());
    }

    @GetMapping("/codigosSaida/{numCodigo}/list")
    public ResponseEntity<CodigosSaida> listarCodigoSaidaByNumCodigo(@PathVariable Double numCodigo) {
        return ResponseEntity.ok().body(this.codigoSaidaService.listarCodigosSaidaPeloNumCodigo(numCodigo));
    }

    // Receitas

    //LISTA RECEITAS
    @GetMapping("/receitas")
    public List<ReceitaFinanceira> listReceitas(@RequestParam(name = "mes") String mes,
                                                @RequestParam(name = "ano") String ano) {
        return this.receitaService.listAll(mes, ano);
    }

    @GetMapping("/receita/thisDay")
    public List<ReceitaFinanceira> listReceitasByThisDay() {
        return this.receitaService.listAllByThisDay();
    }

    @GetMapping("/receita/thisWeek")
    public List<ReceitaFinanceira> listReceitaByThisWeek() {
        return this.receitaService.listAllByThisWeek();
    }

    @GetMapping("/ultimasEntradas")
    public List<EntradaFinanceiraResponseUltimas> listUltimasEntradas() {
        return this.receitaService.listUltimasEntradas();
    }

    // LISTA BASEADA EM DATA
    @GetMapping("/receitas/dia")
    public ReceitasDiaAtualResponse listFinanceiraDia() {
        return this.receitaService.getValorTotalByThisDay();
    }

    @GetMapping("/receita/semana")
    public ReceitasSemanaAtualResponse listFinanceiraSemana() {
        return this.receitaService.getValorTotalByThisWeek();
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


    @PutMapping("/meta/alterar")
    public ResponseEntity<String> updateMeta(@RequestParam(name = "mes") String mes,
                                             @RequestParam(name = "ano") String ano,
                                             @RequestParam(name = "meta") String metaAtualizada) {
        this.financeiroService.alterarMeta(mes, ano, metaAtualizada);
        log.info("Meta " + mes + "/" + ano + " alterado");
        return ResponseEntity.ok().body("Meta " + mes + "/" + ano + " alterado");
    }

    @GetMapping("/meta/listar")
    public ResponseEntity<Double> listMeta(@RequestParam(name = "mes") String mes,
                                           @RequestParam(name = "ano") String ano) {
        return ResponseEntity.ok().body(this.financeiroService.listMetaMensal(mes, ano));
    }

    @GetMapping("/meta/porcentagem")
    public ResponseEntity<Double> listMetaPorcentagem(@RequestParam(name = "mes") String mes,
                                                      @RequestParam(name = "ano") String ano) {
        return ResponseEntity.ok().body(this.financeiroService.listMetaMensalPorcentagem(mes, ano));
    }


}
