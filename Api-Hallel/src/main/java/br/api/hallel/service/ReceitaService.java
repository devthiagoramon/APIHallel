package br.api.hallel.service;

import br.api.hallel.model.Financeiro;
import br.api.hallel.model.ReceitaFinanceira;
import br.api.hallel.payload.requerimento.ReceitaReq;
import br.api.hallel.payload.resposta.ReceitasDiaAtualResponse;
import br.api.hallel.payload.resposta.ReceitasSemanaAtualResponse;
import br.api.hallel.repository.ReceitaFinanceiraRepository;
import br.api.hallel.service.interfaces.ReceitaInterface;
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

@Service
public class ReceitaService implements ReceitaInterface {

    @Autowired
    ReceitaFinanceiraRepository repository;
    @Autowired
    FinanceiroService service;

    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

    //CRUD DE RECEITAS FINANCEIRO DA COMUNIDADE
    @Override
    public ReceitaFinanceira createReceita(ReceitaFinanceira receitaFinanceira) {

        //SALVA A RECEITA NUM ARRAY DE FINANCEIRO
        this.service.salvarReceita(receitaFinanceira);

        //SALVA A RECEITA NA TABELA 'RECEITA' DO BD
        return this.repository.insert(receitaFinanceira);
    }

    @Override
    public ReceitaFinanceira listById(String id) {
        Optional<ReceitaFinanceira> optional = this.repository.findById(id);

        if (optional.isPresent()) {
            ReceitaFinanceira receita = optional.get();
            return receita;
        }

        return null;
    }

    @Override
    public List<ReceitaFinanceira> listAll() {
        return this.repository.findAll(Sort.by(Sort.Direction.DESC, "dataReceita"));
    }

    @Override
    public ReceitaFinanceira update(String id, ReceitaReq receita) {

        Optional<ReceitaFinanceira> optional = this.repository.findById(id);

        if (optional.isPresent()) {

            return this.repository.save(receita.toGasto());
        }

        return null;
    }

    @Override
    public void deleteReceita(String id) {
        Optional<ReceitaFinanceira> optional = this.repository.findById(id);

        if (optional.isPresent()) {
            ReceitaFinanceira receita = optional.get();

            Financeiro financeiro = this.service.getFinanceiro();
            financeiro.getValorReceitas().remove(receita.getValor());

            this.service.update(financeiro);

            this.repository.deleteById(id);
        } else {

            System.out.println("Não existe nenhum usuário de id :" + id);
        }
    }

    @Override
    public List<ReceitaFinanceira> listUltimasReceitas() {
        List<ReceitaFinanceira> ultimasReceitas = new ArrayList<>();
        List<ReceitaFinanceira> receitas = listAll();

        for (int i = 0; i < 5; i++) {
            ultimasReceitas.add(receitas.get(i));
        }

        return ultimasReceitas;
    }

    @Override
    public ReceitasDiaAtualResponse listAllByThisDay() {
        double valorTotal = 0;
        String diaAtualString = formatter.format(new Date());
        for (ReceitaFinanceira receitaFinanceira :
                listAll()) {
            if (receitaFinanceira.getDataReceita().equals(diaAtualString)) {
                valorTotal += receitaFinanceira.getValor();
            }
        }

        Calendar cal = Calendar.getInstance();
        int diaAtual = cal.get(Calendar.DAY_OF_MONTH);
        return new ReceitasDiaAtualResponse(diaAtualString.substring(0, 5), valorTotal);
    }

    @Override
    public ReceitasSemanaAtualResponse listAllByThisWeek() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YYYY");

        ZoneId TZ = ZoneId.of("America/Puerto_Rico");

        ArrayList<String> datasStrings = new ArrayList<>();

        Locale locale = new Locale("pt", "br");
        DayOfWeek firstDay = WeekFields.of(locale).getFirstDayOfWeek();
        LocalDate ldStart = LocalDate.now(TZ).with(TemporalAdjusters.previousOrSame(firstDay));
        DayOfWeek lastDay = DayOfWeek.of(((firstDay.getValue() + 5) % DayOfWeek.values().length) + 1);
        LocalDate ldEnd = LocalDate.now(TZ).with(TemporalAdjusters.nextOrSame(lastDay));

        List<LocalDate> totalDates = new ArrayList<>();

        while (!ldStart.isAfter(ldEnd)) {
            totalDates.add(ldStart);
            ldStart = ldStart.plusDays(1);
        }
        TreeMap<String, Double> mapaValores = new TreeMap<>();

        totalDates.forEach(datas -> {
            mapaValores.put(formatter.format(datas), 0.0);
        });


        int lastIndex = 0;
        for (ReceitaFinanceira receitaFinanceira :
                listAll()) {
            for (String data :
                    mapaValores.keySet()) {
                if (data.equals(receitaFinanceira.getDataReceita())) {
                    mapaValores.replace(data, receitaFinanceira.getValor());
                    if (lastIndex != 0) {
                        mapaValores.replace(data, receitaFinanceira.getValor()+listAll().get(lastIndex).getValor());
                    }
                    lastIndex = listAll().indexOf(receitaFinanceira);
                }
            }
        }

        ArrayList<Double> valores = new ArrayList<>();
        for (String data :
                mapaValores.keySet()) {
            valores.add(mapaValores.get(data));
        }
        for (String data :
                mapaValores.keySet()) {
            datasStrings.add(data.substring(0, 5));
        }

        return new ReceitasSemanaAtualResponse(datasStrings, valores);
    }
}
