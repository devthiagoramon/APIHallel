package br.api.hallel.moduloAPI.service.ministerio;

import br.api.hallel.moduloAPI.dto.v1.*;
import br.api.hallel.moduloAPI.model.Eventos;
import br.api.hallel.moduloAPI.model.FuncaoMinisterio;
import br.api.hallel.moduloAPI.model.MembroMinisterio;
import br.api.hallel.moduloAPI.payload.resposta.MembroResponse;

import java.util.List;

interface MinisterioInterface {

    MinisterioResponse createMinisterio(
            MinisterioDTO ministerioDTO);

    MinisterioResponse editMinisterio(String idMinisterio,
                                             MinisterioDTO ministerioDTO);

    void deleteMinisterio(String idMinisterio);

    List<MinisterioResponse> listMinisterios();

    List<MinisterioWithCoordsResponse> listMinisteriosWithCoords();

    MinisterioResponse listMinisterioById(String idMinisterio);

    MinisterioResponse alterarCoordenadoresInMinisterio(
            String idMinisterio,
            EditCoordMinisterioDTO editCoordMinisterioDTO);

    Boolean validateCoordenadorInMinisterio(
            String idMinisterio, String idUser);

    FuncaoMinisterio createFuncaoMinisterio(
            FuncaoMinisterioDTO funcaoMinisterioDTO);

    List<FuncaoMinisterio> listFuncaoOfMinisterio(
            String idMinisterio);

    FuncaoMinisterio listFuncaoMinisterioById(
            String idFuncaoMinisterio);

    FuncaoMinisterio editFuncaoMinisterio(
            String idFuncaoMinisterio,
            FuncaoMinisterioDTO funcaoMinisterioDTO);

    void deleteFuncaoMinisterio(String idFuncaoMinisterio);

    MembroMinisterioWithInfosResponse defineFunctionsToMembroMinisterio(
            DefineFunctionsDTO defineFunctionsDTO);

    List<MembroResponse> listMembrosToAddIntoThisMinisterio(
            String idMinisterio);

    List<MembroMinisterioWithInfosResponse> listMembrosFromMinisterio(
            String idMinisterio);
    MembroMinisterioWithInfosResponse listMembroMinisterioById(String idMembroMinisterio);

    MembroMinisterio addMembroMinisterio(
            AddMembroMinisterioDTO addMembroMinisterioDTO);

    void removerMembroMinisterio(String idMembroMinisterio);

    EscalaMinisterioResponse createEscalaMinisterio(
            Eventos evento, String ministerioId);

    List<EscalaMinisterioWithEventoInfoResponse> listEscalaMinisterio();
    List<EscalaMinisterioWithEventoInfoResponse> listEscalaMinisterioRangeDate(String start, String end);
    EscalaMinisterioResponseWithInfos listEscalaMinisterioByIdWithInfos(String idEscalaMinisterio);

}
