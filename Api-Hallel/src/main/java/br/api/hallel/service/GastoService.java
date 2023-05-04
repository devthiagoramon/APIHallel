package br.api.hallel.service;

import br.api.hallel.model.Financeiro;
import br.api.hallel.model.GastoFinanceiro;
import br.api.hallel.model.ReceitaFinanceira;
import br.api.hallel.payload.requerimento.GastoReq;
import br.api.hallel.payload.resposta.DoacoesDinheiroListaAdmResponse;
import br.api.hallel.repository.FinanceiroRepository;
import br.api.hallel.repository.GastoFinanceiroRepository;
import br.api.hallel.service.interfaces.GastoInterface;
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
public class GastoService implements GastoInterface {

    @Autowired
    GastoFinanceiroRepository repository;
    @Autowired
    FinanceiroService service;
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

    //ADICONA GASTO À COMUNIDADE
    @Override
    public GastoFinanceiro createGasto(GastoFinanceiro gastoFinanceiro) {

        service.salvarGasto(gastoFinanceiro);
        return this.repository.insert(gastoFinanceiro);
    }

    //LISTA UMA DESPESA PELO ID
    @Override
    public GastoFinanceiro listById(String id) {
        Optional<GastoFinanceiro> optional = this.repository.findById(id);

        if(optional.isPresent()){
            GastoFinanceiro gasto = optional.get();
            return gasto;
        }
        return null;
    }

    //LISTA TODAS AS DESPESAS
    @Override
    public List<GastoFinanceiro> listAll() {
        return this.repository.findAll();
    }

    //ATUALIZA INFORMAÇÕES SOBRE UMA DESPESA
    @Override
    public GastoFinanceiro update(String id, GastoReq gasto) {
        Optional<GastoFinanceiro> optional = this.repository.findById(id);

        if(optional.isPresent()){
            return this.repository.save(gasto.toGasto());
        }

        return null;
    }

    //REMOVE UMA DESPESA
    @Override
    public void deleteGasto(String id) {
        Optional<GastoFinanceiro> optional = this.repository.findById(id);

        if(optional.isPresent()){
            GastoFinanceiro gasto = optional.get();

            Financeiro financeiro = service.getFinanceiro();


            financeiro.getValorGastos().remove(gasto.getValor());
            financeiro.getGastos().remove(gasto);


            this.service.update(financeiro);


            this.repository.deleteById(id);

        }else{
            System.out.println("Nada encontrado de id : "+id+" , foi...");

        }
    }

    public List<GastoFinanceiro> listAllByThisDay() {
        List<GastoFinanceiro> gastosDia = new ArrayList<>();
        String diaAtualString = formatter.format(new Date());
        for (GastoFinanceiro objeto :
                listAll()) {
            if (objeto.getDataGasto().equals(diaAtualString)) {
                gastosDia.add(objeto);
            }
        }
        return gastosDia;
    }

    public List<GastoFinanceiro> listAllByThisWeek() {
        List<GastoFinanceiro> gastosFinaneirosWeek = new ArrayList<>();

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

        gastosFinaneirosWeek = listAll()
                .stream()
                .filter(item -> datasStrings.contains(item.getDataGasto()))
                .collect(Collectors.toList());

        return gastosFinaneirosWeek;
    }
}
