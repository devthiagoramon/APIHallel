package br.api.hallel.service;

import br.api.hallel.model.Associado;
import br.api.hallel.model.Recompensa;
import br.api.hallel.model.Sorteio;
import br.api.hallel.payload.requerimento.AssociadoReq;
import br.api.hallel.payload.requerimento.RecompensaRequest;
import br.api.hallel.payload.resposta.AssociadoResponse;
import br.api.hallel.payload.resposta.AssociadoSorteioResponse;
import br.api.hallel.payload.resposta.SorteioResponse;
import br.api.hallel.repository.SorteioRepository;
import br.api.hallel.service.interfaces.RecompensaInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLOutput;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
@Slf4j
public class RecompensaService implements RecompensaInterface {

    @Autowired
    private AssociadoService associadoService;
    @Autowired
    private SorteioRepository sorteioRepository;


    @Override
    public SorteioResponse addToSort(String idSorteio, String idAssociado) {

        Sorteio sorteio = this.sorteioRepository.findById(idSorteio).get();

        Associado associado = associadoService.listAssociadoById(idAssociado);
        AssociadoReq req = new AssociadoReq();

        if (compareDates(associado)) {

            String dia = String.valueOf(getDataAtual()).substring(0, 2);

            if (Integer.parseInt(dia) < 31) {

                Associado associadoReq = req.toAssociado(associado);

                if (sorteio.getSorteioAssociados() != null) {
                    sorteio.getSorteioAssociados().
                            add(associadoReq);
                } else {
                    List<Associado> listAssociado = new ArrayList<>();
                    listAssociado.add(associadoReq);
                    sorteio.setSorteioAssociados(listAssociado);
                }

                log.info("ASSOCIADO COM RECOMPENSA");
            }

        }


        Sorteio response = this.sorteioRepository.save(sorteio);

        return new SorteioResponse().toSorteioResponse(response);
    }

    @Override
    public AssociadoSorteioResponse sendRecompensa(String idSorteio, RecompensaRequest recompensa) {

        Sorteio sorteio = sorteioRepository.findById(idSorteio).get();

        int indexRandom = new Random().nextInt(sorteio.getSorteioAssociados().size());

        System.out.println("index: "+indexRandom);
        System.out.println("tamanho da lista" + sorteio.getSorteioAssociados().size());

        Associado associado = sorteio.getSorteioAssociados().get(indexRandom);

        if(associado.getRecompensas() != null){
            associado.getRecompensas().add(recompensa.toRecompensa());
        }else{
            List<Recompensa> listaRecompensas = new ArrayList<>();
            listaRecompensas.add(recompensa.toRecompensa());
            associado.setRecompensas(listaRecompensas);
        }

        this.associadoService.updateAssociadoById(associado.getId(), associado);

        return new AssociadoSorteioResponse().toResponse(associado);
    }

    private Boolean compareDates(Associado associado) {

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        try {
            Date date = format.parse(associado.getTransacao().getDataExp());
            Date dateAtual = format.parse(getDataAtual());

            if (date.compareTo(dateAtual) < 0) {
                return false;
            } else {
                return true;
            }

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }

    //PEGA A DATA ATUAL DO SISTEMA
    private String getDataAtual() {
        Date dataAtual = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        return format.format(dataAtual);
    }

}
