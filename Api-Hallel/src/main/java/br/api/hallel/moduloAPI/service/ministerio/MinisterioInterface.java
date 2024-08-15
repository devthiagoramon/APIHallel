package br.api.hallel.moduloAPI.service.ministerio;

import br.api.hallel.moduloAPI.dto.v1.EditCoordMinisterioDTO;
import br.api.hallel.moduloAPI.dto.v1.MinisterioDTO;
import br.api.hallel.moduloAPI.dto.v1.MinisterioResponse;

import java.util.List;

public interface MinisterioInterface {

    public MinisterioResponse createMinisterio(
            MinisterioDTO ministerioDTO);

    public MinisterioResponse editMinisterio(String idMinisterio,
                                             MinisterioDTO ministerioDTO);

    public void deleteMinisterio(String idMinisterio);

    public List<MinisterioResponse> listMinisterios();

    public MinisterioResponse listMinisterioById(String idMinisterio);

    public MinisterioResponse alterarCoordenadoresInMinisterio(
            String idMinisterio, EditCoordMinisterioDTO editCoordMinisterioDTO);
}
