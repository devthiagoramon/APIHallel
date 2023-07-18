package br.api.hallel.moduloAPI.financeiroNovo.service;

import br.api.hallel.moduloAPI.financeiroNovo.model.CodigoEntradaFinanceiro;
import br.api.hallel.moduloAPI.financeiroNovo.repository.CodigoEntradaFinanceiroRepository;
import br.api.hallel.moduloAPI.financeiroNovo.request.CodigoEntradaFinanceiroRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CodigoEntradaFinanceiroService implements MetodosCRUDFinanceiro<CodigoEntradaFinanceiro, CodigoEntradaFinanceiroRequest> {

    @Autowired
    private CodigoEntradaFinanceiroRepository codigoEntradaFinanceiroRepository;

    @Override
    public void cadastrar(CodigoEntradaFinanceiroRequest request) {

    }

    @Override
    public void editar(String id, CodigoEntradaFinanceiroRequest request) {

    }

    @Override
    public void deletar(String id) {

    }

    @Override
    public List<CodigoEntradaFinanceiro> listarAll() {
        return null;
    }

    @Override
    public CodigoEntradaFinanceiro listarPorId(String id) {
        return null;
    }
}
