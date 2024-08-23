package br.api.hallel.moduloAPI.mapper.ministerio;

import br.api.hallel.moduloAPI.dto.v1.ministerio.NaoConfirmarEscalaDTO;
import br.api.hallel.moduloAPI.model.NaoConfirmadoEscalaMinisterio;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

public interface NaoConfirmadoEscalaMinisterioMapper {
     public NaoConfirmadoEscalaMinisterioMapper INSTANCE = Mappers.getMapper(NaoConfirmadoEscalaMinisterioMapper.class);

     @Mapping(source = ".", target = ".")
     NaoConfirmadoEscalaMinisterio toModel(NaoConfirmarEscalaDTO dto);
}
