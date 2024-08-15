package br.api.hallel.moduloAPI.service.ministerio;

import br.api.hallel.moduloAPI.dto.v1.EditCoordMinisterioDTO;
import br.api.hallel.moduloAPI.dto.v1.MinisterioDTO;
import br.api.hallel.moduloAPI.dto.v1.MinisterioResponse;
import br.api.hallel.moduloAPI.mapper.ministerio.MinisterioMapper;
import br.api.hallel.moduloAPI.model.Ministerio;
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

    public MinisterioService(
            MinisterioRepository ministerioRepository,
            FuncaoMinisterioRepository funcaoMinisterioRepository,
            MembroMinisterioRepository membroMinisterioRepository,
            EscalaMinisterioRepository escalaMinisterioRepository,
            NaoConfirmadoEscalaMinisterioRepository naoConfirmadoEscalaMinisterioRepository) {
        this.ministerioRepository = ministerioRepository;
        this.funcaoMinisterioRepository = funcaoMinisterioRepository;
        this.membroMinisterioRepository = membroMinisterioRepository;
        this.escalaMinisterioRepository = escalaMinisterioRepository;
        this.naoConfirmadoEscalaMinisterioRepository = naoConfirmadoEscalaMinisterioRepository;
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
    public MinisterioResponse listMinisterioById(
            String idMinisterio) {
        log.info("Listing ministerio " + idMinisterio + "...");
        return MinisterioMapper.INSTANCE.toResponse(getMinisterioById(idMinisterio));
    }

    @Override
    public MinisterioResponse alterarCoordenadoresInMinisterio(
            String idMinisterio,
            EditCoordMinisterioDTO editCoordMinisterioDTO) {
        log.info("Changing coord and vice-coord in ministerio "+ idMinisterio+ "...");
        Ministerio ministerio = getMinisterioById(idMinisterio);
        ministerio.setCoordenadorId(editCoordMinisterioDTO.getCoordenadorId());
        ministerio.setViceCoordenadorId(editCoordMinisterioDTO.getViceCoordenadorId());
        Ministerio ministerioUpdated = this.ministerioRepository.save(ministerio);
        log.info("Coord and vice-coord changed in ministerio "+ ministerioUpdated.getNome());
        return MinisterioMapper.INSTANCE.toResponse(ministerioUpdated);
    }


}
