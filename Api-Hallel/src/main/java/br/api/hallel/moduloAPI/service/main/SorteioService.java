package br.api.hallel.moduloAPI.service.main;


import br.api.hallel.moduloAPI.model.Associado;
import br.api.hallel.moduloAPI.model.Recompensa;
import br.api.hallel.moduloAPI.model.Sorteio;
import br.api.hallel.moduloAPI.payload.requerimento.AssociadoReq;
import br.api.hallel.moduloAPI.payload.requerimento.RecompensaRequest;
import br.api.hallel.moduloAPI.payload.requerimento.SorteioRequest;
import br.api.hallel.moduloAPI.payload.resposta.AssociadoSorteioResponse;
import br.api.hallel.moduloAPI.payload.resposta.SorteioResponse;
import br.api.hallel.moduloAPI.repository.SorteioRepository;
import br.api.hallel.moduloAPI.service.financeiro.AssociadoService;
import br.api.hallel.moduloAPI.service.interfaces.SorteioInterface;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Log
public class SorteioService implements SorteioInterface {

    @Autowired
    private SorteioRepository repository;
    @Autowired
    private AssociadoService associadoService;

    //Criar um sorteio
    @Override
    public Sorteio createSorteio(SorteioRequest sorteio) {
        return this.repository.insert(sorteio.toSorteio());
    }

    //Listar os sorteios ocorridos
    @Override
    public List<SorteioResponse> listAllSorteio() {
        List<SorteioResponse> listaSorteio = new ArrayList<>();

        this.repository.findAll().stream().forEach(sorteios -> {
            SorteioResponse sorteioResponse = new SorteioResponse();
            listaSorteio.add(sorteioResponse.toSorteioResponse(sorteios));
        });

        return listaSorteio;
    }

    //Listar um sorteio pelo Id
    @Override
    public SorteioResponse listSorteioById(String idSorteio) {

        Optional<Sorteio> optional = this.repository.findById(idSorteio);

        if (optional.isPresent()) {
            Sorteio sorteio = optional.get();

            return new SorteioResponse().toSorteioResponse(sorteio);
        }

        return null;
    }

    //Atualizar as informações do sorteio
    @Override
    public SorteioResponse updateSorteioById(String idSorteio, SorteioRequest sorteioRequest) {

        Sorteio sorteioAux = sorteioRequest.toSorteio();
        sorteioAux.setId(idSorteio);

        Sorteio sorteioResponse = this.listSorteioById(idSorteio) == null ?
                this.repository.save(sorteioAux) : null;

        return new SorteioResponse().toSorteioResponse(sorteioResponse);
    }

    //Remover sorteio do Banco
    @Override
    public void deleteSorteioById(String idSorteio) {

        if(listAllSorteio() == null){
            this.repository.deleteById(idSorteio);
        }

    }

    @Override
    public AssociadoSorteioResponse realizarSorteio(String idSorteio, RecompensaRequest recompensa) {

        Sorteio sorteio = repository.findById(idSorteio).get();

        int indexRandom = new Random().nextInt(sorteio.getSorteioAssociados().size());
        Associado associado = sorteio.getSorteioAssociados().get(indexRandom);

        System.out.println("tamanho da lista" + sorteio.getSorteioAssociados().size());

        System.out.println("index: "+indexRandom+ ", "+associado.getNome());

        if(associado.getRecompensas() != null){
            associado.getRecompensas().add(recompensa.toRecompensa());
        }else{
            List<Recompensa> listaRecompensas = new ArrayList<>();
            listaRecompensas.add(recompensa.toRecompensa());
            associado.setRecompensas(listaRecompensas);
        }

        if(sorteio.getUltimosAssociados() != null){
            sorteio.getUltimosAssociados().add(associado);
        }else{
            List<Associado> listaAssociados = new ArrayList<>();
            listaAssociados.add(associado);
            sorteio.setUltimosAssociados(listaAssociados);
        }

        this.repository.save(sorteio);
        this.associadoService.updateAssociadoById(associado.getId(), associado);

        return new AssociadoSorteioResponse().toResponse(associado);
    }

    //Adiciona o associado que teve o pagamento em dia ao Sorteio
    @Override
    public SorteioResponse adicionarAssociadoAoSorteio(String idSorteio, String idAssociado) {

        Sorteio sorteio = this.repository.findById(idSorteio).get();

        Associado associado = associadoService.listAssociadoById(idAssociado);
        AssociadoReq req = new AssociadoReq();

        if (compareDates(associado)) {

            String dia = String.valueOf(MainService.getDataAtual()).substring(0, 2);

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


        Sorteio response = this.repository.save(sorteio);

        return new SorteioResponse().toSorteioResponse(response);
    }


    //Compara a data atual com a data de pagamento do associado
    private Boolean compareDates(Associado associado) {



//        try {
//            Date date = format.parse(associado.getTransacao().getDataExp());
//            Date dateAtual = format.parse(getDataAtual());
//
//            if (date.compareTo(dateAtual) < 0) {
//                return false;
//            } else {
//                return true;
//            }
//
//        } catch (ParseException e) {
//            throw new RuntimeException(e);
//        }
        return true;
    }

}
