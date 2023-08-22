package br.api.hallel.moduloAPI.financeiroNovo.service;

import br.api.hallel.moduloAPI.financeiroNovo.model.SaidaFinanceiro;
import br.api.hallel.moduloAPI.financeiroNovo.payload.request.SaidaFinanceiroRequest;
import br.api.hallel.moduloAPI.financeiroNovo.payload.response.SaidaFinanceiroResponse;
import br.api.hallel.moduloAPI.financeiroNovo.repository.SaidaFinanceiroRepository;
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
public class SaidaFinanceiroService implements MetodosCRUDFinanceiro<SaidaFinanceiro, SaidaFinanceiroRequest, SaidaFinanceiroResponse> {

    @Autowired
    private SaidaFinanceiroRepository saidaFinanceiroRepository;

    @Override
    public SaidaFinanceiro cadastrar(SaidaFinanceiroRequest request) {
        SaidaFinanceiro saidaFinanceiro = this.saidaFinanceiroRepository.insert(request.toSaidaFinanceiro());
        log.info("Saida financeiro (id: " + saidaFinanceiro.getId() + ") adicionada com sucesso");
        return saidaFinanceiro;
    }

    @Override
    public Boolean editar(String id, SaidaFinanceiroRequest request) {
        Optional<SaidaFinanceiro> optional = this.saidaFinanceiroRepository.findById(id);
        if (optional.isPresent()) {
            SaidaFinanceiro saidaFinanceiroOld = optional.get();
            saidaFinanceiroOld.setCodigo(request.getCodigo());
            saidaFinanceiroOld.setData(request.getData());
            saidaFinanceiroOld.setMetodoPagamento(request.getMetodoPagamento());
            saidaFinanceiroOld.setValor(request.getValor());
            saidaFinanceiroRepository.save(saidaFinanceiroOld);
            log.info("Saida Financeiro (id: " + id + ") alterado com sucesso");
            return true;
        } else {
            log.info("Saida Financeiro (id: " + id + ") error na hora de alterar, não encontrado no sistema");
            return false;
        }
    }

    @Override
    public Boolean deletar(String id) {
        this.saidaFinanceiroRepository.deleteById(id);
        log.info("Entrada financeiro (id: " + id + ") deletada com sucesso");
        return true;
    }

    @Override
    public List<SaidaFinanceiroResponse> listarAll() {
        List<SaidaFinanceiroResponse> responseList = new ArrayList<>();

        for (SaidaFinanceiro saida : this.saidaFinanceiroRepository.findAll()) {
            responseList.add(new SaidaFinanceiroResponse().toDespesaResponseList(saida));
        }
        return responseList;
    }

    @Override
    public List<SaidaFinanceiroResponse> listByPage(int pagina) {
        Pageable pageable = PageRequest.of(pagina,15);
        List<SaidaFinanceiroResponse> responseList = new ArrayList<>();
        for (SaidaFinanceiro saida : this.saidaFinanceiroRepository.findAll()) {
            responseList.add(new SaidaFinanceiroResponse().toDespesaResponseList(saida));
        }
        return responseList;
    }

    @Override
    public SaidaFinanceiroResponse listarPorId(String id) {
        Optional<SaidaFinanceiro> optional = this.saidaFinanceiroRepository.findById(id);
        return new SaidaFinanceiroResponse().toDespesaResponseList(optional.orElse(null));
    }
}
