package br.api.hallel.moduloAPI.service.financeiro;

import br.api.hallel.moduloAPI.model.Doacao;
import br.api.hallel.moduloAPI.model.DoacaoObjeto;
import br.api.hallel.moduloAPI.payload.requerimento.DoacaoObjetoReq;
import br.api.hallel.moduloAPI.payload.requerimento.DoacaoReq;
import br.api.hallel.moduloAPI.payload.resposta.DoacoesDinheiroListaAdmResponse;
import br.api.hallel.moduloAPI.payload.resposta.DoacoesObjetoListaAdmResponse;
import br.api.hallel.moduloAPI.repository.DoacaoObjetoRepository;
import br.api.hallel.moduloAPI.repository.DoacaoRepository;
import br.api.hallel.moduloAPI.service.interfaces.DoacaoInterface;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.WeekFields;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DoacaoService implements DoacaoInterface {

    @Autowired
    private DoacaoRepository repository;

    @Autowired
    private DoacaoObjetoRepository repositoryObjeto;
    @Autowired
    private ComunidadeService comunidadeService;

    @Autowired
    private FinanceiroService financeiroService;
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

    Logger logger = LoggerFactory.getLogger(DoacaoService.class);


    //ADICIONA OU ATUALIZA UMA DOAÇÃO
    @Override
    public Doacao doar(DoacaoReq doacaoReq) {
        logger.info("DOAÇÃO SALVA!");
        comunidadeService.atualizarDoacao(doacaoReq.toDoacao());
        return repository.insert(doacaoReq.toDoacao());
    }

    @Override
    public List<DoacoesDinheiroListaAdmResponse> listAllDoacoes(String mes, String ano) {
        List<Doacao> listaBdDoacoes = this.repository.findAll();
        List<Doacao> listaGastoFinal = listaBdDoacoes
                .stream()
                .filter(doacao ->
                        doacao.getDataDoacao().substring(3).equals(mes + "/" + ano))
                .toList();
        return new DoacoesDinheiroListaAdmResponse().toListDoacoesDinheiroListaAdm(listaGastoFinal);
    }

    @Override
    public Doacao listDoacaoById(String id) {

        Optional<Doacao> optional = this.repository.findById(id);

        if (optional.isPresent()) {
            Doacao doacao = optional.get();


            logger.info("DOAÇÃO LISTADA COM SUCESSO");

            return doacao;

        } else {
            logger.warn("ID NÃO ENCONTRADO!");
            return null;
        }

    }

    @Override
    public List<DoacoesObjetoListaAdmResponse> listAllDoacoesObjeto() {
        return new DoacoesObjetoListaAdmResponse().toDoacoesObjLista(this.repositoryObjeto.findAll());
    }

    @Override
    public DoacaoObjeto doarObjeto(DoacaoObjetoReq doacaoObjeto) {
        return this.repositoryObjeto.insert(doacaoObjeto.toDoacaoObjeto());
    }

    @Override
    public DoacaoObjeto objetoRecebido(String id) {
        Date dataRecebida = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");


        Optional<DoacaoObjeto> optional = this.repositoryObjeto.findById(id);

        if (optional.isPresent()) {
            DoacaoObjeto objeto = optional.get();
            objeto.setDataRecebida(formatter.format(dataRecebida));
            objeto.setRecebido(true);
            return this.repositoryObjeto.save(objeto);
        }
        return null;

    }

    @Override
    public DoacaoObjeto objetoNaoRecebido(String id) {
        Optional<DoacaoObjeto> optional = this.repositoryObjeto.findById(id);

        if (optional.isPresent()) {
            DoacaoObjeto objeto = optional.get();
            objeto.setDataRecebida("");
            objeto.setRecebido(false);
            this.repositoryObjeto.save(objeto);
        }
        return null;
    }

    @Override
    public DoacaoObjeto listDoacaoObjetoById(String id) {
        Optional<DoacaoObjeto> optional = this.repositoryObjeto.findById(id);
        if (optional.isPresent()) {
            DoacaoObjeto objeto = optional.get();
            return objeto;
        }
        return null;
    }

    public List<DoacoesDinheiroListaAdmResponse> listAllDoacaoDinheiroByThisDay() {

        List<Doacao> doacoesDia = new ArrayList<>();
        String diaAtualString = formatter.format(new Date());
        for (Doacao objeto :
                this.repository.findAll()) {
            if (objeto.getDataDoacao().equals(diaAtualString)) {
                doacoesDia.add(objeto);
            }
        }
        return new DoacoesDinheiroListaAdmResponse().toListDoacoesDinheiroListaAdm(doacoesDia);
    }

    public List<DoacoesDinheiroListaAdmResponse> listAllDoacaoDinheiroByThisWeek() {

        List<DoacoesDinheiroListaAdmResponse> doacoesSemana = new ArrayList<>();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YYYY");

        ZoneId TZ = ZoneId.of("America/Puerto_Rico");

        ArrayList<String> datasStrings = new ArrayList<>();

        Locale locale = new Locale("pt", "br");
        DayOfWeek firstDay = WeekFields.of(locale).getFirstDayOfWeek();
        LocalDate ldStart = LocalDate.now(TZ).with(TemporalAdjusters.previousOrSame(firstDay));
        DayOfWeek lastDay = DayOfWeek.of(((firstDay.getValue() + 5) % DayOfWeek.values().length) + 1);
        LocalDate ldEnd = LocalDate.now(TZ).with(TemporalAdjusters.nextOrSame(lastDay));

        while (!ldStart.isAfter(ldEnd)) {
            datasStrings.add(formatter.format(ldStart));
            ldStart = ldStart.plusDays(1);
        }

        doacoesSemana = new DoacoesDinheiroListaAdmResponse().toListDoacoesDinheiroListaAdm(this.repository.findAll())
                .stream()
                .filter(item -> datasStrings.contains(item.getDataDoacao()))
                .collect(Collectors.toList());

        return doacoesSemana;
    }
}
