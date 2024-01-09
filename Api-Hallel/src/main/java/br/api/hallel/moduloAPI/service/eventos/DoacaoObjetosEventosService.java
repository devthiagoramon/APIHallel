package br.api.hallel.moduloAPI.service.eventos;

import br.api.hallel.moduloAPI.model.DoacaoObjetosEventos;
import br.api.hallel.moduloAPI.payload.requerimento.DoacaoObjetosEventosReq;
import br.api.hallel.moduloAPI.payload.resposta.DoacaoObjetosEventosResponse;
import br.api.hallel.moduloAPI.repository.DoacaoObjetosEventosRepository;
import br.api.hallel.moduloAPI.service.interfaces.DoacaoObjetosEventosInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class DoacaoObjetosEventosService implements DoacaoObjetosEventosInterface {

    @Autowired
    private DoacaoObjetosEventosRepository repository;

    @Override
    public DoacaoObjetosEventos createObjeto(DoacaoObjetosEventosReq req) {
        return this.repository.insert(req.toDoacaoObjetosEventos());
    }

    @Override
    public List<DoacaoObjetosEventosResponse> listAllObjetos() {
        List<DoacaoObjetosEventosResponse> responseList = new ArrayList<>();
        this.repository.findAll().forEach(objeto -> {
            responseList.add(new DoacaoObjetosEventosResponse().toResponse(objeto));
        });
        return responseList;
    }

    @Override
    public DoacaoObjetosEventosResponse ListDoacaoObjetosEventosById(String id) {
        DoacaoObjetosEventos objeto = this.repository.findById(id).orElse(null);
        return new DoacaoObjetosEventosResponse().toResponse(objeto);
    }

    @Override
    public DoacaoObjetosEventosResponse updateDoacaoObjetosEventosById(String id, DoacaoObjetosEventosReq req) {
        DoacaoObjetosEventos objetoRequest = req.toDoacaoObjetosEventos();
        objetoRequest.setId(id);

        DoacaoObjetosEventos response = this.repository.findById(id)
                .map(existingObjeto -> this.repository.save(objetoRequest))
                .orElse(null);

        return new DoacaoObjetosEventosResponse().toResponse(response);
    }

    @Override
    public void deleteDoacaoObjetosEventosById(String id) {
        if (this.ListDoacaoObjetosEventosById(id) != null) {
            this.repository.deleteById(id);
        } else {
            log.warn("Objeto com id " + id + " não existente!");
        }
    }

    @Override
    public void deleteDoacaoObjetosEventosByObj(DoacaoObjetosEventosReq req) {
        this.repository.findAll().forEach(objeto -> {
            DoacaoObjetosEventosReq objetoVal = new DoacaoObjetosEventosReq().ToDoacaoObjetosEventosReq(objeto);
            if (objetoVal.equals(req)) {
                this.repository.delete(objeto);
            }
        });
    }
}
