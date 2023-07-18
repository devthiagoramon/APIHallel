package br.api.hallel.moduloAPI.financeiroNovo.service;

import br.api.hallel.moduloAPI.financeiroNovo.model.PagamentosAssociado;
import br.api.hallel.moduloAPI.financeiroNovo.repository.PagamentoAssociadoRepository;
import br.api.hallel.moduloAPI.financeiroNovo.request.PagamentoAssociadoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PagamentoAssociadoService implements MetodosCRUDFinanceiro<PagamentosAssociado, PagamentoAssociadoRequest> {

    @Autowired
    private PagamentoAssociadoRepository pagamentoAssociadoRepository;

    @Override
    public void cadastrar(PagamentoAssociadoRequest request) {

    }

    @Override
    public void editar(String id, PagamentoAssociadoRequest request) {

    }

    @Override
    public void deletar(String id) {

    }

    @Override
    public List<PagamentosAssociado> listarAll() {
        return null;
    }

    @Override
    public PagamentosAssociado listarPorId(String id) {
        return null;
    }
}
