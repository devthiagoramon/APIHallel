package br.api.hallel.moduloAPI.financeiroNovo.service;

import br.api.hallel.moduloAPI.financeiroNovo.model.CodigoSaidaFinanceiro;
import br.api.hallel.moduloAPI.financeiroNovo.repository.CodigoSaidaFinanceiroRepository;
import br.api.hallel.moduloAPI.financeiroNovo.request.CodigoSaidaFinanceiroRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CodigoSaidaFinanceiroService implements MetodosCRUDFinanceiro<CodigoSaidaFinanceiro, CodigoSaidaFinanceiroRequest> {

    private CodigoSaidaFinanceiroRepository codigoSaidaFinanceiroRepository;
    @Override
    public void cadastrar(CodigoSaidaFinanceiroRequest request) {

    }

    @Override
    public void editar(String id, CodigoSaidaFinanceiroRequest request) {

    }

    @Override
    public void deletar(String id) {

    }

    @Override
    public List<CodigoSaidaFinanceiro> listarAll() {
        return null;
    }

    @Override
    public CodigoSaidaFinanceiro listarPorId(String id) {
        return null;
    }
}
