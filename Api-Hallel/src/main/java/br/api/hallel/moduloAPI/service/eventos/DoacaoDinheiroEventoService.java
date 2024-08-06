package br.api.hallel.moduloAPI.service.eventos;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Slf4j
@Service
public class DoacaoDinheiroEventoService implements DoacaoDinheiroEventoInterface {

    @Autowired
    DoacaoDinheiroEventoRepository repository;

    @Override
    public DoacaoDinheiroEvento createObjeto(DoacaoDinheiroEventoReq req) {
       return this.repository.insert(req.toDoacaoDinheiroEvento());
    }

    @Override
    public List<DoacaoDinheiroEventoResponse> listAllObjetos() {
        List<DoacaoDinheiroEventoResponse> responseList = new ArrayList<>();
        this.repository.findAll().forEach(objeto -> {
            responseList.add(new DoacaoDinheiroEventoResponse().toResponse(objeto));
        });
        return responseList;
    }

    @Override
    public DoacaoDinheiroEventoResponse ListDoacaoObjetosEventosById(String Id) {
        return null;
    }

    @Override
    public DoacaoDinheiroEventoResponse updateDoacaoObjetosEventosById(String Id, DoacaoDinheiroEventoReq doacaoDinheiroEventosReq) {
        return null;
    }

    @Override
    public void deleteDoacaoObjetosEventosById(String Id) {

    }

    @Override
    public void deleteDoacaoObjetosEventosByObj(DoacaoDinheiroEventoReq doacaoDinheiroEventosReq) {

    }
}
