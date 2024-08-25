package br.api.hallel.moduloAPI.mapper.ministerio;

import br.api.hallel.moduloAPI.dto.v1.ministerio.MinisterioDTO;
import br.api.hallel.moduloAPI.dto.v1.ministerio.MinisterioResponse;
import br.api.hallel.moduloAPI.model.Ministerio;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MinisterioMapper {

    MinisterioMapper INSTANCE = Mappers.getMapper(MinisterioMapper.class);
    @Mapping(target = ".", source = ".")
    MinisterioDTO toDTO(Ministerio ministerio);
    @Mapping(target = ".", source = ".")
    Ministerio toModel(MinisterioDTO ministerioDTO);
    @Mapping(target = "id", source = "id")
    MinisterioResponse toResponse(Ministerio ministerio);

    @BeanMapping( ignoreByDefault = true )
    MinisterioResponse toResponse(MinisterioDTO ministerioDTO);
    @Mapping(target = ".", source = ".")
    List<MinisterioResponse> toListResponse(List<Ministerio> ministerioList);
}
