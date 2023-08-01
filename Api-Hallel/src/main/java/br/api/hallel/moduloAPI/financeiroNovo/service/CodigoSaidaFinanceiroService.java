package br.api.hallel.moduloAPI.financeiroNovo.service;

import br.api.hallel.moduloAPI.financeiroNovo.model.CodigoSaidaFinanceiro;
import br.api.hallel.moduloAPI.financeiroNovo.payload.request.CodigoSaidaFinanceiroRequest;
import br.api.hallel.moduloAPI.financeiroNovo.repository.CodigoSaidaFinanceiroRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class CodigoSaidaFinanceiroService implements MetodosCRUDFinanceiro<CodigoSaidaFinanceiro, CodigoSaidaFinanceiroRequest> {

    private CodigoSaidaFinanceiroRepository codigoSaidaFinanceiroRepository;

    @Override
    public Boolean cadastrar(CodigoSaidaFinanceiroRequest request) {
        CodigoSaidaFinanceiro codigoSaida = this.codigoSaidaFinanceiroRepository.insert(request.toCodigoSaidaFinanceiro());
        log.info("Cadastrando codigo de saida financeiro " + codigoSaida.getId());
        return true;
    }

    @Override
    public Boolean editar(String id, CodigoSaidaFinanceiroRequest request) {
        Optional<CodigoSaidaFinanceiro> optional = this.codigoSaidaFinanceiroRepository.findById(id);
        if (optional.isPresent()) {
            CodigoSaidaFinanceiro codigoSaidaFinanceiroOld = optional.get();
            codigoSaidaFinanceiroOld.setNomeCodigo(request.getNomeCodigo());
            codigoSaidaFinanceiroOld.setNumeroCodigo(request.getNumeroCodigo());
            log.info("Codigo Saida (id: " + id + ") alterado com sucesso");
            return true;
        } else {
            log.info("Codigo Saida (id: " + id + ") error na hora de alterar, não encontrado no sistema");
            return false;
        }
    }

    @Override
    public Boolean deletar(String id) {
        this.codigoSaidaFinanceiroRepository.deleteById(id);
        log.info("Codigo saida (id: " + id + ") deletado");
        return true;
    }

    @Override
    public List<CodigoSaidaFinanceiro> listarAll() {
        return this.codigoSaidaFinanceiroRepository.findAll();
    }

    @Override
    public CodigoSaidaFinanceiro listarPorId(String id) {
        Optional<CodigoSaidaFinanceiro> optional = this.codigoSaidaFinanceiroRepository.findById(id);
        return optional.orElse(null);
    }
}
