package br.api.hallel.moduloAPI.repository.custom;

import br.api.hallel.moduloAPI.dto.v1.EscalaMinisterioResponseWithInfos;
import br.api.hallel.moduloAPI.dto.v1.EscalaMinisterioWithEventoInfoResponse;

import java.util.Date;
import java.util.List;

public interface CustomEscalaMinisterioRepository {
    List<EscalaMinisterioWithEventoInfoResponse> findAllWithEventosInfos();

    List<EscalaMinisterioWithEventoInfoResponse> findAllWithEventosInfosRangeDate(
            Date start, Date end);

    EscalaMinisterioResponseWithInfos findWithAllInfosById(
            String idEscalaMinisterio);
}
