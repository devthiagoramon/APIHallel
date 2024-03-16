package br.api.hallel.moduloAPI.financeiroNovo.service;

import br.api.hallel.moduloAPI.financeiroNovo.model.PagamentoEntradaEvento;
import br.api.hallel.moduloAPI.financeiroNovo.payload.request.PagamentoEntradaEventoReq;
import br.api.hallel.moduloAPI.financeiroNovo.payload.response.PagamentoEntradaEventoRes;
import br.api.hallel.moduloAPI.financeiroNovo.repository.EntradasFinanceiroRepository;
import br.api.hallel.moduloAPI.financeiroNovo.repository.PagamentoEntradaEventoRepository;
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
public class PagamentoEntradaEventoService implements
        MetodosCRUDFinanceiro<PagamentoEntradaEvento, PagamentoEntradaEventoReq, PagamentoEntradaEventoRes> {

    @Autowired
    private PagamentoEntradaEventoRepository pagamentoEntradaEventoRepository;
    @Autowired
    private EntradasFinanceiroRepository repository;

    @Override
    public PagamentoEntradaEvento cadastrar(PagamentoEntradaEventoReq request) {
        PagamentoEntradaEvento pagamentoEntradaEvento =
                this.pagamentoEntradaEventoRepository.insert(request.toPagamentoEntradaEvento());
        log.info("Pagamento de um evento(id: " + pagamentoEntradaEvento.getId() + ") salvo no sistema");

        this.repository.save(pagamentoEntradaEvento);
        return pagamentoEntradaEvento;
    }

    @Override
    public Boolean editar(String id, PagamentoEntradaEventoReq request) {
        Optional<PagamentoEntradaEvento> optional = this.pagamentoEntradaEventoRepository.findById(id);
        if (optional.isPresent()) {
            PagamentoEntradaEvento pagamentoEntradaEventoOld = optional.get();

            pagamentoEntradaEventoOld.setId(request.getId());
            pagamentoEntradaEventoOld.setCodigo(request.getCodigo());
            pagamentoEntradaEventoOld.setDate(request.getDate());
            pagamentoEntradaEventoOld.setValor(request.getValor());
            pagamentoEntradaEventoOld.setMetodoPagamento(request.getMetodoPagamento());
            this.pagamentoEntradaEventoRepository.save(pagamentoEntradaEventoOld);

            log.info("Pagamento de evento (id: " + id + ") alterado com sucesso");
            return true;
        } else {
            log.info("Pagamento de evento (id: " + id + ") error na hora de alterar, não encontrado no sistema");
            return false;
        }
    }

    @Override
    public Boolean deletar(String id) {
        this.pagamentoEntradaEventoRepository.deleteById(id);
        log.info("Pagamento assoociado (id: " + id + ") deletado com sucesso");
        return true;
    }

    @Override
    public List<PagamentoEntradaEventoRes> listarAll() {
        List<PagamentoEntradaEventoRes> responseList = new ArrayList<>();

        for (PagamentoEntradaEvento pagamentoEntradaEvento : this.pagamentoEntradaEventoRepository.findAll()) {
            responseList.add(new PagamentoEntradaEventoRes().toPagamentoEntradaEventoRes(pagamentoEntradaEvento));
        }

        return responseList;
    }

    @Override
    public List<PagamentoEntradaEventoRes> listByPage(int pagina) {
        List<PagamentoEntradaEventoRes> responseList = new ArrayList<>();
        Pageable pageable = PageRequest.of(pagina, 15);

        for (PagamentoEntradaEvento pagamentoEntradaEvento : this.pagamentoEntradaEventoRepository.findAll(pageable)) {
            responseList.add(new PagamentoEntradaEventoRes().toPagamentoEntradaEventoRes(pagamentoEntradaEvento));
        }

        return responseList;
    }

    @Override
    public PagamentoEntradaEventoRes listarPorId(String id) {
        Optional<PagamentoEntradaEvento> optional = this.pagamentoEntradaEventoRepository.findById(id);
        return new PagamentoEntradaEventoRes().toPagamentoEntradaEventoRes(optional.orElse(null));
    }
}
