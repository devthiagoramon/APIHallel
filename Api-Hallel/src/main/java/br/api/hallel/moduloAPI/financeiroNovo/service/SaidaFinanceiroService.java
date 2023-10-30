package br.api.hallel.moduloAPI.financeiroNovo.service;

import br.api.hallel.moduloAPI.financeiroNovo.model.DespesaEvento;
import br.api.hallel.moduloAPI.financeiroNovo.model.DespesaRetiro;
import br.api.hallel.moduloAPI.financeiroNovo.model.SaidaFinanceiro;
import br.api.hallel.moduloAPI.financeiroNovo.payload.request.SaidaFinanceiroRequest;
import br.api.hallel.moduloAPI.financeiroNovo.payload.response.SaidaFinanceiroResponse;
import br.api.hallel.moduloAPI.financeiroNovo.payload.response.SaidaFinanceiroResponseComparator;
import br.api.hallel.moduloAPI.financeiroNovo.repository.DespesaEventoRepository;
import br.api.hallel.moduloAPI.financeiroNovo.repository.DespesasRetiroRepository;
import br.api.hallel.moduloAPI.financeiroNovo.repository.SaidaFinanceiroRepository;
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
public class SaidaFinanceiroService implements MetodosCRUDFinanceiro<SaidaFinanceiro, SaidaFinanceiroRequest, SaidaFinanceiroResponse> {

    @Autowired
    private SaidaFinanceiroRepository saidaFinanceiroRepository;
    @Autowired
    private DespesaEventoRepository despesaEventoRepository;
    @Autowired
    private DespesasRetiroRepository despesasRetiroRepository;

    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    public SaidaFinanceiro cadastrar(SaidaFinanceiroRequest request) {
        SaidaFinanceiro saidaFinanceiro = this.saidaFinanceiroRepository.insert(request.toSaidaFinanceiro());
        log.info("Saida financeiro (id: " + saidaFinanceiro.getId() + ") adicionada com sucesso");
        return saidaFinanceiro;
    }

    public List<SaidaFinanceiro> cadastrarMultiValores(List<SaidaFinanceiroRequest> requests){
        List<SaidaFinanceiro> saidaList = new ArrayList<>();
        int i = 0;
        if(requests != null){
            for (SaidaFinanceiroRequest request : requests) {
                saidaList.add(request.toSaidaFinanceiro());
                i++;
            }
            log.info(i+" saídas foram cadastradas!");
        }

        return saidaList.isEmpty() ? null : this.saidaFinanceiroRepository.saveAll(saidaList);
    }

    @Override
    public Boolean editar(String id, SaidaFinanceiroRequest request) {
        Optional<SaidaFinanceiro> optional = this.saidaFinanceiroRepository.findById(id);
        if (optional.isPresent()) {
            SaidaFinanceiro saidaFinanceiroOld = optional.get();
            saidaFinanceiroOld.setCodigo(request.getCodigo());
            saidaFinanceiroOld.setDate(request.getData());
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
        Pageable pageable = PageRequest.of(pagina,5);
        List<SaidaFinanceiroResponse> responseList = new ArrayList<>();
        for (SaidaFinanceiro saida : this.saidaFinanceiroRepository.findAll(pageable)) {
            responseList.add(new SaidaFinanceiroResponse().toDespesaResponseList(saida));
        }

        for (DespesaEvento despesaEvento : this.despesaEventoRepository.findAll(pageable)) {
            responseList.add(new SaidaFinanceiroResponse().toDespesaResponseList(despesaEvento));
        }

        for (DespesaRetiro despesaRetiro : this.despesasRetiroRepository.findAll(pageable)) {
            responseList.add(new SaidaFinanceiroResponse().toDespesaResponseList(despesaRetiro));
        }

        return responseList;
    }

    @Override
    public SaidaFinanceiroResponse listarPorId(String id) {
        Optional<SaidaFinanceiro> optional = this.saidaFinanceiroRepository.findById(id);
        return new SaidaFinanceiroResponse().toDespesaResponseList(optional.orElse(null));
    }


    public List<SaidaFinanceiroResponse> listarUltimasSaidas(){

        Pageable pageable = PageRequest.of(0,5);
        List<SaidaFinanceiroResponse> responseList = new ArrayList<>();

        List<SaidaFinanceiro> saidaFinanceiros = new ArrayList<>();

        for (SaidaFinanceiro saida : this.saidaFinanceiroRepository.findAll(pageable)) {
            saidaFinanceiros.add(saida);
        }

        for (DespesaEvento despesaEvento : this.despesaEventoRepository.findAll(pageable)) {
            saidaFinanceiros.add(despesaEvento);
        }

        for (DespesaRetiro despesaRetiro : this.despesasRetiroRepository.findAll(pageable)) {
            saidaFinanceiros.add(despesaRetiro);
        }

        for (SaidaFinanceiro saidaFinanceiro : saidaFinanceiros) {
            responseList.add(new SaidaFinanceiroResponse().toDespesaResponseList(saidaFinanceiro));
        }

        return responseList;
    }

    public Integer getTotalPages(String mes, String ano) {
        int numTotalSaidas = 0;
        int numTotalDespesaRetiro = 0;
        int numTotalDespesaEvento = 0;

        int tamanhoPagina = 15;

        for (SaidaFinanceiro saidaFinanceiro : this.saidaFinanceiroRepository.findAll()) {
            if (sdf.format(saidaFinanceiro.getDate()).substring(3).equals(mes + "/" + ano)) {
                numTotalSaidas++;
            }
        }

        for (DespesaRetiro despesaRetiro : this.despesasRetiroRepository.findAll()) {
            if (sdf.format(despesaRetiro.getDate()).substring(3).equals(mes + "/" + ano)) {
                numTotalDespesaRetiro++;
            }
        }

        for (DespesaEvento despesaEvento : this.despesaEventoRepository.findAll()) {
            if (sdf.format(despesaEvento.getDate()).substring(3).equals(mes + "/" + ano)) {
                numTotalDespesaEvento++;
            }
        }

        return (int) Math.ceil((double) (numTotalDespesaEvento + numTotalSaidas + numTotalDespesaRetiro) / tamanhoPagina);
    }

    public List<SaidaFinanceiroResponse> listEntradasByMesAndAno(int numPage, String mes, String ano) {

        Pageable pageable = PageRequest.of(numPage, 15);

        List<SaidaFinanceiroResponse> responseList = new ArrayList<>();
        List<SaidaFinanceiro> saidaFinanceiros = new ArrayList<>();
        int indexTotal = pageable.getPageNumber() * pageable.getPageSize();

        for (SaidaFinanceiro saidaFinanceiro : this.saidaFinanceiroRepository.findAll()) {
            if (sdf.format(saidaFinanceiro.getDate()).substring(3).equals(mes + "/" + ano)) {
                saidaFinanceiros.add(saidaFinanceiro);
            }
        }

        for (DespesaRetiro despesaRetiro : this.despesasRetiroRepository.findAll()) {
            if (sdf.format(despesaRetiro.getDate()).substring(3).equals(mes + "/" + ano)) {
                saidaFinanceiros.add(despesaRetiro);
            }
        }

        for (DespesaEvento despesaEvento : this.despesaEventoRepository.findAll()) {
            if (sdf.format(despesaEvento.getDate()).substring(3).equals(mes + "/" + ano)) {
                saidaFinanceiros.add(despesaEvento);
            }
        }
        if (indexTotal == 0) {
            indexTotal = 15;
        }

        int indexInicial = indexTotal - 15;

        if (saidaFinanceiros.size() > indexTotal) {
            for (int i = indexInicial; i < indexTotal; i++) {
                SaidaFinanceiro saidaFinanceiro = saidaFinanceiros.get(i);
                if (saidaFinanceiro != null) {
                    responseList.add(new SaidaFinanceiroResponse().toDespesaResponseList(saidaFinanceiro));
                }
            }
        }else{
            for (SaidaFinanceiro saidaFinanceiro : saidaFinanceiros) {
                responseList.add(new SaidaFinanceiroResponse().toDespesaResponseList(saidaFinanceiro));
            }
        }

        Collections.sort(responseList, new SaidaFinanceiroResponseComparator());

        return responseList;

    }
}
