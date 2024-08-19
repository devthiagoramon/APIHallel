package br.api.hallel.moduloAPI.service.ministerio;

import br.api.hallel.moduloAPI.dto.v1.*;
import br.api.hallel.moduloAPI.mapper.ministerio.*;
import br.api.hallel.moduloAPI.model.*;
import br.api.hallel.moduloAPI.payload.resposta.MembroResponse;
import br.api.hallel.moduloAPI.repository.*;
import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public FuncaoMinisterio createFuncaoMinisterio(
            FuncaoMinisterioDTO funcaoMinisterioDTO) {
        log.info("Creating função ministerio...");
        FuncaoMinisterio funcaoMinisterio = FuncaoMinisterioMapper.INSTANCE.toModel(funcaoMinisterioDTO);
        return this.funcaoMinisterioRepository.insert(funcaoMinisterio);
    }

    @Override
    public List<FuncaoMinisterio> listFuncaoOfMinisterio(
            String idMinisterio) {
        log.info("Listing funções ministerio from ministerio " + idMinisterio + "...");
        return this.funcaoMinisterioRepository.findAllByMinisterioId(idMinisterio);
    }

    @Override
    public FuncaoMinisterio listFuncaoMinisterioById(
            String idFuncaoMinisterio) {
        log.info("Listing funcao ministerio by id " + idFuncaoMinisterio + "...");
        Optional<FuncaoMinisterio> optional = this.funcaoMinisterioRepository.findById(idFuncaoMinisterio);
        if (optional.isEmpty()) {
            log.info("Error listing função ministerio: Can't find this id");
            throw new RuntimeException("Can't find função ministerio by this id");
        }
        return optional.get();
    }

    @Override
    public FuncaoMinisterio editFuncaoMinisterio(
            String idFuncaoMinisterio,
            FuncaoMinisterioDTO funcaoMinisterioDTO) {
        FuncaoMinisterio funcaoMinisterio = listFuncaoMinisterioById(idFuncaoMinisterio);
        log.info("Editing função ministerio " + funcaoMinisterio.getNome() + "...");
        FuncaoMinisterio funcaoMinisterioNew = FuncaoMinisterioMapper.INSTANCE.toModel(funcaoMinisterioDTO);
        funcaoMinisterio.setNome(funcaoMinisterioNew.getNome());
        funcaoMinisterio.setCor(funcaoMinisterioNew.getCor());
        funcaoMinisterio.setDescricao(funcaoMinisterioNew.getDescricao());
        funcaoMinisterio.setIcone(funcaoMinisterioNew.getIcone());
        log.info("Função ministerio " + funcaoMinisterio.getId() + " edited!");
        return this.funcaoMinisterioRepository.save(funcaoMinisterio);
    }

    @Override
    public void deleteFuncaoMinisterio(String idFuncaoMinisterio) {
        log.info("Delete função ministerio " + idFuncaoMinisterio + "...");
        FuncaoMinisterio funcaoMinisterio = listFuncaoMinisterioById(idFuncaoMinisterio);
        this.funcaoMinisterioRepository.delete(funcaoMinisterio);
        log.info("Função ministerio " + funcaoMinisterio.getNome() + " deleted!");
    }

    @Override
    public MembroMinisterioWithInfosResponse defineFunctionsToMembroMinisterio(
            DefineFunctionsDTO defineFunctionsDTO) {
        log.info("Defining/Deleting functions of membro ministerio...");
        String idMembroMinisterio = defineFunctionsDTO.getIdMinisterioMembro();
        MembroMinisterio membroMinisterio = getMembroMinisterioById(idMembroMinisterio);
        if (membroMinisterio.getFuncaoMinisterioIds() == null) {
            membroMinisterio.setFuncaoMinisterioIds(new ArrayList<>());
        }
        defineFunctionsDTO.getIdsFuncaoMinisterioAdd()
                          .forEach(idAdds -> {
                              if (!membroMinisterio.getFuncaoMinisterioIds()
                                                   .contains(idAdds)) {
                                  membroMinisterio.getFuncaoMinisterioIds()
                                                  .add(idAdds);
                              }
                          });
        defineFunctionsDTO.getIdsFuncaoMinisterioRemove()
                          .forEach(idRemove -> {
                              if (membroMinisterio.getFuncaoMinisterioIds()
                                                  .contains(idRemove)) {
                                  membroMinisterio.getFuncaoMinisterioIds()
                                                  .removeIf(item -> item.equals(idRemove));
                              }
                          });
        membroMinisterioRepository.save(membroMinisterio);
        return listMembroMinisterioById(membroMinisterio.getId());
    }

    @NotNull
    private MembroMinisterio getMembroMinisterioById(
            String idMembroMinisterio) {
        Optional<MembroMinisterio> optional = membroMinisterioRepository.findById(idMembroMinisterio);

        if (optional.isEmpty()) {
            throw new RuntimeException("Can't find membroMinisterio by this id");
        }
        return optional.get();
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
    public MembroMinisterioWithInfosResponse listMembroMinisterioById(
            String idMembroMinisterio) {
        Optional<MembroMinisterioWithInfosResponse> membroMinisterioWithInfosResponse = membroMinisterioRepository.findWithInfosById(idMembroMinisterio);
        if (membroMinisterioWithInfosResponse.isEmpty()) {
            throw new RuntimeException("Can't find user ministerio by this id");
        }
        return membroMinisterioWithInfosResponse.get();
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
    public StatusParticipacaoEscalaMinisterio getStatusParticipacaoEscala(
            String idMembroMinisterio, String idEscalaMinisterio) {
        log.info("Get status of membro " + idMembroMinisterio + " about the scale " + idEscalaMinisterio);
        EscalaMinisterio escalaMinisterio = getEscalaMinisterioById(idEscalaMinisterio);
        if (escalaMinisterio.getMembrosMinisterioConfimadoIds()
                            .contains(idMembroMinisterio)) {
            return StatusParticipacaoEscalaMinisterio.CONFIRMADO;
        } else if (escalaMinisterio.getMembrosMinisterioConvidadosIds()
                                   .contains(idMembroMinisterio)) {
            return StatusParticipacaoEscalaMinisterio.CONVIDADO;
        } else if (escalaMinisterio.getMembrosMinisterioNaoConfirmadoIds()
                                   .contains(idMembroMinisterio)) {
            return StatusParticipacaoEscalaMinisterio.RECUSADO;
        }
        throw new RuntimeException("Can't found this membro by id");
    }

    @Override
    public Boolean confirmarParticipacaoEscala(
            String idMembroMinisterio, String idEscalaMinisterio) {
        log.info("Confirming participantion of membro " + idMembroMinisterio + " into " + idEscalaMinisterio);
        EscalaMinisterio escalaMinisterio = getEscalaMinisterioById(idEscalaMinisterio);

        if (escalaMinisterio.getMembrosMinisterioConvidadosIds()
                            .contains(idMembroMinisterio)) {
            escalaMinisterio.getMembrosMinisterioConvidadosIds()
                            .removeIf(item -> item.equals(idMembroMinisterio));
        } else {
            throw new RuntimeException("Member hasn't been invited!");
        }
        if (escalaMinisterio.getMembrosMinisterioConfimadoIds() == null) {
            escalaMinisterio.setMembrosMinisterioConfimadoIds(new ArrayList<>());
        }
        escalaMinisterio.getMembrosMinisterioConfimadoIds()
                        .add(idMembroMinisterio);
        return true;
    }

    @Override
    public Boolean recusarParticipacaoEscala(
            NaoConfirmarEscalaDTO naoConfirmarEscalaDTO) {
        log.info("Recusing participantion of membro " + naoConfirmarEscalaDTO.getIdMembroMinisterio() + " into " + naoConfirmarEscalaDTO.getIdEscalaMinisterio());
        EscalaMinisterio escalaMinisterio = getEscalaMinisterioById(naoConfirmarEscalaDTO.getIdEscalaMinisterio());

        if (escalaMinisterio.getMembrosMinisterioConvidadosIds()
                            .contains(naoConfirmarEscalaDTO.getIdMembroMinisterio())) {
            escalaMinisterio.getMembrosMinisterioConvidadosIds()
                            .removeIf(item -> item.equals(naoConfirmarEscalaDTO.getIdMembroMinisterio()));
        } else {
            throw new RuntimeException("Member hasn't been invited!");
        }

        log.info("Inserting NaoConfirmadoEscalaMinisterio into DB");
        NaoConfirmadoEscalaMinisterio naoConfirmadoEscalaMinisterio = naoConfirmadoEscalaMinisterioRepository
                .insert(NaoConfirmadoEscalaMinisterioMapper
                        .INSTANCE
                        .toModel(naoConfirmarEscalaDTO));
        log.info("NaoConfirmadoEscalaMinisterio inserted with id " + naoConfirmadoEscalaMinisterio.getIdEscalaMinisterio());

        if (escalaMinisterio.getMembrosMinisterioNaoConfirmadoIds() == null) {
            escalaMinisterio.setMembrosMinisterioNaoConfirmadoIds(new ArrayList<>());
        }

        escalaMinisterio.getMembrosMinisterioNaoConfirmadoIds()
                        .add(naoConfirmadoEscalaMinisterio.getIdMembroMinisterio());
        return true;
    }

    private EscalaMinisterio getEscalaMinisterioById(
            String idEscalaMinisterio) {
        Optional<EscalaMinisterio> optional = escalaMinisterioRepository.findById(idEscalaMinisterio);
        if (optional.isEmpty()) {
            throw new RuntimeException("Can't get the ministerio by this id");
        }
        return optional.get();
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

    @Override
    public List<EscalaMinisterioWithEventoInfoResponse> listEscalaMinisterio() {
        return null;
    }

    @Override
    public List<EscalaMinisterioWithEventoInfoResponse> listEscalaMinisterioRangeDate(
            String start, String end) {
        return null;
    }

    @Override
    public EscalaMinisterioResponseWithInfos listEscalaMinisterioByIdWithInfos(
            String idEscalaMinisterio) {
        return null;
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
