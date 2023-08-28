package br.api.hallel.moduloAPI.service.eventos;

import br.api.hallel.moduloAPI.model.Alimentos;
import br.api.hallel.moduloAPI.payload.requerimento.AlimentoReq;
import br.api.hallel.moduloAPI.payload.resposta.AlimentoResponse;
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

    //Salvar um alimento no banco de dados (para evento)
    @Override
    public Alimentos createAlimento(AlimentoReq req) {
        return this.repository.insert(req.toAlimentos());
    }

    //Listar todos os alimentos salvos no banco de dados (para evento)
    @Override
    public List<AlimentoResponse> listAllAlimentos() {
        List<AlimentoResponse> responseList = new ArrayList<>();
        this.repository.findAll().forEach(alimentos -> {
            responseList.add(new AlimentoResponse().toResponse(alimentos));
        });
        return responseList;
    }

    //Pegar um alimento tendo como parametro seu Id
    @Override
    public AlimentoResponse listAlimentoById(String id) {
        Alimentos alimentos = this.repository.findById(id).isPresent() ?
                this.repository.findById(id).get() : null;

        return new AlimentoResponse().toResponse(alimentos);
    }

    //Atualizar um alimento tendo como argumento um Id
    @Override
    public AlimentoResponse updateAlimentoById(String id, AlimentoReq alimentoReq) {
        Alimentos alimentosRequest = alimentoReq.toAlimentos();
        alimentosRequest.setId(id);

        Alimentos response = this.listAlimentoById(id) != null
                ? this.repository.save(alimentosRequest) : null;

        return new AlimentoResponse().toResponse(response);
    }

    //Deletar um alimento tendo como um argumento um Id
    @Override
    public void deleteAlimentoById(String id) {
        if (this.listAlimentoById(id) != null) {
            this.repository.deleteById(id);
        } else {
            log.warn("Alimento com id " + id + " não existente!");
        }
    }

    //Deletar um alimento, tendo como parâmetro o próprio Obj de alimento
    @Override
    public void deleteAlimentoByObj(AlimentoReq alimentoReq) {

        this.repository.findAll().forEach(alimentos -> {
            AlimentoReq alimentoVal = new AlimentoReq().toAlimentoReq(alimentos);
            if (alimentoVal.equals(alimentoReq)) {
                this.repository.delete(alimentos);
            }
        });

    }
}
