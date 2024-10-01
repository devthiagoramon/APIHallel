package br.api.hallel.moduloAPI.mapper.ministerio;

import br.api.hallel.moduloAPI.dto.v1.ministerio.*;
import br.api.hallel.moduloAPI.model.*;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;

import java.util.ArrayList;
import java.util.List;

public class MinisterioMapper {

    private static final ModelMapper MAPPER = new ModelMapper();

    public static Ministerio toModelMinisterio(
            MinisterioDTO ministerioDTO) {
        MAPPER.getConfiguration()
              .setMatchingStrategy(MatchingStrategies.STRICT);
        return MAPPER.map(ministerioDTO, Ministerio.class);
    }

    public static MinisterioDTO toMinisterioDTO(
            Ministerio ministerio) {
        return MAPPER.map(ministerio, MinisterioDTO.class);
    }

    public static MinisterioResponse toMinisterioResponse(
            Ministerio ministerio) {
        return MAPPER.map(ministerio, MinisterioResponse.class);
    }


    public static List<MinisterioResponse> toMinisterioResponseList(
            List<Ministerio> all) {

        List<MinisterioResponse> responses = new ArrayList<>();
        all.forEach(m -> responses.add(toMinisterioResponse(m)));
        return responses;
    }

    public static FuncaoMinisterio toFuncaoMinisterio(
            FuncaoMinisterioDTO funcaoMinisterioDTO) {
        MAPPER.getConfiguration()
              .setMatchingStrategy(MatchingStrategies.STRICT);
        return MAPPER.map(funcaoMinisterioDTO, FuncaoMinisterio.class);
    }

    public static MembroMinisterio toMembroMinisterio(
            AddMembroMinisterioDTO addMembroMinisterioDTO
                                                     ) {
        MAPPER.getConfiguration()
              .setMatchingStrategy(MatchingStrategies.STRICT);

        return MAPPER.map(addMembroMinisterioDTO, MembroMinisterio.class);
    }

    public static NaoConfirmadoEscalaMinisterio toNaoConfirmadoEscalaMinisterio(
            NaoConfirmarEscalaDTO naoConfirmarEscalaDTO
                                                                               ) {
        return MAPPER.map(naoConfirmarEscalaDTO, NaoConfirmadoEscalaMinisterio.class);
    }

    public static EscalaMinisterio toEscalaMinisterio(
            EscalaMinisterioDTO escalaMinisterioDTO
                                                     ) {
        return MAPPER.map(escalaMinisterioDTO, EscalaMinisterio.class);
    }

    public static EscalaMinisterioResponse toEscalaMinisterioResponse(
            EscalaMinisterio escalaMinisterio
                                                                     ) {
        return MAPPER.map(escalaMinisterio, EscalaMinisterioResponse.class);
    }


}
