package br.api.hallel.service;

import br.api.hallel.model.Doacao;
import br.api.hallel.model.DoacaoObjeto;
import br.api.hallel.model.ReceitaFinanceira;
import br.api.hallel.payload.requerimento.DoacaoObjetoReq;
import br.api.hallel.payload.requerimento.DoacaoReq;
import br.api.hallel.payload.resposta.DoacoesDinheiroListaAdmResponse;
import br.api.hallel.payload.resposta.DoacoesObjetoListaAdmResponse;
import br.api.hallel.payload.resposta.ReceitasDiaAtualResponse;
import br.api.hallel.payload.resposta.ReceitasSemanaAtualResponse;
import br.api.hallel.repository.DoacaoObjetoRepository;
import br.api.hallel.repository.DoacaoRepository;
import br.api.hallel.service.interfaces.DoacaoInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.LoggerFactory;

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
    private ComunidadeService doacaoService;
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

    Logger logger = LoggerFactory.getLogger(DoacaoService.class);


    //ADICIONA OU ATUALIZA UMA DOAÇÃO
    @Override
    public Doacao doar(DoacaoReq doacaoReq) {
        logger.info("DOAÇÃO SALVA!");

        doacaoService.atualizarDoacao(doacaoReq.toDoacao());
        return repository.insert(doacaoReq.toDoacao());
    }

    @Override
    public List<DoacoesDinheiroListaAdmResponse> listAllDoacoes() {
        return new DoacoesDinheiroListaAdmResponse().toListDoacoesDinheiroListaAdm(this.repository.findAll());
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
        if(optional.isPresent()){
            DoacaoObjeto objeto = optional.get();
            return objeto;
        }
        return null;
    }

    public List<DoacoesDinheiroListaAdmResponse> listAllDoacaoDinheiroByThisDay() {

        List<DoacoesDinheiroListaAdmResponse> doacoesDia = new ArrayList<>();

        double valorTotal = 0;
        String diaAtualString = formatter.format(new Date());
        for (DoacoesDinheiroListaAdmResponse objeto :
                listAllDoacoes()) {
            if (objeto.getDataDoacao().equals(diaAtualString)) {
                doacoesDia.add(objeto);
            }
        }
        return doacoesDia;
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
            datasStrings.add(ldStart.toString());
            ldStart = ldStart.plusDays(1);
        }

        doacoesSemana = listAllDoacoes()
                .stream()
                .filter(item -> datasStrings.contains(item.getDataDoacao()))
                .collect(Collectors.toList());

        return doacoesSemana;
    }
}
