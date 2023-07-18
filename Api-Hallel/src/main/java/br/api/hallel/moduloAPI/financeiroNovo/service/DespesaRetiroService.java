package br.api.hallel.moduloAPI.financeiroNovo.service;

import br.api.hallel.moduloAPI.financeiroNovo.model.DespesaRetiro;
import br.api.hallel.moduloAPI.financeiroNovo.repository.DespesasRetiroRepository;
import br.api.hallel.moduloAPI.financeiroNovo.request.DespesaRetiroRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DespesaRetiroService implements MetodosCRUDFinanceiro<DespesaRetiro, DespesaRetiroRequest> {

    @Autowired
    private DespesasRetiroRepository despesasRetiroRepository;
    @Override
    public void cadastrar(DespesaRetiroRequest request) {

    }

    @Override
    public void editar(String id, DespesaRetiroRequest request) {

    }

    @Override
    public void deletar(String id) {

    }

    @Override
    public List<DespesaRetiro> listarAll() {
        return null;
    }

    @Override
    public DespesaRetiro listarPorId(String id) {
        return null;
    }
}
