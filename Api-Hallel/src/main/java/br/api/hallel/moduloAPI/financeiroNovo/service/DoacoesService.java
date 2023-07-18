package br.api.hallel.moduloAPI.financeiroNovo.service;

import br.api.hallel.moduloAPI.financeiroNovo.model.Doacoes;
import br.api.hallel.moduloAPI.financeiroNovo.repository.DoacoesRepository;
import br.api.hallel.moduloAPI.financeiroNovo.request.DoacaoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoacoesService implements MetodosCRUDFinanceiro<Doacoes, DoacaoRequest> {

    @Autowired
    private DoacoesRepository doacoesRepository;

    @Override
    public void cadastrar(DoacaoRequest request) {

    }

    @Override
    public void editar(String id, DoacaoRequest request) {

    }

    @Override
    public void deletar(String id) {

    }

    @Override
    public List<Doacoes> listarAll() {
        return null;
    }

    @Override
    public Doacoes listarPorId(String id) {
        return null;
    }
}
