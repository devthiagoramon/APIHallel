package br.api.hallel.moduloAPI.financeiroNovo.service;

import br.api.hallel.moduloAPI.financeiroNovo.model.DespesaEvento;
import br.api.hallel.moduloAPI.financeiroNovo.payload.request.DespesaEventoRequest;
import br.api.hallel.moduloAPI.financeiroNovo.payload.response.DespesaEventoResponse;
import br.api.hallel.moduloAPI.financeiroNovo.repository.DespesaEventoRepository;
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
public class DespesaEventoService implements MetodosCRUDFinanceiro<DespesaEvento, DespesaEventoRequest, DespesaEventoResponse> {

    @Autowired
    private DespesaEventoRepository despesaEventoRepository;

    @Override
    public DespesaEvento cadastrar(DespesaEventoRequest request) {
        DespesaEvento despesaEvento = this.despesaEventoRepository.insert(request.toDespesaEvento());
        log.info("Despesa Evento (id: " + despesaEvento.getId() + ") adicionado com sucesso");
        return despesaEvento;
    }

    @Override
    public Boolean editar(String id, DespesaEventoRequest request) {
        Optional<DespesaEvento> optional = this.despesaEventoRepository.findById(id);
        if (optional.isPresent()) {
            DespesaEvento despesaEventoOld = optional.get();
            despesaEventoOld.setCodigo(request.getCodigo());
            despesaEventoOld.setDate(request.getData());
            despesaEventoOld.setValor(request.getValor());
            despesaEventoOld.setMetodoPagamento(request.getMetodoPagamento());
            this.despesaEventoRepository.save(despesaEventoOld);
            log.info("Despesa Evento (id: " + id + ") alterado com sucesso");
            return true;
        } else {
            log.info("Despesa Evento (id: " + id + ") error na hora de alterar, não encontrado no sistema");
            return false;
        }
    }

    @Override
    public Boolean deletar(String id) {
        this.despesaEventoRepository.deleteById(id);
        log.info("Despesa Evento (id: " + id + ") deletado com sucesso");
        return true;
    }

    @Override
    public List<DespesaEventoResponse> listarAll() {
        List<DespesaEventoResponse> responseList = new ArrayList<>();

        for (DespesaEvento despesaEvento : this.despesaEventoRepository.findAll()) {
            responseList.add(new DespesaEventoResponse().toDespesaResponseList(despesaEvento));
        }
        return responseList;
    }

    @Override
    public List<DespesaEventoResponse> listByPage(int pagina) {
        Pageable pageable = PageRequest.of(pagina,15);
        List<DespesaEventoResponse> responseList = new ArrayList<>();

        for (DespesaEvento despesaEvento : this.despesaEventoRepository.findAll(pageable)) {
            responseList.add(new DespesaEventoResponse().toDespesaResponseList(despesaEvento));
        }
        return responseList;
    }

    @Override
    public DespesaEventoResponse listarPorId(String id) {
        Optional<DespesaEvento> optional = this.despesaEventoRepository.findById(id);
        return new DespesaEventoResponse().toDespesaResponseList(optional.orElse(null));
    }
}
