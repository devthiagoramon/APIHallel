package br.api.hallel.moduloAPI.financeiroNovo.service;

import br.api.hallel.moduloAPI.financeiroNovo.model.DespesaEvento;
import br.api.hallel.moduloAPI.financeiroNovo.repository.DespesaEventoRepository;
import br.api.hallel.moduloAPI.financeiroNovo.request.DespesaEventoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DespesaEventoService implements MetodosCRUDFinanceiro<DespesaEvento, DespesaEventoRequest> {

    @Autowired
    private DespesaEventoRepository despesaEventoRepository;

    @Override
    public void cadastrar(DespesaEventoRequest request) {

    }

    @Override
    public void editar(String id, DespesaEventoRequest request) {

    }

    @Override
    public void deletar(String id) {

    }

    @Override
    public List<DespesaEvento> listarAll() {
        return null;
    }

    @Override
    public DespesaEvento listarPorId(String id) {
        return null;
    }
}
