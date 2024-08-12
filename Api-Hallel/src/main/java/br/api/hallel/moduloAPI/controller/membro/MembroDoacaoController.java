package br.api.hallel.moduloAPI.controller.membro;

import br.api.hallel.moduloAPI.model.Doacao;
import br.api.hallel.moduloAPI.payload.requerimento.DoarEventoReq;
import br.api.hallel.moduloAPI.payload.requerimento.DoarMembroReq;
import br.api.hallel.moduloAPI.payload.requerimento.DoarObjetoEventoReq;
import br.api.hallel.moduloAPI.payload.requerimento.DoarObjetoMembroReq;
import br.api.hallel.moduloAPI.service.financeiro.DoacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/membros/doacao")
public class MembroDoacaoController {

    @Autowired
    private DoacaoService doacaoService;

    @PostMapping("/membro")
    @Operation(
            summary = "Doar como membro para a comunidade (MEMBRO)",
            security = @SecurityRequirement(name = "USER"))

    public ResponseEntity<Doacao> doarMembro(@Valid @RequestBody
                                             DoarMembroReq dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(doacaoService.doarMembro(dto));
    }

    @PostMapping("/membro/objeto")
    @Operation(
            summary = "Doar um objeto como membro para a comunidade (MEMBRO)",
            security = @SecurityRequirement(name = "USER"))
    public ResponseEntity<Doacao> doarObjetoMembro(@Valid @RequestBody
                                                   DoarObjetoMembroReq dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(doacaoService.doarObjetoMembro(dto));
    }

    @PostMapping("/evento")
    @Operation(
            summary = "Doar para um evento da comunidade (MEMBRO)",
            security = @SecurityRequirement(name = "USER"))
    public ResponseEntity<Doacao> doarEvento(@Valid @RequestBody
                                             DoarEventoReq dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(doacaoService.doarEvento(dto));
    }

    @PostMapping("/evento/objeto")
    @Operation(
            summary = "Doar um objeto para um evento da comunidade (MEMBRO)",
            security = @SecurityRequirement(name = "USER"))
    public ResponseEntity<Doacao> doarObjetoEvento(@Valid @RequestBody
                                                   DoarObjetoEventoReq dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(doacaoService.doarObjetoEvento(dto));
    }

    @GetMapping("/{idMembro}/membro")
    @Operation(
            summary = "Listar todas as doações de um membro (MEMBRO)",
            security = @SecurityRequirement(name = "USER"))
    public ResponseEntity<List<Doacao>> listarDoacoesDoMembroPorID(
            @PathVariable("idMembro") String idMembro) {
        return ResponseEntity.status(HttpStatus.OK)
                             .body(doacaoService.listarDoacaoMembroPorId(idMembro));
    }
}
