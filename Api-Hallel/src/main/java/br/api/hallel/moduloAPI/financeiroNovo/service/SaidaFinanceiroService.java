package br.api.hallel.moduloAPI.financeiroNovo.service;

import br.api.hallel.moduloAPI.financeiroNovo.model.SaidaFinanceiro;
import br.api.hallel.moduloAPI.financeiroNovo.repository.SaidaFinanceiroRepository;
import br.api.hallel.moduloAPI.financeiroNovo.request.SaidaFinanceiroRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaidaFinanceiroService implements MetodosCRUDFinanceiro<SaidaFinanceiro, SaidaFinanceiroRequest> {

    @Autowired
    private SaidaFinanceiroRepository saidaFinanceiroRepository;

    @Override
    public Boolean cadastrar(SaidaFinanceiroRequest request) {
        return null;
    }

    @Override
    public Boolean editar(String id, SaidaFinanceiroRequest request) {
        return null;
    }

    @Override
    public Boolean deletar(String id) {
        return null;
    }

    @Override
    public List<SaidaFinanceiro> listarAll() {
        return null;
    }

    @Override
    public SaidaFinanceiro listarPorId(String id) {
        return null;
    }
}
