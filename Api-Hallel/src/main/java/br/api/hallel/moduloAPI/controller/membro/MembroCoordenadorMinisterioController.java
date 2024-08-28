package br.api.hallel.moduloAPI.controller.membro;

import br.api.hallel.moduloAPI.dto.v1.ministerio.*;
import br.api.hallel.moduloAPI.model.FuncaoMinisterio;
import br.api.hallel.moduloAPI.model.MembroMinisterio;
import br.api.hallel.moduloAPI.model.NaoConfirmadoEscalaMinisterio;
import br.api.hallel.moduloAPI.payload.resposta.MembroResponse;
import br.api.hallel.moduloAPI.service.ministerio.MinisterioService;
import com.nimbusds.oauth2.sdk.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RequestMapping("/api/membros/ministerio/coordenador")
@RestController
@Tag(name = "Coordenador ministerio",
     description = "Endpoints para os coordenadores de um ministério")
public class MembroCoordenadorMinisterioController {

    @Autowired
    private MinisterioService ministerioService;


    /**
     * Parte de escala ministério (COORDENADOR)
     */

    @PostMapping("/escala/resucarParticipacao")
    @Operation(
            summary = "Criar uma recusa de participação por um membro do ministerio",
            description = "Criará uma recusa de participação de um membro em uma escala de um ministerio, isto feito pelo coordenador")
    public ResponseEntity<NaoConfirmadoEscalaMinisterio> criarRecusaParticipacao(
            @RequestBody
            NaoConfirmarEscalaDTO naoConfirmarEscalaDTO) {
        return ResponseEntity.ok()
                             .body(this.ministerioService.createNaoConfirmadoEscalaMinisterio(naoConfirmarEscalaDTO));
    }

    @PutMapping(
            "/escala/recusarParticipacao/{idRecusaParticipacao}/edit")
    @Operation(
            summary = "Editar uma recusa de participação por um membro de um ministerio")
    public ResponseEntity<NaoConfirmadoEscalaMinisterio> editarRecusaParticipacao(
            @PathVariable("idRecusaParticipacao")
            String idRecusaParticipacao, @RequestBody
            NaoConfirmarEscalaDTO naoConfirmarEscalaDTO) {
        return ResponseEntity.ok()
                             .body(this.ministerioService.editNaoConfirmadoEscalaMinisterio(idRecusaParticipacao, naoConfirmarEscalaDTO));
    }

    @PatchMapping("/escala/confirmarMembros/{idEscala}")
    @Operation(
            summary = "Confirmar membros em uma escala a partir de seus ids")
    public ResponseEntity<EscalaMinisterioResponse> confirmarMembrosInEscala(
            @PathVariable("idEscala") String idEscala,
            @RequestBody List<String> idsMembrosMinisterio) {
        return ResponseEntity.ok()
                             .body(this.ministerioService
                                     .alterarEscalaConfirmandoMembroMinisterio(idEscala, idsMembrosMinisterio));
    }

    @PatchMapping("/escala/recusarMembros/{idEscala}")
    @Operation(
            summary = "Ausentar membros em uma escala")
    public ResponseEntity<EscalaMinisterioResponse> ausentarMembrosInEscala(
            @PathVariable("idEscala") String idEscala,
            @RequestBody
            List<NaoConfirmadoEscalaDTOAdm> naoConfirmadoEscalaDTO) {
        return ResponseEntity.ok()
                             .body(this.ministerioService
                                     .alterarEscalaNaoConfirmandoMembroMinisterio(idEscala, naoConfirmadoEscalaDTO));
    }

    @GetMapping("/escala/{idEscalaMinisterio}")
    @Operation(
            summary = "Listar as informações de uma escala de um ministerio pelo seu id")
    public ResponseEntity<EscalaMinisterioResponseWithInfos> listEscalaMinisterioById(
            @PathVariable("idEscalaMinisterio")
            String idEscalaMinisterio) {
        return ResponseEntity.ok(this.ministerioService.listEscalaMinisterioByIdWithInfos(idEscalaMinisterio));
    }

    @GetMapping("/escala/{idEscalaMinisterio}/ausencias")
    @Operation(
            summary = "Listar as ausencias e seus motivos a partir do id de um escala")
    public ResponseEntity<List<NaoConfirmadoEscalaMinisterioWithInfos>> listAusenciaEscalaMinisterioById(
            @PathVariable("idEscalaMinisterio")
            String idEscalaMinisterio) {
        return ResponseEntity.ok(this.ministerioService
                .listMotivosAusenciaMembroEventoByIdEscalasMinisterio(idEscalaMinisterio));
    }

    @GetMapping("/escala/{idMinisterio}/date")
    @Operation(
            summary = "Listar as escalas de um ministerio em um intervalo de tempo")
    public ResponseEntity<List<EscalaMinisterioWithEventoInfoResponse>> listEscalaMinisterioByIdMinisterio(
            @PathVariable("idMinisterio") String idMinisterio,
            @RequestParam("dateStart")
            Date dateStart, @RequestParam("dateEnd") Date dateEnd) {
        return ResponseEntity.ok(this.ministerioService
                .listEscalaMinisterioRangeDateByMinisterioId(idMinisterio, dateStart, dateEnd));
    }


    /**
     * @param idMinisterio
     * @return List {@link EventosShortResponse}
     * @apiNote {@summary Listar os eventos que o ministerio participa}
     */
    @Operation(summary = "Listar eventos que o ministerio participa")
    @GetMapping("/eventos/{idMinisterio}")
    public List<EventosShortResponse> listarEventosMinisterioParticipa(
            @PathVariable String idMinisterio) {
        return this.ministerioService.listEventosThatMinisterioIsIn(idMinisterio);
    }

    /**
     * Parte de membros de um ministerio (COORDENADOR)
     *
     * @return {@link MembroResponse}, {@link MembroMinisterioWithInfosResponse}
     */

    @Operation(
            summary = "Listar membros adicionaveis em um ministerio")
    @GetMapping("/membroMinisterio/disponivel/{idMinisterio}")
    public ResponseEntity<List<MembroResponse>> listMembroToAddIntoMinisterio(
            @PathVariable("idMinisterio") String idMinisterio) {
        return ResponseEntity.ok()
                             .body(this.ministerioService.listMembrosToAddIntoThisMinisterio(idMinisterio));
    }

    @Operation(summary = "Listar membros de um ministerio")
    @GetMapping("/membroMinisterio/list/{idMinisterio}")
    public ResponseEntity<List<MembroMinisterioWithInfosResponse>> listMembrosOfMinisterio(
            @PathVariable("idMinisterio") String idMinisterio) {
        return ResponseEntity.ok()
                             .body(this.ministerioService.listMembrosFromMinisterio(idMinisterio));
    }

    @Operation(
            summary = "Listar um membro de um ministerio pelo seu id")
    @GetMapping("/membroMinisterio/{idMembroMinisterio}")
    public ResponseEntity<MembroMinisterioWithInfosResponse> listMembrosOfMinisterioById(
            @PathVariable("idMembroMinisterio")
            String idMembroMinisterio) {
        return ResponseEntity.ok()
                             .body(this.ministerioService.listMembroMinisterioById(idMembroMinisterio));
    }

    @Operation(
            summary = "Adicionar um membro em um ministerio"
    )
    @PostMapping("/membroMinisterio")
    public ResponseEntity<MembroMinisterio> adicionarMembroMinisterio(
            @RequestBody
            AddMembroMinisterioDTO addMembroMinisterioDTO) {
        return ResponseEntity.ok()
                             .body(this.ministerioService.addMembroMinisterio(addMembroMinisterioDTO));
    }

    @Operation(
            summary = "Remover um membro de um ministerio"
    )
    @DeleteMapping("/membroMinisterio/{idMembroMinisterio}")
    public ResponseEntity<?> removerMembroMinisterio(
            @PathVariable("idMembroMinisterio")
            String idMembroMinisterio) {
        this.ministerioService.removerMembroMinisterio(idMembroMinisterio);
        return ResponseEntity.noContent().build();
    }

    /**
     * Parte de funções de um ministerio
     *
     * @return {@link FuncaoMinisterio}, {@link MembroMinisterioWithInfosResponse}
     */

    @Operation(summary = "Adicionar função ministerio")
    @PostMapping("/funcao")
    public ResponseEntity<FuncaoMinisterio> createFuncaoMinisterio(
            @RequestBody
            FuncaoMinisterioDTO funcaoMinisterioDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(this.ministerioService.createFuncaoMinisterio(funcaoMinisterioDTO));
    }

    @Operation(summary = "Listar funções de um ministerio")
    @GetMapping("/funcao/ministerio/{idMinisterio}")
    public ResponseEntity<List<FuncaoMinisterio>> listFuncoesMinisterioByIdMinisterio(
            @PathVariable("idMinisterio") String ministerioId) {
        return ResponseEntity.status(HttpStatus.OK)
                             .body(this.ministerioService.listFuncaoOfMinisterio(ministerioId));
    }

    @Operation(summary = "Listar uma função ministerio pelo id")
    @GetMapping("/funcao/{idFuncaoMinisterio}")
    public ResponseEntity<FuncaoMinisterio> lsitFuncaoById(
            @PathVariable("idFuncaoMinisterio")
            String idFuncaoMinisterio) {
        return ResponseEntity.status(HttpStatus.OK)
                             .body(this.ministerioService.listFuncaoMinisterioById(idFuncaoMinisterio));
    }

    @Operation(summary = "Editar uma função ministerio")
    @PutMapping("/funcao/{idFuncaoMinisterio}")
    public ResponseEntity<FuncaoMinisterio> editFuncaoMinisterio(
            @PathVariable("idFuncaoMinisterio")
            String idFuncaoMinisterio,
            @RequestBody FuncaoMinisterioDTO funcaoMinisterioDTO) {
        return ResponseEntity.status(HttpStatus.OK)
                             .body(this.ministerioService.editFuncaoMinisterio(idFuncaoMinisterio, funcaoMinisterioDTO));
    }

    @Operation(summary = "Deletar uma função ministerio")
    @DeleteMapping("/funcao/{idFuncaoMinisterio}")
    public ResponseEntity<?> deleteFuncaoMinisterio(
            @PathVariable("idFuncaoMinisterio")
            String idFuncaoMinisterio) {
        this.ministerioService.deleteFuncaoMinisterio(idFuncaoMinisterio);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Operation(
            summary = "Definir uma função a um membro do ministerio",
            description = "Rota para definir um função do ministerio a um membro ministerio")
    @PatchMapping("/funcao/membroMinisterio")
    public ResponseEntity<MembroMinisterioWithInfosResponse> defineFuncaoMinisterioToMembroMinisterio(
            @RequestBody
            DefineFunctionsDTO defineFunctionsDTO) {
        return ResponseEntity.ok()
                             .body(this.ministerioService.defineFunctionsToMembroMinisterio(defineFunctionsDTO));
    }


}
