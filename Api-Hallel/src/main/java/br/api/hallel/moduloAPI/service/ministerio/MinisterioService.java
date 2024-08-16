package br.api.hallel.moduloAPI.service.ministerio;

import br.api.hallel.moduloAPI.dto.v1.*;
import br.api.hallel.moduloAPI.mapper.ministerio.EscalaMinisterioMapper;
import br.api.hallel.moduloAPI.mapper.ministerio.MembroMinisterioMapper;
import br.api.hallel.moduloAPI.mapper.ministerio.MinisterioMapper;
import br.api.hallel.moduloAPI.model.EscalaMinisterio;
import br.api.hallel.moduloAPI.model.Eventos;
import br.api.hallel.moduloAPI.model.MembroMinisterio;
import br.api.hallel.moduloAPI.model.Ministerio;
import br.api.hallel.moduloAPI.payload.resposta.MembroResponse;
import br.api.hallel.moduloAPI.repository.*;
import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class MinisterioService implements MinisterioInterface {

    private final MinisterioRepository ministerioRepository;
    private final FuncaoMinisterioRepository funcaoMinisterioRepository;
    private final MembroMinisterioRepository membroMinisterioRepository;
    private final EscalaMinisterioRepository escalaMinisterioRepository;
    private final NaoConfirmadoEscalaMinisterioRepository naoConfirmadoEscalaMinisterioRepository;
    private final MembroRepository membroRepository;

    public MinisterioService(
            MinisterioRepository ministerioRepository,
            FuncaoMinisterioRepository funcaoMinisterioRepository,
            MembroMinisterioRepository membroMinisterioRepository,
            EscalaMinisterioRepository escalaMinisterioRepository,
            NaoConfirmadoEscalaMinisterioRepository naoConfirmadoEscalaMinisterioRepository,
            MembroRepository membroRepository) {
        this.ministerioRepository = ministerioRepository;
        this.funcaoMinisterioRepository = funcaoMinisterioRepository;
        this.membroMinisterioRepository = membroMinisterioRepository;
        this.escalaMinisterioRepository = escalaMinisterioRepository;
        this.naoConfirmadoEscalaMinisterioRepository = naoConfirmadoEscalaMinisterioRepository;
        this.membroRepository = membroRepository;
    }

    @NotNull
    private Ministerio getMinisterioById(String idMinisterio) throws
            RuntimeException {
        Optional<Ministerio> optional = ministerioRepository.findById(idMinisterio);
        if (optional.isEmpty()) {
            log.info("Error: getting ministerio " + idMinisterio + "!");
            throw new RuntimeException("Can't find ministerio by this id!");
        }
        return optional.get();
    }

    @Override
    public MinisterioResponse createMinisterio(
            MinisterioDTO ministerioDTO) {
        log.info("Creating ministerio...");
        Ministerio ministerio = this.ministerioRepository.insert(MinisterioMapper.INSTANCE.toModel(ministerioDTO));
        log.info("Ministerio id " + ministerio.getId() + " created!");
        return MinisterioMapper.INSTANCE.toResponse(ministerio);
    }

    @Override
    public MinisterioResponse editMinisterio(String idMinisterio,
                                             MinisterioDTO ministerioDTO) {
        log.info("Editing ministerio...");
        Ministerio ministerio = getMinisterioById(idMinisterio);
        ministerio.setNome(ministerioDTO.getNome());
        ministerio.setCoordenadorId(ministerioDTO.getCoordenadorId());
        ministerio.setViceCoordenadorId(ministerioDTO.getViceCoordenadorId());
        Ministerio ministerioUpdated = this.ministerioRepository.save(ministerio);
        log.info("Ministerio id " + ministerio.getId() + " edited!");
        return MinisterioMapper.INSTANCE.toResponse(ministerioUpdated);
    }


    @Override
    public void deleteMinisterio(String idMinisterio) {
        log.info("Deleting ministerio...");
        Ministerio ministerio = getMinisterioById(idMinisterio);
        this.ministerioRepository.delete(ministerio);
        log.info("Ministerio " + ministerio.getId() + "_" + ministerio.getNome() + " deleted!");
    }

    @Override
    public List<MinisterioResponse> listMinisterios() {
        log.info("Listing ministerios...");
        return MinisterioMapper.INSTANCE.toListResponse(this.ministerioRepository.findAll());
    }

    @Override
    public List<MinisterioWithCoordsResponse> listMinisteriosWithCoords() {
        return this.ministerioRepository.findAllWithCoords()
                                        .getMappedResults();
    }

    @Override
    public MinisterioResponse listMinisterioById(
            String idMinisterio) {
        log.info("Listing ministerio " + idMinisterio + "...");
        return MinisterioMapper.INSTANCE.toResponse(getMinisterioById(idMinisterio));
    }

    @Override
    public MinisterioResponse alterarCoordenadoresInMinisterio(
            String idMinisterio,
            EditCoordMinisterioDTO editCoordMinisterioDTO) {
        log.info("Changing coord and vice-coord in ministerio " + idMinisterio + "...");
        Ministerio ministerio = getMinisterioById(idMinisterio);
        ministerio.setCoordenadorId(editCoordMinisterioDTO.getCoordenadorId());
        ministerio.setViceCoordenadorId(editCoordMinisterioDTO.getViceCoordenadorId());
        Ministerio ministerioUpdated = this.ministerioRepository.save(ministerio);
        log.info("Coord and vice-coord changed in ministerio " + ministerioUpdated.getNome());
        return MinisterioMapper.INSTANCE.toResponse(ministerioUpdated);
    }

    @Override
    public Boolean validateCoordenadorInMinisterio(
            String idMinisterio, String idUser) {
        log.info("Validating coordenator of ministerios " + idMinisterio + " user_id: " + idUser);
        Ministerio ministerio = getMinisterioById(idMinisterio);
        return ministerio.getCoordenadorId()
                         .equals(idUser) || ministerio.getViceCoordenadorId()
                                                      .equals(idUser);
    }

    @Override
    public List<MembroResponse> listMembrosToAddIntoThisMinisterio(
            String idMinisterio) {
        return membroRepository.findMembrosWithNoParticipationInThisMinisterio(idMinisterio);
    }

    @Override
    public List<MembroMinisterioWithInfosResponse> listMembrosFromMinisterio(
            String idMinisterio) {
        return membroMinisterioRepository.findWithInfosByMinisterioId(idMinisterio);
    }

    @Override
    public MembroMinisterio addMembroMinisterio(
            AddMembroMinisterioDTO addMembroMinisterioDTO) {
        log.info("Adding member into ministerio...");
        if (verifyIfMembroIsOnMinisterioAlready(addMembroMinisterioDTO.getMinisterioId(), addMembroMinisterioDTO.getMembroId())) {
            throw new RuntimeException("Can't add member into ministerio: he's already there");
        }

        MembroMinisterio membroMinisterio = MembroMinisterioMapper.INSTANCE.toModel(addMembroMinisterioDTO);
        MembroMinisterio membroMinisterioInserted = membroMinisterioRepository.insert(membroMinisterio);
        log.info("Member id " + membroMinisterioInserted.getMembroId() + " added into " + membroMinisterio.getMinisterioId());
        return membroMinisterioInserted;
    }

    private Boolean verifyIfMembroIsOnMinisterioAlready(
            String idMinisterio, String idMembro) {
        return membroMinisterioRepository.findByMinisterioIdAndMembroId(idMinisterio, idMembro)
                                         .isPresent();
    }

    @Override
    public void removerMembroMinisterio(
            String idMembroMinisterio) {
        log.info("Removing membro from ministerio...");
        Optional<MembroMinisterio> optional = membroMinisterioRepository.findById(idMembroMinisterio);
        if (optional.isEmpty()) {
            log.info("Error removing member from ministerios...");
            throw new RuntimeException("Can't find member ministerio by this id");
        }
        MembroMinisterio membroMinisterio = optional.get();
        this.membroMinisterioRepository.delete(membroMinisterio);
        log.info("Member removed!");
    }

    @Override
    public EscalaMinisterioResponse createEscalaMinisterio(
            Eventos evento, String ministerioId) {
        log.info("Creating escala for ministerio " + ministerioId + "...");
        EscalaMinisterioDTO dto = new EscalaMinisterioDTO(ministerioId, evento.getId(), evento.getDate());
        EscalaMinisterio escalaMinisterio = this.escalaMinisterioRepository.save(EscalaMinisterioMapper.INSTANCE.toModel(dto));
        EscalaMinisterio escalaMinisterioWithConvites = getEscalaMinisterioWithConvitesMembro(escalaMinisterio);
        EscalaMinisterio escalaMinisterioWithConvitesSaved = this.escalaMinisterioRepository.save(escalaMinisterioWithConvites);
        log.info("Escala " + escalaMinisterio.getId() + " created for event " + evento.getTitulo() + " to ministerio " + ministerioId);
        return EscalaMinisterioMapper.INSTANCE.toResponse(escalaMinisterioWithConvitesSaved);
    }

    private EscalaMinisterio getEscalaMinisterioWithConvitesMembro(
            EscalaMinisterio escalaMinisterio) {
        log.info("Getting escala ministerio with convites for all membros...");
        List<MembroMinisterio> membroMinisterioList = membroMinisterioRepository.findByMinisterioId(escalaMinisterio.getMinisterioId());
        List<String> idsMembrosFromMinisterio = membroMinisterioList.stream()
                                                                    .map(item -> item.getId())
                                                                    .toList();
        escalaMinisterio.setMembrosMinisterioConvidadosIds(idsMembrosFromMinisterio);
        return escalaMinisterio;
    }


}
