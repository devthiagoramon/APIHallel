package br.api.hallel.moduloAPI.service.ministerio;

import br.api.hallel.moduloAPI.dto.v1.*;
import br.api.hallel.moduloAPI.model.*;
import br.api.hallel.moduloAPI.payload.resposta.MembroResponse;

import java.util.Date;
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

    MembroMinisterioWithInfosResponse listMembroMinisterioById(
            String idMembroMinisterio);

    MembroMinisterio addMembroMinisterio(
            AddMembroMinisterioDTO addMembroMinisterioDTO);

    void removerMembroMinisterio(String idMembroMinisterio);

    StatusParticipacaoEscalaMinisterio getStatusParticipacaoEscala(
            String idMembroMinisterio, String idEscalaMinisterio);

    Boolean confirmarParticipacaoEscala(String idMembroMinisterio,
                                        String idEscalaMinisterio);

    Boolean recusarParticipacaoEscala(
            NaoConfirmarEscalaDTO naoConfirmarEscalaDTO);

    NaoConfirmadoEscalaMinisterio createNaoConfirmadoEscalaMinisterio(
            NaoConfirmarEscalaDTO naoConfirmarEscalaDTO);

    NaoConfirmadoEscalaMinisterio editNaoConfirmadoEscalaMinisterio(
            String idNaoConfirmadoEscala,
            NaoConfirmarEscalaDTO naoConfirmarEscalaDTO);

    List<NaoConfirmadoEscalaMinisterio> listNaoConfirmadoEscalaMinisterioByIdMembroMinisterio(
            String idMemmbroMinisterio);

    NaoConfirmadoEscalaMinisterio listNaoConfirmadoEscalaMinisterioById(
            String idNaoConfirmadoEscalaMinisterio);

    void deleteNaoConfirmadoEscalaMinisterio(
            String idNaoConfirmadoEscalaMinisterio);

    EventosShortResponse listEventosThatMinisterioIsIn(String ministerioId);

    EscalaMinisterioResponse createEscalaMinisterio(
            Eventos evento, String ministerioId);

    EscalaMinisterioResponse alterarEscalaConfirmandoMembroMinisterio(
            String idEscala, List<String> idsMembrosMinisterio);

    EscalaMinisterioResponse alterarEscalaNaoConfirmandoMembroMinisterio(
            String idEscala,
            List<NaoConfirmadoEscalaDTOAdm> naoConfirmadoEscalaDtoAdm);

    List<EscalaMinisterioWithEventoInfoResponse> listEscalaMinisterio();

    List<EscalaMinisterioWithEventoInfoResponse> listEscalaMinisterioMembroIdCanParticipate(
            String membroId, Date start, Date end);

    List<EscalaMinisterioWithEventoInfoResponse> listEscalaMinisterioConfirmedMembro(
            String membroId, Date start, Date end);

    List<EscalaMinisterioWithEventoInfoResponse> listEscalaMinisterioRangeDate(
            Date start, Date end);

    EscalaMinisterioResponseWithInfos listEscalaMinisterioByIdWithInfos(
            String idEscalaMinisterio);

    List<NaoConfirmadoEscalaMinisterioWithInfos> listMotivosAusenciaMembroEventoByIdEscalasMinisterio(
            String idEscala);

    void deleteEscalasWithDeletingEvento(String idEvento);

}
