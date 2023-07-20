package br.api.hallel.moduloAPI.financeiroNovo.service;

import br.api.hallel.moduloAPI.financeiroNovo.model.Doacoes;
import br.api.hallel.moduloAPI.financeiroNovo.repository.DoacoesRepository;
import br.api.hallel.moduloAPI.financeiroNovo.request.DoacaoRequest;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class DoacoesService implements MetodosCRUDFinanceiro<Doacoes, DoacaoRequest> {

    @Autowired
    private DoacoesRepository doacoesRepository;

    @Override
    public Boolean cadastrar(DoacaoRequest request) {
        Doacoes doacao = this.doacoesRepository.insert(request.toDoacoes());
        log.info("Doação (id: " + doacao.getId() + ") salva com sucesso");
        return true;
    }

    @Override
    public Boolean editar(String id, DoacaoRequest request) {
        Optional<Doacoes> optional = this.doacoesRepository.findById(id);
        if (optional.isPresent()) {
            Doacoes doacaoOld = optional.get();
            doacaoOld.setCodigo(request.getCodigo());
            doacaoOld.setData(request.getData());
            doacaoOld.setMetodoPagamento(request.getMetodoPagamento());
            doacaoOld.setValor(request.getValor());
            doacaoOld.setUsuarioDoador(request.getUsuarioDoador());
            log.info("Doação (id: " + id + ") alterado com sucesso");
            return true;
        } else {
            log.info("Doação (id: " + id + ") error na hora de alterar, não encontrado no sistema");
            return false;
        }
    }

    @Override
    public Boolean deletar(String id) {
        this.doacoesRepository.deleteById(id);
        log.info("Doação (id: " + id + ") deletada com sucesso");
        return true;
    }

    @Override
    public List<Doacoes> listarAll() {
        return this.doacoesRepository.findAll();
    }

    @Override
    public Doacoes listarPorId(String id) {
        Optional<Doacoes> optional = this.doacoesRepository.findById(id);
        return optional.orElse(null);
    }
}
