package br.api.hallel.moduloAPI.financeiroNovo.service;

import br.api.hallel.moduloAPI.financeiroNovo.model.Doacoes;
import br.api.hallel.moduloAPI.financeiroNovo.payload.request.DoacaoRequest;
import br.api.hallel.moduloAPI.financeiroNovo.payload.response.DoacoesResponse;
import br.api.hallel.moduloAPI.financeiroNovo.repository.DoacoesRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class DoacoesService implements MetodosCRUDFinanceiro<Doacoes, DoacaoRequest, DoacoesResponse> {

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
            doacaoOld.setDate(request.getData());
            doacaoOld.setMetodoPagamento(request.getMetodoPagamento());
            doacaoOld.setValor(request.getValor());
            doacaoOld.setUsuarioDoador(request.getUsuarioDoador());
            this.doacoesRepository.save(doacaoOld);
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
    public List<DoacoesResponse> listarAll() {
        List<DoacoesResponse> responseList = new ArrayList<>();
        for (Doacoes doacoes : this.doacoesRepository.findAll()) {
            responseList.add(new DoacoesResponse().toDoacaoResponseList(doacoes));
        }
        return responseList;
    }

    @Override
    public List<DoacoesResponse> listByPage(int pagina) {
        Pageable pageable = PageRequest.of(pagina,15);
        List<DoacoesResponse> responseList = new ArrayList<>();
        for (Doacoes doacoes : this.doacoesRepository.findAll(pageable)) {
            responseList.add(new DoacoesResponse().toDoacaoResponseList(doacoes));
        }
        return responseList;
    }

    @Override
    public DoacoesResponse listarPorId(String id) {
        Optional<Doacoes> optional = this.doacoesRepository.findById(id);
        return new DoacoesResponse().toDoacaoResponseList(optional.orElse(null));
    }
}
