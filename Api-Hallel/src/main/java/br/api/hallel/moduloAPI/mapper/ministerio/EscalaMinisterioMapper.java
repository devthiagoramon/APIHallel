package br.api.hallel.moduloAPI.mapper.ministerio;

import br.api.hallel.moduloAPI.dto.v1.EscalaMinisterioDTO;
import br.api.hallel.moduloAPI.dto.v1.EscalaMinisterioResponse;
import br.api.hallel.moduloAPI.model.EscalaMinisterio;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EscalaMinisterioMapper {

    EscalaMinisterioMapper INSTANCE = Mappers.getMapper(EscalaMinisterioMapper.class);

    @Mapping(target = ".", source = ".")
    public EscalaMinisterioResponse toResponse(EscalaMinisterio escalaMinisterio);
    @Mapping(target = ".", source = ".")
    public EscalaMinisterioDTO toDto(EscalaMinisterio escalaMinisterio);
    @Mapping(target = ".", source = ".")
    public EscalaMinisterio toModel(EscalaMinisterioDTO escalaMinisterioDTO);


}
