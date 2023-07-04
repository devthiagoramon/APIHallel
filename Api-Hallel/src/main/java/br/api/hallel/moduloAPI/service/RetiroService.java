package br.api.hallel.moduloAPI.service;

import br.api.hallel.moduloAPI.model.Alimentos;
import br.api.hallel.moduloAPI.model.Retiro;
import br.api.hallel.moduloAPI.payload.requerimento.AlimentoReq;
import br.api.hallel.moduloAPI.payload.requerimento.RetiroRequest;
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

        if (retiroRequest.getAlimentos() == null) {

            List<Alimentos> alimentosList = new ArrayList<>();
            alimentosList.add(alimentoReq.toAlimentos());
            retiroRequest.setAlimentos(alimentosList);
        } else {
            retiroRequest.getAlimentos().add(alimentoReq.toAlimentos());

        }

        this.updateRetiroById(retiroRequest, idRetiro);
        return alimentoReq;
    }


}
