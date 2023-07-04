package br.api.hallel.moduloAPI.service;

import br.api.hallel.moduloAPI.model.Alimentos;
import br.api.hallel.moduloAPI.model.Retiro;
import br.api.hallel.moduloAPI.payload.requerimento.AlimentoReq;
import br.api.hallel.moduloAPI.payload.resposta.AlimentoResponse;
import br.api.hallel.moduloAPI.payload.resposta.RetiroResponse;
import br.api.hallel.moduloAPI.repository.AlimentosRepository;
import br.api.hallel.moduloAPI.service.interfaces.AlimentosInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class AlimentoService implements AlimentosInterface {

    @Autowired
    private AlimentosRepository repository;

    @Override
    public Alimentos createAlimento(AlimentoReq req) {
        return this.repository.insert(req.toAlimentos());
    }

    @Override
    public List<AlimentoResponse> listAllAlimentos() {
        List<AlimentoResponse> responseList = new ArrayList<>();
        this.repository.findAll().forEach(alimentos -> {
            responseList.add(new AlimentoResponse().toResponse(alimentos));
        });
        return responseList;
    }

    @Override
    public AlimentoResponse listAlimentoById(String id) {
        Alimentos alimentos = this.repository.findById(id).isPresent() ?
                this.repository.findById(id).get() : null;

        return new AlimentoResponse().toResponse(alimentos);
    }

    @Override
    public AlimentoResponse updateAlimentoById(String id, AlimentoReq alimentoReq) {
        Alimentos alimentosRequest = alimentoReq.toAlimentos();
        alimentosRequest.setId(id);

        Alimentos response = this.listAlimentoById(id) != null
                ? this.repository.save(alimentosRequest) : null;

        return new AlimentoResponse().toResponse(response);
    }

    @Override
    public void deleteAlimentoById(String id) {
        if (this.listAlimentoById(id) != null) {
            this.repository.deleteById(id);
        } else {
            log.warn("Alimento com id " + id + " não existente!");
        }
    }
}
