package br.api.hallel.moduloAPI.financeiroNovo.service;

import br.api.hallel.moduloAPI.financeiroNovo.model.EntradasFinanceiro;
import br.api.hallel.moduloAPI.financeiroNovo.payload.request.EntradaFinanceiroRequest;
import br.api.hallel.moduloAPI.financeiroNovo.payload.response.EntradaFinanceiroResponse;
import br.api.hallel.moduloAPI.financeiroNovo.repository.EntradasFinanceiroRepository;
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
public class EntradasFinanceiraService implements MetodosCRUDFinanceiro<EntradasFinanceiro, EntradaFinanceiroRequest, EntradaFinanceiroResponse> {

    @Autowired
    private EntradasFinanceiroRepository entradasFinanceiroRepository;

    @Override
    public Boolean cadastrar(EntradaFinanceiroRequest request) {
        EntradasFinanceiro entradasFinanceiro = this.entradasFinanceiroRepository.insert(request.toEntradaFinanceiro());
        log.info("Entrada financeiro (id: " + entradasFinanceiro.getId() + ") adicionado com sucesso");
        return true;
    }

    @Override
    public Boolean editar(String id, EntradaFinanceiroRequest request) {
        Optional<EntradasFinanceiro> optional = this.entradasFinanceiroRepository.findById(id);
        if (optional.isPresent()) {
            EntradasFinanceiro entradasFinanceiroOld = optional.get();
            entradasFinanceiroOld.setCodigo(request.getCodigo());
            entradasFinanceiroOld.setDate(request.getData());
            entradasFinanceiroOld.setMetodoPagamento(request.getMetodoPagamento());
            entradasFinanceiroOld.setValor(request.getValor());
            this.entradasFinanceiroRepository.save(entradasFinanceiroOld);
            log.info("Entrada Financeiro (id: " + id + ") alterado com sucesso");
            return true;
        } else {
            log.info("Entrada Financeiro (id: " + id + ") error na hora de alterar, não encontrado no sistema");
            return false;
        }
    }

    @Override
    public Boolean deletar(String id) {
        this.entradasFinanceiroRepository.deleteById(id);
        log.info("Entrada financeiro (id: " + id + ") deletada com sucesso");
        return true;
    }

    @Override
    public List<EntradaFinanceiroResponse> listarAll() {
        List<EntradaFinanceiroResponse> responseList = new ArrayList<>();

        for (EntradasFinanceiro financeiro : this.entradasFinanceiroRepository.findAll()) {
            responseList.add(new EntradaFinanceiroResponse().toResponseList(financeiro));
        }
        return responseList;
    }

    @Override
    public List<EntradaFinanceiroResponse> listByPage(int pagina) {
        Pageable pageable = PageRequest.of(pagina, 15);

        List<EntradaFinanceiroResponse> responseList = new ArrayList<>();
        for (EntradasFinanceiro financeiro : this.entradasFinanceiroRepository.findAll(pageable)) {
            responseList.add(new EntradaFinanceiroResponse().toResponseList(financeiro));
        }
        return responseList;
    }

    @Override
    public EntradaFinanceiroResponse listarPorId(String id) {
        Optional<EntradasFinanceiro> optional = this.entradasFinanceiroRepository.findById(id);
        return new EntradaFinanceiroResponse().toResponseList(optional.orElse(null));
    }
}
