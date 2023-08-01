package br.api.hallel.moduloAPI.financeiroNovo.service;

import br.api.hallel.moduloAPI.financeiroNovo.model.EntradasFinanceiro;
import br.api.hallel.moduloAPI.financeiroNovo.payload.request.EntradaFinanceiroRequest;
import br.api.hallel.moduloAPI.financeiroNovo.repository.EntradasFinanceiroRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class EntradasFinanceiraService implements MetodosCRUDFinanceiro<EntradasFinanceiro, EntradaFinanceiroRequest> {

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
        if (optional.isPresent()){
            EntradasFinanceiro entradasFinanceiroOld = optional.get();
            entradasFinanceiroOld.setCodigo(request.getCodigo());
            entradasFinanceiroOld.setData(request.getData());
            entradasFinanceiroOld.setMetodoPagamento(request.getMetodoPagamento());
            entradasFinanceiroOld.setValor(request.getValor());
            log.info("Entrada Financeiro (id: " + id + ") alterado com sucesso");
            return true;
        }else{
            log.info("Entrada Financeiro (id: " + id + ") error na hora de alterar, não encontrado no sistema");
            return false;
        }
    }

    @Override
    public Boolean deletar(String id) {
        this.entradasFinanceiroRepository.deleteById(id);
        log.info("Entrada financeiro (id: "+id+") deletada com sucesso");
        return true;
    }

    @Override
    public List<EntradasFinanceiro> listarAll() {
        return this.entradasFinanceiroRepository.findAll();
    }

    @Override
    public EntradasFinanceiro listarPorId(String id) {
        Optional<EntradasFinanceiro> optional = this.entradasFinanceiroRepository.findById(id);
        return optional.orElse(null);
    }
}
