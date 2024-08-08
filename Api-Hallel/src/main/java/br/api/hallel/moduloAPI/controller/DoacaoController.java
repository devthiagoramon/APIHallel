package br.api.hallel.moduloAPI.controller;

import br.api.hallel.moduloAPI.model.Doacao;
import br.api.hallel.moduloAPI.model.ERole;
import br.api.hallel.moduloAPI.payload.requerimento.*;
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
@CrossOrigin(origins = "*")
@RequestMapping("/api/doacao")
public class DoacaoController {

    @Autowired
    private DoacaoService doacaoService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/criar")
    @Operation(summary = "Criar uma doação (ADM)",
               security = @SecurityRequirement(name = "ADM"))
    public ResponseEntity<Doacao> criar(
            @RequestBody CriarEditarDoacaoReq dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(doacaoService.criarDoacao(dto));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{idDoacao}/editar (ADM)")
    @Operation(summary = "Editar uma doação",
               security = @SecurityRequirement(name = "ADM"))
    public ResponseEntity<Doacao> editar(
            @PathVariable("idDoacao") String idDoacao,
            @RequestBody CriarEditarDoacaoReq dto) {
        return ResponseEntity.ok()
                             .body(doacaoService.editarDoacao(idDoacao, dto));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{idDoacao}")
    @Operation(summary = "Deletar uma doação (ADM)",
               security = @SecurityRequirement(name = "ADM"))
    public ResponseEntity<Boolean> deletar(
            @PathVariable("idDoacao") String idDoacao) {
        return ResponseEntity.ok()
                             .body(doacaoService.deleteDoacao(idDoacao));
    }

    @PostMapping
    @Operation(summary = "Doar para a comunidade (TODOS)")
    public ResponseEntity<Doacao> doar(
            @Valid @RequestBody DoarReq dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(doacaoService.doar(dto));
    }

    @PostMapping("/objeto")
    @Operation(summary = "Doar um objeto para a comunidade (TODOS)")
    public ResponseEntity<Doacao> doarObjeto(@Valid @RequestBody
                                             DoarObjetoReq dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(doacaoService.doarObjeto(dto));
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/membro")
    @Operation(
            summary = "Doar como membro para a comunidade (MEMBRO)",
            security = @SecurityRequirement(name = "USER"))

    public ResponseEntity<Doacao> doarMembro(@Valid @RequestBody
                                             DoarMembroReq dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(doacaoService.doarMembro(dto));
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/membro/objeto")
    @Operation(
            summary = "Doar um objeto como membro para a comunidade (MEMBRO)",
            security = @SecurityRequirement(name = "USER"))
    public ResponseEntity<Doacao> doarObjetoMembro(@Valid @RequestBody
                                                   DoarObjetoMembroReq dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(doacaoService.doarObjetoMembro(dto));
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/evento")
    @Operation(
            summary = "Doar para um evento da comunidade (MEMBRO)",
            security = @SecurityRequirement(name = "USER"))
    public ResponseEntity<Doacao> doarEvento(@Valid @RequestBody
                                             DoarEventoReq dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(doacaoService.doarEvento(dto));
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/evento/objeto")
    @Operation(
            summary = "Doar um objeto para um evento da comunidade (MEMBRO)",
            security = @SecurityRequirement(name = "USER"))
    public ResponseEntity<Doacao> doarObjetoEvento(@Valid @RequestBody
                                                   DoarObjetoEventoReq dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(doacaoService.doarObjetoEvento(dto));
    }

    @PreAuthorize("hasRole('ASSOCIADO')")
    @PostMapping("/retiro")
    @Operation(
            summary = "Doar para um retiro da comunidade (ASSOCIADO)",
            security = @SecurityRequirement(name = "ASSOCIADO"))
    public ResponseEntity<Doacao> doarRetiro(@Valid @RequestBody
                                             DoarRetiroReq dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(doacaoService.doarRetiro(dto));
    }

    @PreAuthorize("hasRole('ASSOCIADO')")
    @PostMapping("/retiro/objeto")
    @Operation(
            summary = "Doar um objeto para um retiro da comunidade (ASSOCIADO)",
            security = @SecurityRequirement(name = "ASSOCIADO"))
    public ResponseEntity<Doacao> doarObjetoRetiro(@Valid @RequestBody
                                                   DoarObjetoRetiroReq dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(doacaoService.doarObjetoRetiro(dto));
    }

    @PatchMapping("/{idDoacao}/finalizar")
    @Operation(
            summary = "Atualizar a doação como finalizado ao pagamento (TODOS)")
    public ResponseEntity<Doacao> finalizarDoacao(
            @PathVariable("idDoacao") String idDoacao) {
        return ResponseEntity.status(HttpStatus.OK)
                             .body(doacaoService.finalizarDoacao(idDoacao));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/{idDoacao}/finalizar/objeto")
    @Operation(
            summary = "Atualizar a doação de objeto, como recebido (ADM)",
            description = "Ao receber o objeto na comunidade, marcar como entregue",
            security = @SecurityRequirement(name = "ADM"))
    public ResponseEntity<Doacao> finalizarDoacaoObjeto(
            @PathVariable("idDoacao") String idDoacao) {
        return ResponseEntity.status(HttpStatus.OK)
                             .body(doacaoService.finalizarDoacaoObjeto(idDoacao));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    @Operation(summary = "Listar todas as doações (ADM)",
               security = @SecurityRequirement(name = "ADM"))
    public ResponseEntity<List<Doacao>> listarDoacoes() {
        return ResponseEntity.status(HttpStatus.OK)
                             .body(doacaoService.listarDoacao());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{idDoacao}")
    @Operation(summary = "Listar doação por id (ADM)",
               security = @SecurityRequirement(name = "ADM"))
    public ResponseEntity<Doacao> listarDoacoesPorId(
            @PathVariable("idDoacao") String idDoacao) {
        return ResponseEntity.status(HttpStatus.OK)
                             .body(doacaoService.listarDoacaoPorId(idDoacao));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/anonimas")
    @Operation(summary = "Listar todas as doações anonimas (ADM)",
               security = @SecurityRequirement(name = "ADM"))
    public ResponseEntity<List<Doacao>> listarDoacoesAnonimas() {
        return ResponseEntity.status(HttpStatus.OK)
                             .body(doacaoService.listarDoacaoAnonima());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/membros")
    @Operation(summary = "Listar todas as doações de membros (ADM)",
               security = @SecurityRequirement(name = "ADM"))
    public ResponseEntity<List<Doacao>> listarDoacoesMembros() {
        return ResponseEntity.status(HttpStatus.OK)
                             .body(doacaoService.listarDoacaoMembros());
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/{idMembro}/membro")
    @Operation(
            summary = "Listar todas as doações de um membro (MEMBRO)",
            security = @SecurityRequirement(name = "USER"))
    public ResponseEntity<List<Doacao>> listarDoacoesDoMembroPorID(
            @PathVariable("idMembro") String idMembro) {
        return ResponseEntity.status(HttpStatus.OK)
                             .body(doacaoService.listarDoacaoMembroPorId(idMembro));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/objeto")
    @Operation(summary = "Listar todos as doações de objetos (ADM)",
               security = @SecurityRequirement(name = "ADM"))
    public ResponseEntity<List<Doacao>> listarDoacoesObjeto() {
        return ResponseEntity.status(HttpStatus.OK)
                             .body(doacaoService.listarDoacaoObjeto());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{idEvento}/evento")
    @Operation(summary = "Listar todos as doações de um evento (ADM)",
               security = @SecurityRequirement(name = "ADM"))
    public ResponseEntity<List<Doacao>> listarDoacoesEvento(
            @PathVariable("idEvento") String idEvento) {
        return ResponseEntity.status(HttpStatus.OK)
                             .body(doacaoService.listarDoacaoEventoPorId(idEvento));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{idRetiro}/retiro")
    @Operation(summary = "Listar todos as doações de um retiro (ADM)",
               security = @SecurityRequirement(name = "ADM"))
    public ResponseEntity<List<Doacao>> listarDoacoesRetiro(
            @PathVariable("idRetiro") String idRetiro) {
        return ResponseEntity.status(HttpStatus.OK)
                             .body(doacaoService.listarDoacaoRetiroPorId(idRetiro));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/error")
    @Operation(
            summary = "Listar todos as doações com status com error (ADM)",
            security = @SecurityRequirement(name = "ADM"))
    public ResponseEntity<List<Doacao>> listarDoacoesError() {
        return ResponseEntity.status(HttpStatus.OK)
                             .body(doacaoService.listarDoacaoError());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/pendente")
    @Operation(
            summary = "Listar todos as doações com status pendentes (ADM)",
            security = @SecurityRequirement(name = "ADM"))
    public ResponseEntity<List<Doacao>> listarDoacoesPendente() {
        return ResponseEntity.status(HttpStatus.OK)
                             .body(doacaoService.listarDoacaoPendentes());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/finalizados")
    @Operation(
            summary = "Listar todos as doações com status finalizados (ADM)",
            security = @SecurityRequirement(name = "ADM"))
    public ResponseEntity<List<Doacao>> listarDoacoesFinalizados() {
        return ResponseEntity.status(HttpStatus.OK)
                             .body(doacaoService.listarDoacaoFinalizadas());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/entregues")
    @Operation(
            summary = "Listar todos as doações com status entregue (ADM)",
            security = @SecurityRequirement(name = "ADM"))
    public ResponseEntity<List<Doacao>> listarDoacoesEntregue() {
        return ResponseEntity.status(HttpStatus.OK)
                             .body(doacaoService.listarDoacaoEntregues());
    }

}
