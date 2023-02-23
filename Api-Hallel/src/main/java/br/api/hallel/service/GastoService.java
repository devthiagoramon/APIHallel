package br.api.hallel.service;

import br.api.hallel.model.Financeiro;
import br.api.hallel.model.GastoFinanceiro;
import br.api.hallel.model.ReceitaFinanceira;
import br.api.hallel.payload.requerimento.GastoReq;
import br.api.hallel.repository.FinanceiroRepository;
import br.api.hallel.repository.GastoFinanceiroRepository;
import br.api.hallel.service.interfaces.GastoInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GastoService implements GastoInterface {

    @Autowired
    GastoFinanceiroRepository repository;
    @Autowired
    FinanceiroService service;

    @Override
    public GastoFinanceiro createGasto(GastoFinanceiro gastoFinanceiro) {

        service.salvarGasto(gastoFinanceiro);
        return this.repository.insert(gastoFinanceiro);
    }

    @Override
    public GastoFinanceiro listById(String id) {
        Optional<GastoFinanceiro> optional = this.repository.findById(id);

        if(optional.isPresent()){
            GastoFinanceiro gasto = optional.get();
            return gasto;
        }
        return null;
    }

    @Override
    public List<GastoFinanceiro> listAll() {
        return this.repository.findAll();
    }

    @Override
    public GastoFinanceiro update(String id, GastoReq gasto) {
        Optional<GastoFinanceiro> optional = this.repository.findById(id);

        if(optional.isPresent()){
            return this.repository.save(gasto.toGasto());
        }

        return null;
    }

    @Override
    public void deleteGasto(String id) {
        Optional<GastoFinanceiro> optional = this.repository.findById(id);

        if(optional.isPresent()){
            this.repository.deleteById(id);

        }else{
            System.out.println("Nada encontrado de id : "+id+" , foi...");

        }
    }
}
