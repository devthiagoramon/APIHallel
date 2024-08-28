package br.api.hallel.moduloAPI.controller.membro;

import br.api.hallel.moduloAPI.dto.v1.ministerio.EscalaMinisterioWithEventoInfoResponse;
import br.api.hallel.moduloAPI.dto.v1.ministerio.NaoConfirmarEscalaDTO;
import br.api.hallel.moduloAPI.dto.v1.ministerio.StatusParticipacaoEscalaMinisterio;
import br.api.hallel.moduloAPI.model.NaoConfirmadoEscalaMinisterio;
import br.api.hallel.moduloAPI.security.ministerio.TokenCoordenadorMinisterio;
import br.api.hallel.moduloAPI.service.ministerio.MinisterioService;
import com.nimbusds.oauth2.sdk.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RequestMapping("/api/membros/ministerio")
@RestController
@Tag(name = "Membro ministerio",
     description = "Endpoints para os membros do ministério")
public class MembroMinisterioController {

    @Autowired
    private MinisterioService ministerioService;

    @Autowired
    private TokenCoordenadorMinisterio tokenCoordenadorMinisterio;


    @GetMapping("/token")
    @Operation(summary = "Valida e gera um token para o coordenador")
    public String generateTokenCoordenador(
            @RequestParam(name = "ministerioId") String ministerioId,
            @RequestParam(name = "membroId") String membroId) {
        if (!ministerioService.validateCoordenadorInMinisterio(ministerioId, membroId)) {
            throw new RuntimeException("Can't generate the token for coordenador, invalid ministerio or membro id");
        }

        return tokenCoordenadorMinisterio.generateToken(ministerioId, membroId);
    }

    @GetMapping("/status/{idMembroMinisterio}/{idEscalaMinisterio}")
    @Operation(summary = "Pegar o status do membro em uma escala")
    public ResponseEntity<StatusParticipacaoEscalaMinisterio>
    statusParticipacaoEscalaMinisterio
            (@PathVariable("idMembroMinisterio")
             String idMembroMinisterio,
             @PathVariable("idEscalaMinisterio")
             String idEscalaMinisterio) {
        return ResponseEntity.ok()
                             .body(this.ministerioService.getStatusParticipacaoEscala(idMembroMinisterio, idEscalaMinisterio));
    }

    @PatchMapping(
            "/confirmarParticipacao/{idMembroMinisterio}/{idEscalaMinisterio}")
    @Operation(
            summary = "Confirmar participação em uma escala de um ministerio")
    public ResponseEntity<Boolean> confirmParticipacaoEscalaMinisterio(
            @PathVariable("idMembroMinisterio")
            String idMembroMinisterio,
            @PathVariable("idEscalaMinisterio")
            String idEscalaMinisterio) {
        return ResponseEntity.ok()
                             .body(this.ministerioService.confirmarParticipacaoEscala(idMembroMinisterio, idEscalaMinisterio));

    }

    @PatchMapping(
            "/recusarParticipacao")
    @Operation(
            summary = "Recusar participação em uma escala de um ministerio")
    public ResponseEntity<Boolean> recusarParticipacaoEscalaMinisterio(
            @RequestBody
            NaoConfirmarEscalaDTO naoConfirmarEscalaDTO) {
        return ResponseEntity.ok()
                             .body(this.ministerioService.recusarParticipacaoEscala(naoConfirmarEscalaDTO));
    }

    @GetMapping("/recusarParticipacao/{idMembroMinisterio}")
    @Operation(
            summary = "Listar recusa participação de um membro do ministerio")
    public ResponseEntity<List<NaoConfirmadoEscalaMinisterio>> listarRecusarParticipacaoEscalaMinisterio(
            @PathVariable("idMembroMinisterio")
            String idMembroMinisterio) {
        return ResponseEntity.ok()
                             .body(this.ministerioService
                                     .listNaoConfirmadoEscalaMinisterioByIdMembroMinisterio(idMembroMinisterio));
    }

    @GetMapping(
            "/recusarParticipacao/{idNaoConfirmadoEscalaMinisterio}/byId")
    @Operation(summary = "Listar recusa participação pelo id")
    public ResponseEntity<NaoConfirmadoEscalaMinisterio> listarRecusarParticipacaoPeloId(
            @PathVariable("idNaoConfirmadoEscalaMinisterio")
            String idNaoConfirmadoEscalaMinisterio) {
        return ResponseEntity.ok()
                             .body(this.ministerioService.listNaoConfirmadoEscalaMinisterioById(idNaoConfirmadoEscalaMinisterio));
    }

    @GetMapping("/escalas/canParticipate/{idMembroMinisterio}")
    @Operation(
            summary = "Listar todos as escalas que pode participar em um intervalo de tempo")
    public ResponseEntity<List<EscalaMinisterioWithEventoInfoResponse>> listarEscalasMinisterioMembroCanParticipate(
            @PathVariable("idMembroMinisterio")
            String idMembroMinisterio,
            @RequestParam(name = "dateStart")
            Date dateStart,
            @RequestParam(name = "dateEnd") Date dateEnd) {
        return ResponseEntity.ok(this.ministerioService.listEscalaMinisterioMembroIdCanParticipate(idMembroMinisterio, dateStart, dateEnd));
    }

    @GetMapping("/escalas/confirmed/{idMembroMinisterio}")
    @Operation(
            summary = "Listar todos as escalas que participa em um intervalo de tempo")
    public ResponseEntity<List<EscalaMinisterioWithEventoInfoResponse>> listarEscalasMinisterioMembroConfirmed(
            @PathVariable("idMembroMinisterio")
            String idMembroMinisterio,
            @RequestParam(name = "dateStart")
            Date dateStart,
            @RequestParam(name = "dateEnd") Date dateEnd) {
        return ResponseEntity.ok(this.ministerioService.listEscalaMinisterioConfirmedMembro(idMembroMinisterio, dateStart, dateEnd));
    }
}
