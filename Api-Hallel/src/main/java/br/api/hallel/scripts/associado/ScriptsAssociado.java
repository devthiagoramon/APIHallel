package br.api.hallel.scripts.associado;

import br.api.hallel.moduloAPI.financeiroNovo.model.PagamentosAssociado;
import br.api.hallel.moduloAPI.model.Associado;
import br.api.hallel.moduloAPI.repository.AssociadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Component
public class ScriptsAssociado {

    @Autowired
    private AssociadoRepository associadoRepository;

    @Scheduled(cron = "59 59 23 ? * *")
    public void fixAssociadosPagamentos() {
        List<Associado> associados = associadoRepository.findAll();

        for (Associado associado : associados) {
            if (associado.getPagamentosAssociados() != null) {
                for (PagamentosAssociado pagamentosAssociado : associado.getPagamentosAssociados()) {
                    LocalDate dataExpiroAssociado = associado.getDataExpiroAssociacao()
                            .toInstant()
                            .atZone(ZoneId.of("America/Puerto_Rico")).toLocalDate();
                    LocalDate pagamentoDataPaga = pagamentosAssociado.getDataPaga()
                            .toInstant()
                            .atZone(ZoneId.of("America/Puerto_Rico")).toLocalDate();

                    if (dataExpiroAssociado.getMonthValue() ==
                            pagamentoDataPaga.getMonthValue() &&
                            dataExpiroAssociado.getYear() ==
                                    pagamentoDataPaga.getYear()) {

                        associado.setDataExpiroAssociacao(Date.from(pagamentoDataPaga
                                .plusDays(30)
                                .atStartOfDay(ZoneId.of("America/Puerto_Rico"))
                                .toInstant()));
                        this.associadoRepository.save(associado);
                    }

                }
            }
        }

    }

}
