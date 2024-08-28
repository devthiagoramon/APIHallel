package br.api.hallel.moduloAPI.mapper.ministerio;

import br.api.hallel.moduloAPI.dto.v1.ministerio.AddMembroMinisterioDTO;
import br.api.hallel.moduloAPI.model.MembroMinisterio;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MembroMinisterioMapper {

    public MembroMinisterioMapper INSTANCE = Mappers.getMapper(MembroMinisterioMapper.class);

    @Mapping(target = ".", source = ".")
    MembroMinisterio toModel(AddMembroMinisterioDTO dto);
}
