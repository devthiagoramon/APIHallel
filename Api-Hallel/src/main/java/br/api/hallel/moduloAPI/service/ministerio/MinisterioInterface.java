package br.api.hallel.moduloAPI.service.ministerio;

import br.api.hallel.moduloAPI.dto.v1.*;
import br.api.hallel.moduloAPI.model.Eventos;

import java.util.List;

public interface MinisterioInterface {

    public MinisterioResponse createMinisterio(
            MinisterioDTO ministerioDTO);

    public MinisterioResponse editMinisterio(String idMinisterio,
                                             MinisterioDTO ministerioDTO);

    public void deleteMinisterio(String idMinisterio);

    public List<MinisterioResponse> listMinisterios();
    public List<MinisterioWithCoordsResponse> listMinisteriosWithCoords();

    public MinisterioResponse listMinisterioById(String idMinisterio);

    public MinisterioResponse alterarCoordenadoresInMinisterio(
            String idMinisterio, EditCoordMinisterioDTO editCoordMinisterioDTO);

    public Boolean validateCoordenadorInMinisterio(String idMinisterio, String idUser);

    public EscalaMinisterioResponse createEscalaMinisterio(
            Eventos evento, String ministerioId);
}
