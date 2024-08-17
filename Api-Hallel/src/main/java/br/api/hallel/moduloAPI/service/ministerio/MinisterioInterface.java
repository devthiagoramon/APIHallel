package br.api.hallel.moduloAPI.service.ministerio;

import br.api.hallel.moduloAPI.dto.v1.*;
import br.api.hallel.moduloAPI.model.Eventos;
import br.api.hallel.moduloAPI.model.FuncaoMinisterio;
import br.api.hallel.moduloAPI.model.MembroMinisterio;
import br.api.hallel.moduloAPI.payload.resposta.MembroResponse;

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
            String idMinisterio,
            EditCoordMinisterioDTO editCoordMinisterioDTO);

    public Boolean validateCoordenadorInMinisterio(
            String idMinisterio, String idUser);

    public FuncaoMinisterio createFuncaoMinisterio(
            FuncaoMinisterioDTO funcaoMinisterioDTO);

    public List<FuncaoMinisterio> listFuncaoOfMinisterio(
            String idMinisterio);

    public FuncaoMinisterio listFuncaoMinisterioById(
            String idFuncaoMinisterio);

    public FuncaoMinisterio editFuncaoMinisterio(
            String idFuncaoMinisterio,
            FuncaoMinisterioDTO funcaoMinisterioDTO);
    public void deleteFuncaoMinisterio(String idFuncaoMinisterio);

    public List<MembroResponse> listMembrosToAddIntoThisMinisterio(
            String idMinisterio);

    public List<MembroMinisterioWithInfosResponse> listMembrosFromMinisterio(
            String idMinisterio);

    public MembroMinisterio addMembroMinisterio(
            AddMembroMinisterioDTO addMembroMinisterioDTO);

    public void removerMembroMinisterio(String idMembroMinisterio);

    public EscalaMinisterioResponse createEscalaMinisterio(
            Eventos evento, String ministerioId);
}
