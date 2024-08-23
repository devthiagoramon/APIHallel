package br.api.hallel.moduloAPI.repository.custom;

import br.api.hallel.moduloAPI.dto.v1.ministerio.NaoConfirmadoEscalaMinisterioWithInfos;

import java.util.List;

public interface CustomNaoConfirmadoEscalaMinisterioRepository {
    List<NaoConfirmadoEscalaMinisterioWithInfos> findAllWithEscalaId(
            String idEscala);

}
