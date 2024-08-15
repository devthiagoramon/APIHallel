package br.api.hallel.moduloAPI.mapper.ministerio;

import br.api.hallel.moduloAPI.dto.v1.MinisterioDTO;
import br.api.hallel.moduloAPI.dto.v1.MinisterioResponse;
import br.api.hallel.moduloAPI.model.Ministerio;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MinisterioMapper {

    MinisterioMapper INSTANCE = Mappers.getMapper(MinisterioMapper.class);
    MinisterioDTO toDTO(Ministerio ministerio);
    Ministerio toModel(MinisterioDTO ministerioDTO);
    MinisterioResponse toResponse(Ministerio ministerio);
    @Mapping(target = "id",defaultValue = "", ignore = true)
    MinisterioResponse toResponse(MinisterioDTO ministerioDTO);
    List<MinisterioResponse> toListResponse(List<Ministerio> ministerioList);
}
