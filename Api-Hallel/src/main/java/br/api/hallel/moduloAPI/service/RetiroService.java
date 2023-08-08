package br.api.hallel.moduloAPI.service;

import br.api.hallel.moduloAPI.model.Alimentos;
import br.api.hallel.moduloAPI.model.Retiro;
import br.api.hallel.moduloAPI.payload.requerimento.AlimentoReq;
import br.api.hallel.moduloAPI.payload.requerimento.RetiroRequest;
import br.api.hallel.moduloAPI.payload.resposta.AlimentoResponse;
import br.api.hallel.moduloAPI.payload.resposta.RetiroResponse;
import br.api.hallel.moduloAPI.repository.RetiroRepository;
import br.api.hallel.moduloAPI.service.interfaces.RetiroInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class RetiroService implements RetiroInterface {

    @Autowired
    private RetiroRepository repository;
    @Autowired
    private AlimentoService alimentoService;

    @Override
    public Retiro createRetiro(RetiroRequest request) {
        return request != null ? this.repository.save(request.toRetiro()) : null;
    }

    @Override
    public List<RetiroResponse> listAllRetiros() {

        List<RetiroResponse> responseList = new ArrayList<>();

        this.repository.findAll().forEach(retiro -> {
            responseList.add(new RetiroResponse().toResponse(retiro));
        });

        return responseList;
    }

    @Override
    public RetiroResponse listRetiroById(String id) {
        Retiro retiro = this.repository.findById(id).isPresent() ?
                this.repository.findById(id).get() : null;

        return new RetiroResponse().toResponse(retiro);
    }

    @Override
    public RetiroResponse updateRetiroById(RetiroRequest request, String id) {

        Retiro retiroRequest = request.toRetiro();
        retiroRequest.setId(id);

        Retiro response = this.listRetiroById(id) != null
                ? this.repository.save(retiroRequest) : null;

        return new RetiroResponse().toResponse(response);
    }

    @Override
    public void deleteRetiroById(String id) {
        if (this.listRetiroById(id) != null) {

            log.info("Retiro removido!");
            this.repository.deleteById(id);
        }
    }

    @Override
    public AlimentoReq addAlimentosRetiro(String idRetiro, AlimentoReq alimentoReq) {

        RetiroResponse retiroResponse = this.listRetiroById(idRetiro);
        RetiroRequest retiroRequest = retiroResponse.toRetiroRequest();
        Alimentos alimentos = null;
        if (retiroRequest.getAlimentos() == null) {

            List<Alimentos> alimentosList = new ArrayList<>();
            alimentos = this.alimentoService.createAlimento(alimentoReq);
            alimentosList.add(alimentos);
            retiroRequest.setAlimentos(alimentosList);
        } else {
            alimentos = this.alimentoService.createAlimento(alimentoReq);
            retiroRequest.getAlimentos().add(alimentos);

        }

        this.updateRetiroById(retiroRequest, idRetiro);

        return alimentoReq;
    }

    @Override
    public AlimentoReq removeAlimentoRetiro(String idRetiro, AlimentoReq alimentoReq) {

        RetiroResponse retiroResponse = this.listRetiroById(idRetiro);
        RetiroRequest retiroRequest = retiroResponse.toRetiroRequest();

        if (retiroRequest.getAlimentos() != null) {
            int index = 0;

            for (Alimentos alimentoFor : retiroRequest.getAlimentos()) {
                AlimentoReq alimentoVal = new AlimentoReq().toAlimentoReq(alimentoFor);

                if (alimentoVal.equals(alimentoReq)) {

                    retiroRequest.getAlimentos().remove(index);

                    this.updateRetiroById(retiroRequest, idRetiro);
                    this.alimentoService.deleteAlimentoByObj(alimentoReq);
                    log.warn("Alimento removido com sucesso");
                    break;
                }
                index++;
            }

            return alimentoReq;
        } else {
            log.warn("Retiro não possui alimentos cadastrados!");
        }


        return null;
    }

    @Override
    public AlimentoReq atualizarAlimentoRetiro(String idRetiro, AlimentoReq alimentoReq) {

        RetiroResponse retiroResponse = this.listRetiroById(idRetiro);
        RetiroRequest retiroRequest = retiroResponse.toRetiroRequest();

        if (retiroRequest.getAlimentos() != null) {
            int index = 0;

            for (Alimentos alimentoFor : retiroRequest.getAlimentos()) {
                AlimentoReq alimentoVal = new AlimentoReq().toAlimentoReq(alimentoFor);

                System.out.println(alimentoFor.toString());
                System.out.println(alimentoVal.toString());

                if (new AlimentoReq().toAlimentoReq(alimentoFor).equals(alimentoVal)) {
                    Alimentos alimentoUpdate = alimentoReq.toAlimentos();
                    alimentoUpdate.setId(alimentoFor.getId());

                    retiroRequest.getAlimentos().set(index, alimentoUpdate);

                    this.updateRetiroById(retiroRequest, idRetiro);
                    this.alimentoService.updateAlimentoById(alimentoFor.getId(), alimentoReq);

                    log.warn("Alimento atualizado com sucesso");
                    break;
                }
                index++;
            }

            return alimentoReq;
        } else {
            log.warn("Retiro não possui alimentos cadastrados!");
        }

        return null;
    }

    @Override
    public List<AlimentoResponse> listAllAlimentosByRetiro(String idRetiro) {

        RetiroResponse retiroResponse = this.listRetiroById(idRetiro);

        List<AlimentoResponse> alimentoResponseList = new ArrayList<>();

        retiroResponse.getAlimentos().forEach(alimentos -> {

            alimentoResponseList.add(new AlimentoResponse().toResponse(alimentos));
        });

        return alimentoResponseList;
    }


}
