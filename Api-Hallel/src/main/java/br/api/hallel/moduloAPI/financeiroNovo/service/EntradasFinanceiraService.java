package br.api.hallel.moduloAPI.financeiroNovo.service;

import br.api.hallel.moduloAPI.financeiroNovo.model.Doacoes;
import br.api.hallel.moduloAPI.financeiroNovo.model.EntradasFinanceiro;
import br.api.hallel.moduloAPI.financeiroNovo.model.PagamentosAssociado;
import br.api.hallel.moduloAPI.financeiroNovo.payload.request.EntradaFinanceiroRequest;
import br.api.hallel.moduloAPI.financeiroNovo.payload.response.EntradaFinanceiroResponse;
import br.api.hallel.moduloAPI.financeiroNovo.payload.response.EntradasResponseComparator;
import br.api.hallel.moduloAPI.financeiroNovo.repository.DoacoesRepository;
import br.api.hallel.moduloAPI.financeiroNovo.repository.EntradasFinanceiroRepository;
import br.api.hallel.moduloAPI.financeiroNovo.repository.PagamentoAssociadoRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class EntradasFinanceiraService implements MetodosCRUDFinanceiro<EntradasFinanceiro, EntradaFinanceiroRequest, EntradaFinanceiroResponse> {

    @Autowired
    private EntradasFinanceiroRepository entradasFinanceiroRepository;

    @Autowired
    private PagamentoAssociadoRepository pagamentoAssociadoRepository;

    @Autowired
    private DoacoesRepository doacoesRepository;

    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    public EntradasFinanceiro cadastrar(EntradaFinanceiroRequest request) {
        EntradasFinanceiro entradasFinanceiro = this.entradasFinanceiroRepository.insert(request.toEntradaFinanceiro());
        log.info("Entrada financeiro (id: " + entradasFinanceiro.getId() + ") adicionado com sucesso");
        return entradasFinanceiro;
    }

    public List<EntradasFinanceiro> cadastrarMultiValores(List<EntradaFinanceiroRequest> requests) {
        if (requests != null) {
            int i = 0;

            List<EntradasFinanceiro> financeiroList = new ArrayList<>();
            for (EntradaFinanceiroRequest request : requests) {
                financeiroList.add(request.toEntradaFinanceiro());
                i++;
            }
            log.info(i+" entradas foram cadastradas!");
            return this.entradasFinanceiroRepository.saveAll(financeiroList);
        }
        return null;
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

        for (PagamentosAssociado pagamentosAssociado : this.pagamentoAssociadoRepository.findAll()) {
            responseList.add(new EntradaFinanceiroResponse().toResponseList(pagamentosAssociado));
        }

        for (Doacoes doacoes : this.doacoesRepository.findAll()) {
            responseList.add(new EntradaFinanceiroResponse().toResponseList(doacoes));
        }

        return responseList;
    }

    @Override
    public List<EntradaFinanceiroResponse> listByPage(int pagina) {
        Pageable pageable = PageRequest.of(pagina, 5);

        List<EntradaFinanceiroResponse> responseList = new ArrayList<>();
        for (EntradasFinanceiro financeiro : this.entradasFinanceiroRepository.findAll(pageable)) {
            responseList.add(new EntradaFinanceiroResponse().toResponseList(financeiro));
        }
        for (PagamentosAssociado pagamentosAssociado : this.pagamentoAssociadoRepository.findAll(pageable)) {
            responseList.add(new EntradaFinanceiroResponse().toResponseList(pagamentosAssociado));
        }

        for (Doacoes doacoes : this.doacoesRepository.findAll(pageable)) {
            responseList.add(new EntradaFinanceiroResponse().toResponseList(doacoes));
        }

        return responseList;
    }

    @Override
    public EntradaFinanceiroResponse listarPorId(String id) {
        Optional<EntradasFinanceiro> optional = this.entradasFinanceiroRepository.findById(id);
        return new EntradaFinanceiroResponse().toResponseList(optional.orElse(null));
    }

    public List<EntradaFinanceiroResponse> listUltimasEntradas() {
        Pageable pageable = PageRequest.of(0, 5);

        List<EntradaFinanceiroResponse> responseList = new ArrayList<>();

        List<EntradasFinanceiro> entradas = new ArrayList<>();
        for (EntradasFinanceiro financeiro : this.entradasFinanceiroRepository.findAll(pageable)) {
            entradas.add(financeiro);
        }
        for (PagamentosAssociado pagamentosAssociado : this.pagamentoAssociadoRepository.findAll(pageable)) {
            entradas.add(pagamentosAssociado);
        }

        for (Doacoes doacoes : this.doacoesRepository.findAll(pageable)) {
            entradas.add(doacoes);
        }

        Collections.sort(entradas);


        for (int i = 0; i < 5; i++) {
            EntradasFinanceiro entrada = entradas.get(i);
            responseList.add(new EntradaFinanceiroResponse().toResponseList(entrada));
        }

        return responseList;
    }

    public List<EntradaFinanceiroResponse> listEntradasByMesAndAno(int numPage, String mes, String ano) {
        Pageable pageable = PageRequest.of(numPage, 15);

        List<EntradaFinanceiroResponse> responseList = new ArrayList<>();
        List<EntradasFinanceiro> entradasFinanceiros = new ArrayList<>();
        int indexTotal = pageable.getPageNumber() * pageable.getPageSize();

        for (EntradasFinanceiro entradasFinanceiro : this.entradasFinanceiroRepository.findAll()) {
            if (sdf.format(entradasFinanceiro.getDate()).substring(3).equals(mes + "/" + ano)) {
                entradasFinanceiros.add(entradasFinanceiro);
            }
        }

        for (PagamentosAssociado pagamentosAssociado : this.pagamentoAssociadoRepository.findAll()) {
            if (sdf.format(pagamentosAssociado.getDate()).substring(3).equals(mes + "/" + ano)) {
                entradasFinanceiros.add(pagamentosAssociado);
            }
        }

        for (Doacoes doacoes : this.doacoesRepository.findAll()) {
            if (sdf.format(doacoes.getDate()).substring(3).equals(mes + "/" + ano)) {
                entradasFinanceiros.add(doacoes);
            }
        }

        if (indexTotal == 0) {
            indexTotal = 15;
        }

        int indexInicial = indexTotal - 15;

        if (entradasFinanceiros.size() > indexTotal) {
            for (int i = indexInicial; i < indexTotal; i++) {
                EntradasFinanceiro entradaOBJ = entradasFinanceiros.get(i);
                if (entradaOBJ != null) {
                    responseList.add(new EntradaFinanceiroResponse().toResponseList(entradaOBJ));
                }
            }
        }else{
            for (EntradasFinanceiro entradasFinanceiro : entradasFinanceiros) {
                responseList.add(new EntradaFinanceiroResponse().toResponseList(entradasFinanceiro));
            }
        }

        Collections.sort(responseList, new EntradasResponseComparator());

        return responseList;
}


    // Por mês
    public Integer getTotalPages(String mes, String ano) {
        int numTotalEntradas = 0;
        int numTotalPagamentos = 0;
        int numTotalDoacoes = 0;

        int tamanhoPagina = 15;

        for (EntradasFinanceiro entradasFinanceiro : this.entradasFinanceiroRepository.findAll()) {
            if (sdf.format(entradasFinanceiro.getDate()).substring(3).equals(mes + "/" + ano)) {
                numTotalEntradas++;
            }
        }

        for (PagamentosAssociado pagamentosAssociado : this.pagamentoAssociadoRepository.findAll()) {
            if (sdf.format(pagamentosAssociado.getDate()).substring(3).equals(mes + "/" + ano)) {
                numTotalPagamentos++;
            }
        }

        for (Doacoes doacoes : this.doacoesRepository.findAll()) {
            if (sdf.format(doacoes.getDate()).substring(3).equals(mes + "/" + ano)) {
                numTotalDoacoes++;
            }
        }

        return (int) Math.ceil((double) (numTotalDoacoes + numTotalEntradas + numTotalPagamentos) / tamanhoPagina);
    }
}
