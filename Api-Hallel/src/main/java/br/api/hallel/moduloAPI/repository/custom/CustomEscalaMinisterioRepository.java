package br.api.hallel.moduloAPI.repository.custom;

import br.api.hallel.moduloAPI.dto.v1.ministerio.EscalaMinisterioResponseWithInfos;
import br.api.hallel.moduloAPI.dto.v1.ministerio.EscalaMinisterioWithEventoInfoResponse;

import java.util.Date;
import java.util.List;

public interface CustomEscalaMinisterioRepository {
    List<EscalaMinisterioWithEventoInfoResponse> findAllWithEventosInfos();

    List<EscalaMinisterioWithEventoInfoResponse> findAllWithEventosInfosRangeDate(
            Date start, Date end);

    EscalaMinisterioResponseWithInfos findWithAllInfosById(
            String idEscalaMinisterio);

    List<EscalaMinisterioWithEventoInfoResponse> findAllWithEventosInfosCanParticipateByMembroId(
            String membroId, Date start, Date end);

    List<EscalaMinisterioWithEventoInfoResponse> findAllWithEventosInfosConfirmedByMembroId(
            String membroId,
            Date start,
            Date end);

    List<EscalaMinisterioWithEventoInfoResponse> findAllWithEventosInfosRangeDateByMinisterioId(
            String idMinisterio,
            Date start,
            Date end);
}
