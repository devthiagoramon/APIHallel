package br.api.hallel.moduloAPI.service;

import br.api.hallel.moduloAPI.model.EntradaMensalFinanceiro;
import br.api.hallel.moduloAPI.model.Financeiro;
import br.api.hallel.moduloAPI.model.GastoFinanceiro;
import br.api.hallel.moduloAPI.model.ReceitaFinanceira;
import br.api.hallel.moduloAPI.repository.FinanceiroRepository;
import br.api.hallel.moduloAPI.service.interfaces.FinanceiroInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.WeekFields;
import java.util.*;

@Service
public class FinanceiroService implements FinanceiroInterface {

    @Autowired
    FinanceiroRepository repository;
    private DecimalFormat decimalFormat = new DecimalFormat("##");

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
            if (financeiro.getValorGastos() != null) {
                financeiro.getValorGastos().add(gastoFinanceiro.getValor());
            } else {
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
            if (financeiro.getReceita() != null) {
                financeiro.getReceita().add(receitaFinanceira);
            } else {
                ArrayList<ReceitaFinanceira> receita = new ArrayList<>();
                receita.add(receitaFinanceira);
                financeiro.setReceita(receita);
            }
            if (financeiro.getValorReceitas() != null) {
                financeiro.getValorReceitas().add(receitaFinanceira.getValor());
            } else {
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

        if (financeiro.getValorReceitas() != null) {
            receita.addAll(financeiro.getValorReceitas());
        } else {
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

        if (financeiro.getValorGastos() != null) {
            gasto.addAll(financeiro.getValorGastos());
        } else {
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

    @Override
    public void alterarMeta(String mes, String ano, String metaAtualizada) {
        Financeiro financeiro = getFinanceiro();

        if (financeiro.getEntradasMensais() != null) {
            for (EntradaMensalFinanceiro entradasMensai : financeiro.getEntradasMensais()) {
                if (entradasMensai.getMes().equals(mes) && entradasMensai.getAno().equals(ano)) {
                    int index = financeiro.getEntradasMensais().indexOf(entradasMensai);

                    String metaToDoubleString = metaAtualizada.replace("R$", "");
                    metaToDoubleString = metaToDoubleString.replace(".", "");
                    metaToDoubleString = metaToDoubleString.replace(",", ".");
                    metaToDoubleString = metaToDoubleString.substring(1);

                    Double metaToDouble = Double.parseDouble(metaToDoubleString);

                    EntradaMensalFinanceiro entradaAtualizada = entradasMensai;
                    entradaAtualizada.setMeta(metaToDouble);

                    financeiro.getEntradasMensais().set(index, entradaAtualizada);
                }
            }
        }
        this.repository.save(financeiro);
    }

    @Override
    public Double listMetaMensal(String mes, String ano) {

        Financeiro financeiro = getFinanceiro();

        EntradaMensalFinanceiro entradaMensalFinanceiro = new EntradaMensalFinanceiro();

        if (financeiro.getEntradasMensais() != null) {

            for (EntradaMensalFinanceiro entradasMensai : financeiro.getEntradasMensais()) {
                if (entradasMensai.getMes().equals(mes) && entradasMensai.getAno().equals(ano)) {
                    entradaMensalFinanceiro = entradasMensai;
                }
            }
        } else {
            entradaMensalFinanceiro.setMes(mes);
            entradaMensalFinanceiro.setAno(ano);
            entradaMensalFinanceiro.setMeta(0.0);
            financeiro.setEntradasMensais(new ArrayList<>());
            financeiro.getEntradasMensais().add(entradaMensalFinanceiro);
            this.repository.save(financeiro);
        }

        return entradaMensalFinanceiro.getMeta();
    }

    @Override
    public Double listMetaMensalPorcentagem(String mes, String ano) {

        Financeiro financeiro = getFinanceiro();

        Double porcentagem = 0.0;

        EntradaMensalFinanceiro entradaMensalFinanceiro = null;

        if (financeiro.getEntradasMensais() != null) {
            for (EntradaMensalFinanceiro entradasMensai : financeiro.getEntradasMensais()) {
                if (entradasMensai.getMes().equals(mes) && entradasMensai.getAno().equals(ano)) {
                    entradaMensalFinanceiro = entradasMensai;
                }
            }

            porcentagem = (financeiro.getReceitaMensal() * 100) / entradaMensalFinanceiro.getMeta();
            System.out.println(porcentagem);
        }
        return Double.parseDouble(decimalFormat.format(porcentagem));
    }

    @Override
    public Double entradasMesValor(String mes, String ano) {

        Double totalEntradaMes = 0.0;

        Financeiro financeiro = getFinanceiro();

        for (ReceitaFinanceira receitaFinanceira : financeiro.getReceita()) {
            if (receitaFinanceira.getDataReceita().substring(3).equals(mes + "/" + ano)) {
                totalEntradaMes += receitaFinanceira.getValor();
            }
        }

        return totalEntradaMes;
    }

    @Override
    public Double saidaMesValor(String mes, String ano) {
        Double totalSaidaMes = 0.0;

        Financeiro financeiro = getFinanceiro();

        if(financeiro.getGastos()!=null) {
            for (GastoFinanceiro gastoFinanceiro : financeiro.getGastos()) {
                if (gastoFinanceiro.getDataGasto().substring(3).equals(mes + "/" + ano)) {
                    totalSaidaMes += gastoFinanceiro.getValor();
                }
            }
        }

        return totalSaidaMes;
    }

}


