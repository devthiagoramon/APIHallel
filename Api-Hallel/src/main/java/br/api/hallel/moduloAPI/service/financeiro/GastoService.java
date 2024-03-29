package br.api.hallel.moduloAPI.service.financeiro;

import br.api.hallel.moduloAPI.model.Financeiro;
import br.api.hallel.moduloAPI.model.GastoFinanceiro;
import br.api.hallel.moduloAPI.model.SaidaFinanceiraResponseUltimas;
import br.api.hallel.moduloAPI.payload.requerimento.GastoReq;
import br.api.hallel.moduloAPI.repository.GastoFinanceiroRepository;
import br.api.hallel.moduloAPI.service.interfaces.GastoInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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
    FinanceiroService financeiroService;

    @Autowired
    CodigoSaidaService codigoSaidaService;

    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

    //ADICONA GASTO À COMUNIDADE
    @Override
    public GastoFinanceiro createGasto(GastoFinanceiro gastoFinanceiro) {

        financeiroService.salvarGasto(gastoFinanceiro);

        return this.repository.insert(gastoFinanceiro);
    }

    //LISTA UMA DESPESA PELO ID
    @Override
    public GastoFinanceiro listById(String id) {
        Optional<GastoFinanceiro> optional = this.repository.findById(id);

        if (optional.isPresent()) {
            GastoFinanceiro gasto = optional.get();
            return gasto;
        }
        return null;
    }

    //LISTA TODAS AS DESPESAS
    @Override
    public List<GastoFinanceiro> listAll(String mes, String ano) {
        List<GastoFinanceiro> listaGastosBD = this.repository.findAll(Sort.by(Sort.Direction.DESC, "id"));

        List<GastoFinanceiro> listaGastoFinal = listaGastosBD
                .stream()
                .filter(gastoFinanceiro ->
                        gastoFinanceiro.getDataGasto().substring(3).equals(mes + "/" + ano))
                .toList();

        return listaGastoFinal;
    }

    //ATUALIZA INFORMAÇÕES SOBRE UMA DESPESA
    @Override
    public GastoFinanceiro update(String id, GastoReq gasto) {
        Optional<GastoFinanceiro> optional = this.repository.findById(id);

        if (optional.isPresent()) {
            return this.repository.save(gasto.toGasto());
        }

        return null;
    }

    //REMOVE UMA DESPESA
    @Override
    public void deleteGasto(String id) {
        Optional<GastoFinanceiro> optional = this.repository.findById(id);

        if (optional.isPresent()) {
            GastoFinanceiro gasto = optional.get();

            Financeiro financeiro = financeiroService.getFinanceiro();


            financeiro.getValorGastos().remove(gasto.getValor());
            financeiro.getGastos().remove(gasto);


            this.financeiroService.update(financeiro);


            this.repository.deleteById(id);

        } else {
            System.out.println("Nada encontrado de id : " + id + " , foi...");

        }
    }

    @Override
    public List<SaidaFinanceiraResponseUltimas> listUltimasSaidas() {

        List<SaidaFinanceiraResponseUltimas> ultimasSaidas = new ArrayList<>();
        List<GastoFinanceiro> gastos = this.repository.findAll(Sort.by(Sort.Direction.DESC, "id"));

        int index = 0;

        if (gastos.size() != 0) {
            while (index < gastos.size()) {
                ultimasSaidas.add(new SaidaFinanceiraResponseUltimas(
                        gastos.get(index).getDescricaoGasto(),
                        gastos.get(index).getValor()));
                index++;
            }
        }
        return ultimasSaidas;
    }

    public List<GastoFinanceiro> listAllByThisDay() {
        List<GastoFinanceiro> gastosDia = new ArrayList<>();
        String diaAtualString = formatter.format(new Date());
        for (GastoFinanceiro objeto :
                this.repository.findAll()) {
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

        gastosFinaneirosWeek = this.repository.findAll()
                .stream()
                .filter(item -> datasStrings.contains(item.getDataGasto()))
                .collect(Collectors.toList());

        return gastosFinaneirosWeek;
    }
}
