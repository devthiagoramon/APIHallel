package br.api.hallel.moduloAPI.mapper;

import br.api.hallel.moduloAPI.dto.v1.ministerio.*;
import br.api.hallel.moduloAPI.model.*;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;

public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.addMappings(new PropertyMap<AddMembroMinisterioDTO, MembroMinisterio>() {
            @Override
            protected void configure() {
                skip(destination.getId());
            }
        });

        modelMapper.addMappings(new PropertyMap<MinisterioDTO, Ministerio>() {
            @Override
            protected void configure() {
                skip(destination.getId());
            }
        });

        modelMapper.addMappings(new PropertyMap<FuncaoMinisterioDTO, FuncaoMinisterio>() {
            @Override
            protected void configure() {
                skip(destination.getId());
            }
        });

        modelMapper.addMappings(new PropertyMap<NaoConfirmarEscalaDTO, NaoConfirmadoEscalaMinisterio>() {
            @Override
            protected void configure() {
                skip(destination.getId());
            }
        });

        modelMapper.addMappings(new PropertyMap<EscalaMinisterioDTO, EscalaMinisterio>() {
            @Override
            protected void configure() {
                skip(destination.getId());
            }
        });

        return modelMapper;
    }

}
