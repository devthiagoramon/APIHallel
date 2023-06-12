package br.api.hallel.moduloAPI.service;

import br.api.hallel.moduloAPI.model.Financeiro;
import br.api.hallel.moduloAPI.model.GastoFinanceiro;
import br.api.hallel.moduloAPI.model.ReceitaFinanceira;
import br.api.hallel.moduloAPI.repository.FinanceiroRepository;
import br.api.hallel.moduloAPI.service.interfaces.FinanceiroInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FinanceiroService implements FinanceiroInterface {

    @Autowired
    FinanceiroRepository repository;

    @Override
    public Financeiro createFinanceiro(Financeiro financeiro) {
        return this.repository.insert(financeiro);
    }

    @Override
    public Financeiro update(Financeiro financeiro) {
        return this.repository.save(financeiro);
    }




    //RESGATA DO BANCO DE DADOS, O FINANCEIRO DA COMUNIDADE
    @Override
    public Financeiro getFinanceiro() {
        return repository.findById("63f80b6ed5f23b6d7597b46a").get() != null ?
                repository.findById("63f80b6ed5f23b6d7597b46a").get() :
                null;
    }

    //REALIZA UMA LÓGICA PARA VER SE ESTÁ CRIADO OU NÃO
    //ADICIONA OS DESPESAS FINANCEIROS À COMUNIDADE
    @Override
    public void salvarGasto(GastoFinanceiro gastoFinanceiro) {
        Financeiro financeiro = getFinanceiro();

        if (financeiro.getGastos() != null) {
            financeiro.getGastos().add(gastoFinanceiro);
            if(financeiro.getValorGastos()!=null) {
                financeiro.getValorGastos().add(gastoFinanceiro.getValor());
            }else{
                ArrayList<Double> gastos = new ArrayList<>();
                gastos.add(gastoFinanceiro.getValor());
                financeiro.setValorGastos(gastos);
            }
            somaGasto(financeiro, gastoFinanceiro.getValor());
        } else {
            ArrayList<GastoFinanceiro> gastoArray = new ArrayList<>();
            ArrayList<Double> valorArray = new ArrayList<>();

            gastoArray.add(gastoFinanceiro);
            valorArray.add(gastoFinanceiro.getValor());

            financeiro.setGastos(gastoArray);
            financeiro.setValorGastos(valorArray);
        }
        this.repository.save(financeiro);
    }


    //ADICIONA RECEITAS FINANCEIRA À COMUNIDADE
    //REALIZA UMA LÓGICA PARA VER SE ESTÁ CRIADO OU NÃO
    @Override
    public void salvarReceita(ReceitaFinanceira receitaFinanceira) {
        Financeiro financeiro = getFinanceiro();

        if (financeiro.getGastos() != null) {
            if(financeiro.getReceita() != null) {
                financeiro.getReceita().add(receitaFinanceira);
            }else{
                ArrayList<ReceitaFinanceira> receita = new ArrayList<>();
                receita.add(receitaFinanceira);
                financeiro.setReceita(receita);
            }
            if(financeiro.getValorReceitas() != null){
                financeiro.getValorReceitas().add(receitaFinanceira.getValor());
            }else{
                ArrayList<Double> receitas = new ArrayList<>();
                receitas.add(receitaFinanceira.getValor());
                financeiro.setValorReceitas(receitas);
            }
            somaReceita(financeiro, receitaFinanceira.getValor());
        } else {

            ArrayList<ReceitaFinanceira> receitaArray = new ArrayList<>();
            ArrayList<Double> valorArray = new ArrayList<>();

            receitaArray.add(receitaFinanceira);
            valorArray.add(receitaFinanceira.getValor());

            financeiro.setReceita(receitaArray);
            financeiro.setValorReceitas(valorArray);

        }
        this.repository.save(financeiro);
    }

    //LISTA TODAS AS RECEITAS DA COMUNIDADE
    @Override
    public List<Financeiro> getReceitas() {
        return this.repository.findAll();
    }

    //LISTA TODOS AS DESPESAS DA COMUNIDADE
    @Override
    public List<Financeiro> getGastos() {
        return this.repository.findAll();
    }

    //MÉTODO SIMPLES PARA OBTER O LUCRO
    @Override
    public Double lucro() {
        Financeiro financeiro = getFinanceiro();
        Double lucro = (financeiro.getReceitaProvisoria() - financeiro.getGastoProvisorio());
        return lucro;
    }

    public Double lucroMensalCalc() {
        Financeiro financeiro = getFinanceiro();
        Double lucroMensal = (financeiro.getReceitaMensal() - financeiro.getGastoMensal());
        return lucroMensal;
    }

    //REMOVER FINANCEIRO
    @Override
    public void deleteFinanceiro(String id) {
        this.repository.deleteById(id);
    }

    //SOMA DE RECEITA
    public void somaReceita(Financeiro financeiro, Double somaTotal) {

        List<Double> receita = new ArrayList<>();

        if(financeiro.getValorReceitas() != null) {
            receita.addAll(financeiro.getValorReceitas());
        }else{
            List<Double> receitaNew = new ArrayList<>();
            receita.addAll(receitaNew);
        }

        for (int i = 0; i < receita.size(); i++) {
            somaTotal += receita.get(i);
        }

        financeiro.setReceitaProvisoria(somaTotal);

        update(financeiro);

    }

    //SOMA DE DESPESAS
    public void somaGasto(Financeiro financeiro, Double somaTotal) {

        List<Double> gasto = new ArrayList<>();

        if(financeiro.getValorGastos() != null) {
            gasto.addAll(financeiro.getValorGastos());
        }else{
            List<Double> gastoNew = new ArrayList<>();
            gasto.addAll(gastoNew);
        }

        for (int i = 0; i < gasto.size(); i++) {
            somaTotal += gasto.get(i);

        }

        financeiro.setGastoProvisorio(somaTotal);

        System.out.println();

        update(financeiro);
    }

    @Override
    public Double lucroMensal() {

        Financeiro financeiro = getFinanceiro();

        Integer data = 0;
        Double somaFinanceiro = 0.0, somaGasto = 0.0;
        int cont = 0;

        for (ReceitaFinanceira receitas : financeiro.getReceita()) {

            data = Integer.parseInt(receitas.getDataReceita().substring(0, 2));

            if (data <= 30) {
                somaFinanceiro += receitas.getValor();

            }
            financeiro.setReceitaMensal(somaFinanceiro);

        }

        for (GastoFinanceiro gasto : financeiro.getGastos()) {

            data = Integer.parseInt(gasto.getDataGasto().substring(0, 2));

            if (data <= 30) {
                somaGasto += gasto.getValor();

            }
            financeiro.setGastoMensal(somaGasto);

        }

        update(financeiro);
        financeiro.setLucroMensal(lucroMensalCalc());


        return financeiro.getLucroMensal();
    }

    @Override
    public Double gastoMensal() {
        Financeiro financeiro = getFinanceiro();
        return financeiro.getGastoMensal();
    }

}

