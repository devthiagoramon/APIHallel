package br.api.hallel.moduloAPI.financeiroNovo.service;

import br.api.hallel.moduloAPI.financeiroNovo.model.CodigoEntradaFinanceiro;
import br.api.hallel.moduloAPI.financeiroNovo.payload.request.CodigoEntradaFinanceiroRequest;
import br.api.hallel.moduloAPI.financeiroNovo.payload.response.CodigoEntradaFinanceiroResponse;
import br.api.hallel.moduloAPI.financeiroNovo.repository.CodigoEntradaFinanceiroRepository;
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
public class CodigoEntradaFinanceiroService implements MetodosCRUDFinanceiro<CodigoEntradaFinanceiro, CodigoEntradaFinanceiroRequest, CodigoEntradaFinanceiroResponse> {

    @Autowired
    private CodigoEntradaFinanceiroRepository codigoEntradaFinanceiroRepository;

    @Override
    public Boolean cadastrar(CodigoEntradaFinanceiroRequest request) {
        CodigoEntradaFinanceiro codigoEntrada = this.codigoEntradaFinanceiroRepository.insert(request.toCodigoSaidaFinanceiro());
        log.info("Cadastrando codigo de entrada financeiro " + codigoEntrada.getId());
        return true;
    }

    @Override
    public Boolean editar(String id, CodigoEntradaFinanceiroRequest request) {
        Optional<CodigoEntradaFinanceiro> optional = this.codigoEntradaFinanceiroRepository.findById(id);
        if (optional.isPresent()) {
            CodigoEntradaFinanceiro codigoEntradaFinanceiroOld = optional.get();
            codigoEntradaFinanceiroOld.setNomeCodigo(request.getNomeCodigo());
            codigoEntradaFinanceiroOld.setNumeroCodigo(request.getNumeroCodigo());
            this.codigoEntradaFinanceiroRepository.save(codigoEntradaFinanceiroOld);
            log.info("Codigo entrada (id: " + id + ") alterado com sucesso");
            return true;
        } else {
            log.info("Codigo entrada (id: " + id + ") error na hora de alterar, não encontrado no sistema");
            return false;
        }
    }

    @Override
    public Boolean deletar(String id) {
        this.codigoEntradaFinanceiroRepository.deleteById(id);
        log.info("Codigo entrada (id: " + id + ") deletado");
        return true;
    }

    @Override
    public List<CodigoEntradaFinanceiroResponse> listarAll() {
        List<CodigoEntradaFinanceiroResponse> responseList = new ArrayList<>();
        for (CodigoEntradaFinanceiro codigoEntradaFinanceiro : this.codigoEntradaFinanceiroRepository.findAll()) {
            responseList.add(new CodigoEntradaFinanceiroResponse().toResponseList(codigoEntradaFinanceiro));
        }
        return responseList;
    }

    @Override
    public List<CodigoEntradaFinanceiroResponse> listByPage(int pagina) {
        Pageable pageable = PageRequest.of(pagina,15);

        List<CodigoEntradaFinanceiroResponse> responseList = new ArrayList<>();
        for (CodigoEntradaFinanceiro codigoEntradaFinanceiro : this.codigoEntradaFinanceiroRepository.findAll(pageable)) {
            responseList.add(new CodigoEntradaFinanceiroResponse().toResponseList(codigoEntradaFinanceiro));
        }


        return null;
    }

    @Override
    public CodigoEntradaFinanceiroResponse listarPorId(String id) {
        Optional<CodigoEntradaFinanceiro> optional = this.codigoEntradaFinanceiroRepository.findById(id);
        return new CodigoEntradaFinanceiroResponse().toResponseList(optional.orElse(null));
    }
}
