package br.api.hallel.moduloAPI.service.main;


import br.api.hallel.moduloAPI.model.Associado;
import br.api.hallel.moduloAPI.model.Recompensa;
import br.api.hallel.moduloAPI.model.Sorteio;
import br.api.hallel.moduloAPI.payload.requerimento.AssociadoReq;
import br.api.hallel.moduloAPI.payload.requerimento.RecompensaRequest;
import br.api.hallel.moduloAPI.payload.requerimento.SorteioRequest;
import br.api.hallel.moduloAPI.payload.resposta.AssociadoSorteioResponse;
import br.api.hallel.moduloAPI.payload.resposta.PerfilAssociadoSorteiosResponse;
import br.api.hallel.moduloAPI.payload.resposta.SorteioResponse;
import br.api.hallel.moduloAPI.repository.SorteioRepository;
import br.api.hallel.moduloAPI.service.financeiro.AssociadoService;
import br.api.hallel.moduloAPI.service.interfaces.SorteioInterface;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

@Service
@Log
public class SorteioService implements SorteioInterface {

    @Autowired
    private SorteioRepository repository;
    @Autowired
    private AssociadoService associadoService;

    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

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

        if (listAllSorteio() == null) {
            this.repository.deleteById(idSorteio);
        }

    }

    @Override
    public AssociadoSorteioResponse realizarSorteio(String idSorteio, RecompensaRequest recompensa) {

        Sorteio sorteio = repository.findById(idSorteio).get();

        int indexRandom = new Random().nextInt(sorteio.getSorteioAssociados().size());
        Associado associado = sorteio.getSorteioAssociados().get(indexRandom);

        System.out.println("tamanho da lista" + sorteio.getSorteioAssociados().size());

        System.out.println("index: " + indexRandom + ", " + associado.getNome());

        if (associado.getRecompensas() != null) {
            associado.getRecompensas().add(recompensa.toRecompensa());
        } else {
            List<Recompensa> listaRecompensas = new ArrayList<>();
            listaRecompensas.add(recompensa.toRecompensa());
            associado.setRecompensas(listaRecompensas);
        }

        if (sorteio.getUltimosAssociados() != null) {
            sorteio.getUltimosAssociados().add(associado);
        } else {
            List<Associado> listaAssociados = new ArrayList<>();
            listaAssociados.add(associado);
            sorteio.setUltimosAssociados(listaAssociados);
        }

        this.repository.save(sorteio);
        this.associadoService.updateAssociadoById(associado.getId(), associado);

        return new AssociadoSorteioResponse().toResponse(associado);
    }

    @Override
    public List<Associado> listAssociadosSorteadoByDate(String mes, String ano) {

        Sorteio sorteio = getSorteioDoMes();

        return sorteio.getSorteioAssociados();
    }

    //Adiciona o associado que teve o pagamento em dia ao Sorteio
    @Override
    public SorteioResponse adicionarAssociadoAoSorteio() {

        Sorteio sorteio = getSorteioDoMes();
        List<Associado> associadoToSorteioList = new ArrayList<>();
        LocalDate mesHoje = this.convertToLocalDate(new Date());

        for (Associado associado : this.associadoService.listAllAssociado()) {
            if (associado.getMesesPagos() != null) {
                for (Date mesesPago : associado.getMesesPagos()) {
                    LocalDate mesPago = this.convertToLocalDate(mesesPago);

                    if (mesPago.getMonthValue() == mesHoje.getMonthValue()) {
                        log.info("Associado Id (" + associado.getId() + ") pagou esse mês, " + mesPago);
                        log.info("Associado Id (" + associado.getId() + ") adicionado à lista de associados");
                        associadoToSorteioList.add(associado);
                        break;
                    }
                }

            }
        }

        for (Associado associadoBD : associadoToSorteioList) {

            AssociadoReq req = new AssociadoReq();
            Associado associadoReq = req.toAssociado(associadoBD);

            if (sorteio.getSorteioAssociados() != null) {

                boolean ifExists = false;

                for (Associado sorteioAssociado : sorteio.getSorteioAssociados()) {
                    if (associadoReq.getId().equals(sorteioAssociado.getId())) {
                        ifExists = true;
                        log.info("Associado Id(" + associadoBD.getId() + ")já está presente no sorteio");
                        break;
                    }
                }

                if (!ifExists) {
                    sorteio.getSorteioAssociados().
                            add(associadoReq);
                }

            } else {
                List<Associado> listAssociado = new ArrayList<>();
                listAssociado.add(associadoReq);
                sorteio.setSorteioAssociados(listAssociado);
            }
        }

        Sorteio response = this.repository.save(sorteio);
        return new SorteioResponse().toSorteioResponse(response);
    }

    @Override
    public List<PerfilAssociadoSorteiosResponse> listAllSorteioPerfilAssociado(String mes, String ano) {

        List<Sorteio> sorteios = this.repository.findAll();
        List<PerfilAssociadoSorteiosResponse> responses = new ArrayList<>();

        for (Sorteio sorteio : sorteios) {
            if(sdf.format(sorteio.getData()).substring(3).equals(mes+"/"+ano)) {
                responses.add(PerfilAssociadoSorteiosResponse.toPerfilAssociadoSorteiosResponse(sorteio));
            }
        }

        return responses;
    }

    public Sorteio getSorteioDoMes() {
        LocalDate mesHoje = new Date().toInstant().atZone(ZoneId.of("America/Puerto_Rico")).toLocalDate();
        Sorteio sorteio = null;
        for (Sorteio sorteios : this.repository.findAll()) {
            LocalDate mesSorteio = sorteios.getData().toInstant().atZone(ZoneId.of("America/Puerto_Rico")).toLocalDate();
            if (mesHoje.getMonthValue() == mesSorteio.getMonthValue()) {
                sorteio = sorteios;

                log.info("esse mes tem sorteio: " + sorteios.toString());
                break;
            }
        }
        return sorteio;
    }

    private LocalDate convertToLocalDate(Date date) {
        LocalDate dataConvertida = date.toInstant().atZone(ZoneId.of("America/Puerto_Rico")).toLocalDate();
        return dataConvertida;
    }
}
