package br.api.hallel.service;

import br.api.hallel.model.Financeiro;
import br.api.hallel.model.GastoFinanceiro;
import br.api.hallel.model.ReceitaFinanceira;
import br.api.hallel.repository.FinanceiroRepository;
import br.api.hallel.service.interfaces.FinanceiroInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @Override
    public Financeiro getFinanceiro() {
        return repository.findById("63e28aa8543a7bca82109218").get() != null ?
                repository.findById("63e28aa8543a7bca82109218").get() :
                null;
    }

    @Override
    public void salvarGasto(GastoFinanceiro gastoFinanceiro) {
        Financeiro financeiro = getFinanceiro();
        Financeiro valor = getFinanceiro();

        if (financeiro.getGastos() != null) {
            financeiro.getGastos().add(gastoFinanceiro);
            valor.getValorGastos().add(gastoFinanceiro.getValor());
        } else {
            ArrayList<GastoFinanceiro> gastoArray = new ArrayList<>();
            ArrayList<Double> valorArray = new ArrayList<>();

            gastoArray.add(gastoFinanceiro);
            valorArray.add(gastoFinanceiro.getValor());

            financeiro.setGastos(gastoArray);
            valor.setValorGastos(valorArray);
        }
        this.repository.save(financeiro);
    }


    @Override
    public void salvarReceita(ReceitaFinanceira receitaFinanceira) {
        Financeiro financeiro = getFinanceiro();
        Financeiro valor = getFinanceiro();

        if (financeiro.getGastos() != null) {
            financeiro.getReceita().add(receitaFinanceira);
            valor.getValorReceitas().add(receitaFinanceira.getValor());
        } else {

            ArrayList<ReceitaFinanceira> receitaArray = new ArrayList<>();
            ArrayList<Double> valorArray = new ArrayList<>();

            receitaArray.add(receitaFinanceira);
            valorArray.add(receitaFinanceira.getValor());

            financeiro.setReceita(receitaArray);
            valor.setValorReceitas(valorArray);

        }
        this.repository.save(financeiro);
    }

    @Override
    public List<Financeiro> getReceitas() {
        return this.repository.findAll();
    }

    @Override
    public List<Financeiro> getGastos() {
        return this.repository.findAll();
    }

    @Override
    public Double lucro() {
        Financeiro financeiro = getFinanceiro();
        Double lucro = (financeiro.getReceitaProvisoria() - financeiro.getGastoProvisorio());
        return lucro;
    }


    @Override
    public void deleteFinanceiro(String id) {
        this.repository.deleteById(id);
    }
}
