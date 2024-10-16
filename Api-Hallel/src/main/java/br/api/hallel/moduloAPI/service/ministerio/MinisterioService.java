package br.api.hallel.moduloAPI.service.ministerio;

import br.api.hallel.moduloAPI.dto.v1.ministerio.*;
import br.api.hallel.moduloAPI.mapper.ministerio.*;
import br.api.hallel.moduloAPI.model.*;
import br.api.hallel.moduloAPI.payload.resposta.MembroResponse;
import br.api.hallel.moduloAPI.repository.*;
import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
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
    private final EventosRepository eventosRepository;

    ModelMapper modelMapper;

    public MinisterioService(
            MinisterioRepository ministerioRepository,
            FuncaoMinisterioRepository funcaoMinisterioRepository,
            MembroMinisterioRepository membroMinisterioRepository,
            EscalaMinisterioRepository escalaMinisterioRepository,
            NaoConfirmadoEscalaMinisterioRepository naoConfirmadoEscalaMinisterioRepository,
            MembroRepository membroRepository,
            EventosRepository eventosRepository) {
        this.ministerioRepository = ministerioRepository;
        this.funcaoMinisterioRepository = funcaoMinisterioRepository;
        this.membroMinisterioRepository = membroMinisterioRepository;
        this.escalaMinisterioRepository = escalaMinisterioRepository;
        this.naoConfirmadoEscalaMinisterioRepository = naoConfirmadoEscalaMinisterioRepository;
        this.membroRepository = membroRepository;
        this.eventosRepository = eventosRepository;
        this.modelMapper = new ModelMapper();
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
            MinisterioDTO coordMinisterioDTO) {
        log.info("Creating ministerio...");
        Ministerio ministerio = this.ministerioRepository.insert(MinisterioMapper.toModelMinisterio(coordMinisterioDTO));
        if (coordMinisterioDTO.getCoordenadorId() != null) {
            addMembroMinisterio(new AddMembroMinisterioDTO(coordMinisterioDTO.getCoordenadorId(), ministerio.getId(), null));
        }
        if (coordMinisterioDTO.getViceCoordenadorId() != null) {
            addMembroMinisterio(new AddMembroMinisterioDTO(coordMinisterioDTO.getViceCoordenadorId(), ministerio.getId(), null));
        }
        log.info("Ministerio id " + ministerio.getId() + " created!");
        return MinisterioMapper.toMinisterioResponse(ministerio);
    }

    @Override
    public MinisterioResponse editMinisterio(String idMinisterio,
                                             MinisterioDTO editCoordMinisterioDTO) {
        log.info("Editing ministerio...");
        Ministerio ministerio = getMinisterioById(idMinisterio);
        ministerio.setNome(editCoordMinisterioDTO.getNome());
        Ministerio ministerioCoordsEdited = editCoordsMembroOfMembroMinisterio(ministerio, editCoordMinisterioDTO);

        Ministerio ministerioUpdated = this.ministerioRepository.save(ministerioCoordsEdited);
        log.info("Ministerio id " + ministerio.getId() + " edited!");
        return MinisterioMapper.toMinisterioResponse(ministerioUpdated);
    }

    private Ministerio editCoordsMembroOfMembroMinisterio(
            Ministerio ministerio,
            MinisterioDTO editCoordMinisterioDTO) {

        // Verificação se o coordenador e vicecoordenador são membros ministerio, caso não forem adicionar eles no ministerio
        List<MembroMinisterio> listByCoordId = membroMinisterioRepository.findByMembroId(ministerio.getCoordenadorId());
        List<MembroMinisterio> listByViceCoordId = membroMinisterioRepository.findByMembroId(ministerio.getViceCoordenadorId());

        MembroMinisterio membroMinisterioCoord = null;
        MembroMinisterio membroMinisterioVice = null;

        for (MembroMinisterio coord : listByCoordId) {
            if (coord.getMinisterioId().equals(ministerio.getId())) {
                membroMinisterioCoord = coord;
            }
        }
        for (MembroMinisterio viceCoord : listByViceCoordId) {
            if (viceCoord.getMinisterioId()
                         .equals(ministerio.getId())) {
                membroMinisterioVice = viceCoord;
            }
        }

        // Se houve alteração de coordenador, alterar o id do ministerio e remover o antigo do ministerio
        if (editCoordMinisterioDTO.getCoordenadorId() != null && !editCoordMinisterioDTO.getCoordenadorId()
                                                                                        .equals(ministerio.getCoordenadorId())) {
            ministerio.setCoordenadorId(editCoordMinisterioDTO.getCoordenadorId());
            removerMembroMinisterio(membroMinisterioCoord);
            if (!verifyIfMembroIsOnMinisterioAlready(ministerio.getId(), editCoordMinisterioDTO.getCoordenadorId())) {
                addMembroMinisterio(new AddMembroMinisterioDTO(editCoordMinisterioDTO.getCoordenadorId(), ministerio.getId(), null));
            }
        }
        // Se houve alteração de vice, alterar o id do ministerio e remover o antigo do ministerio
        if (editCoordMinisterioDTO.getViceCoordenadorId() != null && !editCoordMinisterioDTO.getViceCoordenadorId()
                                                                                            .equals(ministerio.getViceCoordenadorId())) {
            ministerio.setViceCoordenadorId(editCoordMinisterioDTO.getViceCoordenadorId());
            assert membroMinisterioVice != null;
            removerMembroMinisterio(membroMinisterioVice);
            if (!verifyIfMembroIsOnMinisterioAlready(ministerio.getId(), editCoordMinisterioDTO.getCoordenadorId())) {
                addMembroMinisterio(new AddMembroMinisterioDTO(editCoordMinisterioDTO.getViceCoordenadorId(), ministerio.getId(), null));
            }
        }

        return ministerio;
    }

    private Ministerio editCoordsMembroOfMembroMinisterio(
            Ministerio ministerio,
            EditCoordMinisterioDTO editCoordMinisterioDTO) {

        // Verificação se o coordenador e vicecoordenador são membros ministerio, caso não forem adicionar eles no ministerio
        List<MembroMinisterio> listByCoordId = membroMinisterioRepository.findByMembroId(ministerio.getCoordenadorId());
        List<MembroMinisterio> listByViceCoordId = membroMinisterioRepository.findByMembroId(ministerio.getViceCoordenadorId());

        MembroMinisterio membroMinisterioCoord = null;
        MembroMinisterio membroMinisterioVice = null;

        for (MembroMinisterio coord : listByCoordId) {
            if (coord.getMinisterioId().equals(ministerio.getId())) {
                membroMinisterioCoord = coord;
            }
        }
        for (MembroMinisterio viceCoord : listByViceCoordId) {
            if (viceCoord.getMinisterioId()
                         .equals(ministerio.getId())) {
                membroMinisterioVice = viceCoord;
            }
        }

        // Se houve alteração de coordenador, alterar o id do ministerio e remover o antigo do ministerio
        if (editCoordMinisterioDTO.getCoordenadorId() != null && !editCoordMinisterioDTO.getCoordenadorId()
                                                                                        .equals(ministerio.getCoordenadorId())) {
            ministerio.setCoordenadorId(editCoordMinisterioDTO.getCoordenadorId());
            removerMembroMinisterio(membroMinisterioCoord);
            if (!verifyIfMembroIsOnMinisterioAlready(ministerio.getId(), editCoordMinisterioDTO.getCoordenadorId())) {
                addMembroMinisterio(new AddMembroMinisterioDTO(editCoordMinisterioDTO.getCoordenadorId(), ministerio.getId(), null));
            }
        }
        // Se houve alteração de vice, alterar o id do ministerio e remover o antigo do ministerio
        if (editCoordMinisterioDTO.getViceCoordenadorId() != null && !editCoordMinisterioDTO.getViceCoordenadorId()
                                                                                            .equals(ministerio.getViceCoordenadorId())) {
            ministerio.setViceCoordenadorId(editCoordMinisterioDTO.getViceCoordenadorId());
            removerMembroMinisterio(membroMinisterioVice);
            if (!verifyIfMembroIsOnMinisterioAlready(ministerio.getId(), editCoordMinisterioDTO.getCoordenadorId())) {
                addMembroMinisterio(new AddMembroMinisterioDTO(editCoordMinisterioDTO.getViceCoordenadorId(), ministerio.getId(), null));
            }
        }

        return ministerio;
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
        return MinisterioMapper.toMinisterioResponseList(this.ministerioRepository.findAll());
    }

    @Override
    public List<MinisterioPublicResponse> listMinisterioPublic() {
        return MinisterioMapper.toMinisterioPublicResponseList(this.ministerioRepository.findAll());
    }

    @Override
    public MinisterioWithCoordsResponse listMinisterioWithCoordById(
            String idMinisterio) {
        return this.ministerioRepository.findAllWithCoordsById(idMinisterio);
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
        return MinisterioMapper.toMinisterioResponse(getMinisterioById(idMinisterio));
    }

    @Override
    public MinisterioResponse alterarCoordenadoresInMinisterio(
            String idMinisterio,
            EditCoordMinisterioDTO editCoordMinisterioDTO) {
        log.info("Changing coord and vice-coord in ministerio " + idMinisterio + "...");
        Ministerio ministerio = getMinisterioById(idMinisterio);
        Ministerio ministerioCoordEdited = editCoordsMembroOfMembroMinisterio(ministerio, editCoordMinisterioDTO);
        Ministerio ministerioUpdated = this.ministerioRepository.save(ministerioCoordEdited);
        log.info("Coord and vice-coord changed in ministerio " + ministerioUpdated.getNome());
        return MinisterioMapper.toMinisterioResponse(ministerioUpdated);
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
        FuncaoMinisterio funcaoMinisterio = MinisterioMapper.toFuncaoMinisterio(funcaoMinisterioDTO);
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
        FuncaoMinisterio funcaoMinisterioNew = MinisterioMapper.toFuncaoMinisterio(funcaoMinisterioDTO);
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
        if (defineFunctionsDTO.getIdsFuncaoMinisterioAdd() != null) {
            defineFunctionsDTO.getIdsFuncaoMinisterioAdd()
                              .forEach(idAdds -> {
                                  if (!membroMinisterio.getFuncaoMinisterioIds()
                                                       .contains(idAdds)) {
                                      membroMinisterio.getFuncaoMinisterioIds()
                                                      .add(idAdds);
                                  }
                              });
        }
        if (defineFunctionsDTO.getIdsFuncaoMinisterioRemove() != null) {
            defineFunctionsDTO.getIdsFuncaoMinisterioRemove()
                              .forEach(idRemove -> {
                                  if (membroMinisterio.getFuncaoMinisterioIds()
                                                      .contains(idRemove)) {
                                      membroMinisterio.getFuncaoMinisterioIds()
                                                      .removeIf(item -> item.equals(idRemove));
                                  }
                              });
        }
        membroMinisterioRepository.save(membroMinisterio);
        return listMembroMinisterioById(idMembroMinisterio);
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
        Optional<MembroMinisterioWithInfosResponse> membroMinisterioWithInfosResponse = membroMinisterioRepository.findWithInfosId(idMembroMinisterio);
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

        MembroMinisterio membroMinisterio = MinisterioMapper.toMembroMinisterio(addMembroMinisterioDTO);
        MembroMinisterio membroMinisterioInserted = membroMinisterioRepository.insert(membroMinisterio);
        log.info("Member id " + membroMinisterioInserted.getMembroId() + " added into " + membroMinisterio.getMinisterioId());
        return membroMinisterioInserted;
    }

    @Override
    public void removerMembroMinisterio(String idMembroMinisterio) {
        Optional<MembroMinisterio> optional = membroMinisterioRepository.findById(idMembroMinisterio);
        if (optional.isEmpty()) {
            throw new RuntimeException("Can't find membroMinisterio by this id");
        }
        MembroMinisterio membroMinisterio = optional.get();
        membroMinisterioRepository.delete(membroMinisterio);
    }

    private Boolean verifyIfMembroIsOnMinisterioAlready(
            String idMinisterio, String idMembro) {
        return membroMinisterioRepository.findByMinisterioIdAndMembroId(idMinisterio, idMembro)
                                         .isPresent();
    }

    @Override
    public void removerMembroMinisterio(
            MembroMinisterio membroMinisterio) {
        log.info("Removing membro from ministerio...");
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


        NaoConfirmadoEscalaMinisterio naoConfirmadoEscalaMinisterio = createNaoConfirmadoEscalaMinisterio(naoConfirmarEscalaDTO);
        log.info("NaoConfirmadoEscalaMinisterio inserted with id " + naoConfirmadoEscalaMinisterio.getIdEscalaMinisterio());
        if (escalaMinisterio.getMembrosMinisterioNaoConfirmadoIds() == null) {
            escalaMinisterio.setMembrosMinisterioNaoConfirmadoIds(new ArrayList<>());
        }

        escalaMinisterio.getMembrosMinisterioNaoConfirmadoIds()
                        .add(naoConfirmadoEscalaMinisterio.getId());
        return true;
    }

    @Override
    public List<MinisterioResponse> listMinisterioThatMembroParticipateByMembroId(
            String idMembro) {
        log.info("List ministerios that membro " + idMembro + " participate");
        return this.membroMinisterioRepository.findMinisterioByMembroId(idMembro);
    }

    @Override
    public StatusMembroMinisterio listStatusMembroMinisterioByMinisterioIdAndMembroId(
            String idMinisterio, String membroId) {
        Optional<Ministerio> optional = this.ministerioRepository.findById(idMinisterio);
        if (optional.isEmpty()) {
            throw new RuntimeException("Ministerio " + idMinisterio + " not found");
        }
        Ministerio ministerio = optional.get();
        if (ministerio.getCoordenadorId().equals(membroId)) {
            return StatusMembroMinisterio.COORDENADOR;
        } else if (ministerio.getViceCoordenadorId()
                             .equals(membroId)) {
            return StatusMembroMinisterio.VICE_COORDENADOR;
        } else {
            return StatusMembroMinisterio.MEMBRO;
        }
    }

    @Override
    public NaoConfirmadoEscalaMinisterio createNaoConfirmadoEscalaMinisterio(
            NaoConfirmarEscalaDTO naoConfirmarEscalaDTO) {
        log.info("Creating nao confirmado escala... ");
        return naoConfirmadoEscalaMinisterioRepository
                .insert(MinisterioMapper.toNaoConfirmadoEscalaMinisterio(naoConfirmarEscalaDTO));
    }

    @Override
    public NaoConfirmadoEscalaMinisterio editNaoConfirmadoEscalaMinisterio(
            String idNaoConfirmadoEscala,
            NaoConfirmarEscalaDTO naoConfirmarEscalaDTO) {
        NaoConfirmadoEscalaMinisterio naoConfirmadoEscalaMinisterio =
                listNaoConfirmadoEscalaMinisterioById(idNaoConfirmadoEscala);
        NaoConfirmadoEscalaMinisterio naoConfirmadoEscalaMinisterioNew = MinisterioMapper.toNaoConfirmadoEscalaMinisterio(naoConfirmarEscalaDTO);
        naoConfirmadoEscalaMinisterio.setMotivo(naoConfirmadoEscalaMinisterioNew.getMotivo());
        return this.naoConfirmadoEscalaMinisterioRepository.save(naoConfirmadoEscalaMinisterio);
    }

    @Override
    public List<NaoConfirmadoEscalaMinisterio> listNaoConfirmadoEscalaMinisterioByIdMembroMinisterio(
            String idMemmbroMinisterio) {
        log.info("Listing nao confirmado escala ministerio by membro ministerio id: " + idMemmbroMinisterio);
        return this.naoConfirmadoEscalaMinisterioRepository.findAllByIdMembroMinisterio(idMemmbroMinisterio);
    }

    @Override
    public NaoConfirmadoEscalaMinisterio listNaoConfirmadoEscalaMinisterioById(
            String idNaoConfirmadoEscalaMinisterio) {
        log.info("Listing nao confirmado escala ministerio by id " + idNaoConfirmadoEscalaMinisterio + "...");
        Optional<NaoConfirmadoEscalaMinisterio> optional = this.naoConfirmadoEscalaMinisterioRepository.findById(idNaoConfirmadoEscalaMinisterio);
        if (optional.isEmpty()) {
            throw new RuntimeException("Can't find nao confirmado escala ministerio by this id");
        }
        return optional.get();
    }

    @Override
    public void deleteNaoConfirmadoEscalaMinisterio(
            String idNaoConfirmadoEscalaMinisterio) {
        log.info("Deleting nao confirmado escala ministerio...");
        NaoConfirmadoEscalaMinisterio naoConfirmadoEscalaMinisterio =
                listNaoConfirmadoEscalaMinisterioById(idNaoConfirmadoEscalaMinisterio);
        this.naoConfirmadoEscalaMinisterioRepository.delete(naoConfirmadoEscalaMinisterio);
        log.info("Nao confirmado id {} deleted", naoConfirmadoEscalaMinisterio.getId());
    }

    @Override
    public List<EventosShortResponse> listEventosThatMinisterioIsIn(
            String ministerioId) {
        log.info("List eventos that ministerio {} is participating! ", ministerioId);
        return this.eventosRepository.findAllByMinisteriosAssociadosContains(ministerioId);
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
        EscalaMinisterioDTO dto = new EscalaMinisterioDTO();
        dto.setMinisterioId(ministerioId);
        dto.setDate(evento.getDate());
        dto.setEventoId(evento.getId());
        EscalaMinisterio escalaMinisterioToModel = MinisterioMapper.toEscalaMinisterio(dto);
        EscalaMinisterio escalaMinisterioWithConvites = getEscalaMinisterioWithConvitesMembro(escalaMinisterioToModel);
        EscalaMinisterio escalaMinisterioWithConvitesSaved = this.escalaMinisterioRepository.save(escalaMinisterioWithConvites);
        log.info("Escala " + escalaMinisterioWithConvitesSaved.getId() + " created for event " + evento.getTitulo() + " to ministerio " + ministerioId);
        return MinisterioMapper.toEscalaMinisterioResponse(escalaMinisterioWithConvitesSaved);
    }

    @Override
    public EscalaMinisterioResponse alterarEscalaConfirmandoMembroMinisterio(
            String idEscala, List<String> idsMembrosMinisterio) {
        log.info("Editing escala ministerio " + idEscala + " confirming members...");
        EscalaMinisterio escalaMinisterio = getEscalaMinisterioById(idEscala);

        if (escalaMinisterio.getMembrosMinisterioConfimadoIds() == null) {
            escalaMinisterio.setMembrosMinisterioConfimadoIds(new ArrayList<>());
        }

        idsMembrosMinisterio.forEach(idMembro -> {
            if (escalaMinisterio.getMembrosMinisterioConvidadosIds()
                                .contains(idMembro)) {
                escalaMinisterio.getMembrosMinisterioConvidadosIds()
                                .removeIf(item -> item.equals(idMembro));
                escalaMinisterio.getMembrosMinisterioConfimadoIds()
                                .add(idMembro);
            } else {
                log.info("Membro ministerio " + idMembro + " isn't invited!");
            }
        });

        EscalaMinisterio escalaMinisterioEdited = this.escalaMinisterioRepository.save(escalaMinisterio);
        return MinisterioMapper.toEscalaMinisterioResponse(escalaMinisterioEdited);
    }

    @Override
    public EscalaMinisterioResponse alterarEscalaNaoConfirmandoMembroMinisterio(
            String idEscala,
            List<NaoConfirmadoEscalaDTOAdm> naoConfirmadoEscalaDtoAdm) {
        log.info("Editing escala ministerio " + idEscala + " recusing members...");
        EscalaMinisterio escalaMinisterio = getEscalaMinisterioById(idEscala);

        if (escalaMinisterio.getMembrosMinisterioNaoConfirmadoIds() == null) {
            escalaMinisterio.setMembrosMinisterioNaoConfirmadoIds(new ArrayList<>());
        }

        naoConfirmadoEscalaDtoAdm.forEach(naoConfirmado -> {
            if (escalaMinisterio.getMembrosMinisterioConvidadosIds()
                                .contains(naoConfirmado.getIdMembroMinisterio())) {
                NaoConfirmadoEscalaMinisterio naoConfirmadoEscalaMinisterio =
                        createNaoConfirmadoEscalaMinisterio(
                                new NaoConfirmarEscalaDTO(
                                        naoConfirmado.getIdMembroMinisterio(),
                                        idEscala,
                                        naoConfirmado.getMotivo()));
                escalaMinisterio.getMembrosMinisterioNaoConfirmadoIds()
                                .add(naoConfirmadoEscalaMinisterio.getId());
            } else {
                log.info("Membro ministerio " + naoConfirmado.getIdMembroMinisterio() + " isn't invited!");
            }
        });

        EscalaMinisterio escalaMinisterioEdited = this.escalaMinisterioRepository.save(escalaMinisterio);
        return MinisterioMapper.toEscalaMinisterioResponse(escalaMinisterioEdited);
    }


    @Override
    public List<EscalaMinisterioWithEventoInfoResponse> listEscalaMinisterio() {
        log.info("Listing all the escala ministerio...");
        return this.escalaMinisterioRepository.findAllWithEventosInfos();
    }

    @Override
    public List<EscalaMinisterioWithEventoInfoResponse> listEscalaMinisterioMembroIdCanParticipate(
            String membroId, Date start, Date end) {
        return this.escalaMinisterioRepository.findAllWithEventosInfosCanParticipateByMembroId(membroId, start, end);
    }

    @Override
    public List<EscalaMinisterioWithEventoInfoResponse> listEscalaMinisterioConfirmedMembro(
            String membroId, Date start, Date end) {
        return this.escalaMinisterioRepository.findAllWithEventosInfosConfirmedByMembroId(membroId, start, end);
    }

    @Override
    public List<EscalaMinisterioWithEventoInfoResponse> listEscalaMinisterioRangeDate(
            Date start, Date end) {
        return this.escalaMinisterioRepository.findAllWithEventosInfosRangeDate(start, end);
    }

    @Override
    public List<EscalaMinisterioWithEventoInfoResponse> listEscalaMinisterioRangeDateByMinisterioId(
            String idMinisterio, Date start, Date end) {
        return this.escalaMinisterioRepository.findAllWithEventosInfosRangeDateByMinisterioId(idMinisterio, start, end);
    }

    @Override
    public EscalaMinisterioResponseWithInfos listEscalaMinisterioByIdWithInfos(
            String idEscalaMinisterio) {
        return this.escalaMinisterioRepository.findWithAllInfosById(idEscalaMinisterio);
    }

    @Override

    public List<NaoConfirmadoEscalaMinisterioWithInfos> listMotivosAusenciaMembroEventoByIdEscalasMinisterio(
            String idEscala) {
        return this.naoConfirmadoEscalaMinisterioRepository.findAllWithEscalaId(idEscala);
    }

    @Override
    public void deleteEscalasWithDeletingEvento(String idEvento) {
        log.info("Deleting escala when deleting evento " + idEvento + "...");
        Optional<EscalaMinisterio> optional = escalaMinisterioRepository.findByEventoId(idEvento);
        if (optional.isEmpty()) {
            throw new RuntimeException("Can't find the delete escala by id");
        }
        EscalaMinisterio escalaMinisterio = optional.get();


        if (escalaMinisterio.getMembrosMinisterioNaoConfirmadoIds() != null) {
            escalaMinisterio.getMembrosMinisterioNaoConfirmadoIds()
                            .forEach(this::deleteNaoConfirmadoEscalaMinisterio);
        }
        escalaMinisterioRepository.delete(escalaMinisterio);
        log.info("Escala " + escalaMinisterio.getId() + " date " + escalaMinisterio.getDate() + " deleted");
    }

    private EscalaMinisterio getEscalaMinisterioWithConvitesMembro(
            EscalaMinisterio escalaMinisterio) {
        log.info("Getting escala ministerio with convites for all membros...");
        EscalaMinisterio escalaMinisterioReturn = new EscalaMinisterio(escalaMinisterio.getId(),
                escalaMinisterio.getMinisterioId(),
                escalaMinisterio.getEventoId(),
                escalaMinisterio.getDate());
        List<MembroMinisterio> membroMinisterioList = membroMinisterioRepository.findByMinisterioId(escalaMinisterio.getMinisterioId());
        List<String> idsMembrosFromMinisterio = new ArrayList<>();
        membroMinisterioList.forEach(membroMinisterio -> {
            idsMembrosFromMinisterio.add(membroMinisterio.getMembroId());
        });
        escalaMinisterioReturn.setMembrosMinisterioConvidadosIds(idsMembrosFromMinisterio);
        return escalaMinisterioReturn;
    }


}
