package br.api.hallel.moduloAPI.service.interfaces;

import br.api.hallel.moduloAPI.model.Retiro;
import br.api.hallel.moduloAPI.payload.requerimento.AlimentoReq;
import br.api.hallel.moduloAPI.payload.requerimento.RetiroRequest;
import br.api.hallel.moduloAPI.payload.resposta.RetiroResponse;

import java.util.List;

public interface RetiroInterface {
    Retiro createRetiro(RetiroRequest request);
    List<RetiroResponse> listAllRetiros();
    RetiroResponse listRetiroById(String id);
    RetiroResponse updateRetiroById(RetiroRequest request,String id);
    void deleteRetiroById(String id);

    AlimentoReq addAlimentosRetiro(String idRetiro, AlimentoReq alimentoReq);

}
