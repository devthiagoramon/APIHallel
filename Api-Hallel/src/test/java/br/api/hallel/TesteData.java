package br.api.hallel;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.WeekFields;
import java.util.*;

public class TesteData {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YYYY");


    @Test
    void dataFormatadaString(){
    }

    @Test
    void semanhaAtual(){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YYYY");

        ZoneId TZ = ZoneId.of("America/Puerto_Rico");

        ArrayList<String> datasStrings = new ArrayList<>();

        Locale locale = new Locale("pt", "br");
        DayOfWeek firstDay = WeekFields.of(locale).getFirstDayOfWeek();
        LocalDate ldStart = LocalDate.now(TZ).with(TemporalAdjusters.previousOrSame(firstDay));
        DayOfWeek lastDay = DayOfWeek.of(((firstDay.getValue() + 5) % DayOfWeek.values().length) + 1);
        LocalDate ldEnd = LocalDate.now(TZ).with(TemporalAdjusters.nextOrSame(lastDay));

        List<LocalDate> totalDates = new ArrayList<>();

        while (!ldStart.isAfter(ldEnd)){
            totalDates.add(ldStart);
            ldStart = ldStart.plusDays(1);
        }
        HashMap<String, Double> mapaValores = new HashMap<>();

        totalDates.forEach(datas -> {
            mapaValores.put(formatter.format(datas), 0.0);
        });

        for (String data :
                mapaValores.keySet()) {
            System.out.println(data);
        }

    }

}
