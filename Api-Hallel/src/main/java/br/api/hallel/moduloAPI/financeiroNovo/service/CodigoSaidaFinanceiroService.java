package br.api.hallel.moduloAPI.financeiroNovo.service;

import br.api.hallel.moduloAPI.financeiroNovo.model.CodigoSaidaFinanceiro;
import br.api.hallel.moduloAPI.financeiroNovo.payload.request.CodigoSaidaFinanceiroRequest;
import br.api.hallel.moduloAPI.financeiroNovo.payload.response.CodigoSaidaFinanceiroResponse;
import br.api.hallel.moduloAPI.financeiroNovo.repository.CodigoSaidaFinanceiroRepository;
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
public class CodigoSaidaFinanceiroService implements MetodosCRUDFinanceiro<CodigoSaidaFinanceiro, CodigoSaidaFinanceiroRequest, CodigoSaidaFinanceiroResponse> {

    @Autowired
    private CodigoSaidaFinanceiroRepository codigoSaidaFinanceiroRepository;

    @Override
    public CodigoSaidaFinanceiro cadastrar(CodigoSaidaFinanceiroRequest request) {
        CodigoSaidaFinanceiro codigoSaida = this.codigoSaidaFinanceiroRepository.insert(request.toCodigoSaidaFinanceiro());
        log.info("Cadastrando codigo de saida financeiro " + codigoSaida.getId());
        return codigoSaida;
    }

    @Override
    public Boolean editar(String id, CodigoSaidaFinanceiroRequest request) {
        Optional<CodigoSaidaFinanceiro> optional = this.codigoSaidaFinanceiroRepository.findById(id);
        if (optional.isPresent()) {
            CodigoSaidaFinanceiro codigoSaidaFinanceiroOld = optional.get();
            codigoSaidaFinanceiroOld.setNomeCodigo(request.getNomeCodigo());
            codigoSaidaFinanceiroOld.setNumeroCodigo(request.getNumeroCodigo());
            this.codigoSaidaFinanceiroRepository.save(codigoSaidaFinanceiroOld);
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
    public List<CodigoSaidaFinanceiroResponse> listarAll() {
        List<CodigoSaidaFinanceiroResponse> responseList = new ArrayList<>();

        for (CodigoSaidaFinanceiro codigoSaidaFinanceiro : this.codigoSaidaFinanceiroRepository.findAll()) {
            responseList.add(new CodigoSaidaFinanceiroResponse().toResponseList(codigoSaidaFinanceiro));
        }

        return responseList;
    }

    @Override
    public List<CodigoSaidaFinanceiroResponse> listByPage(int pagina) {
        Pageable pageable = PageRequest.of(pagina, 15);

        List<CodigoSaidaFinanceiroResponse> responseList = new ArrayList<>();
        for (CodigoSaidaFinanceiro codigoSaidaFinanceiro : this.codigoSaidaFinanceiroRepository.findAll(pageable)) {
            responseList.add(new CodigoSaidaFinanceiroResponse().toResponseList(codigoSaidaFinanceiro));
        }

        return responseList;
    }

    @Override
    public CodigoSaidaFinanceiroResponse listarPorId(String id) {
        Optional<CodigoSaidaFinanceiro> optional = this.codigoSaidaFinanceiroRepository.findById(id);
        return new CodigoSaidaFinanceiroResponse().toResponseList(optional.orElse(null));
    }
}
