package br.api.hallel.moduloAPI.financeiroNovo.service;

import br.api.hallel.moduloAPI.financeiroNovo.model.EntradasFinanceiro;
import br.api.hallel.moduloAPI.financeiroNovo.repository.EntradasFinanceiroRepository;
import br.api.hallel.moduloAPI.financeiroNovo.request.EntradaFinanceiroRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntradasFinanceiraService implements MetodosCRUDFinanceiro<EntradasFinanceiro, EntradaFinanceiroRequest> {

    @Autowired
    private EntradasFinanceiroRepository entradasFinanceiroRepository;
    @Override
    public void cadastrar(EntradaFinanceiroRequest request) {
    }

    @Override
    public void editar(String id, EntradaFinanceiroRequest request) {

    }

    @Override
    public void deletar(String id) {
    }

    @Override
    public List<EntradasFinanceiro> listarAll() {
        return null;
    }

    @Override
    public EntradasFinanceiro listarPorId(String id) {
        return null;
    }
}
