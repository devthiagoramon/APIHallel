package br.api.hallel.moduloAPI.mapper.ministerio;

import br.api.hallel.moduloAPI.dto.v1.ministerio.FuncaoMinisterioDTO;
import br.api.hallel.moduloAPI.model.FuncaoMinisterio;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

public interface FuncaoMinisterioMapper {

    public static FuncaoMinisterioMapper INSTANCE = Mappers.getMapper(FuncaoMinisterioMapper.class);

    @Mapping(target = ".", source = ".")
    FuncaoMinisterio toModel(FuncaoMinisterioDTO funcaoMinisterioDTO);
    @Mapping(target = ".", source = ".")
    FuncaoMinisterioDTO toDTO(FuncaoMinisterio funcaoMinisterio);

}
