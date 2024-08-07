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
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/doacao")
public class DoacaoController {

    @Autowired
    private DoacaoService doacaoService;

    @PostMapping("/criar")
    @Operation(summary = "Criar uma doação",
               security = @SecurityRequirement(name = "ADM"))
    public ResponseEntity<Doacao> criar(
            @RequestBody CriarEditarDoacaoReq dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(doacaoService.criarDoacao(dto));
    }

    @PutMapping("/{idDoacao}/editar")
    @Operation(summary = "Editar uma doação",
               security = @SecurityRequirement(name = "ADM"))
    public ResponseEntity<Doacao> editar(
            @PathVariable("idDoacao") String idDoacao,
            @RequestBody CriarEditarDoacaoReq dto) {
        return ResponseEntity.ok()
                             .body(doacaoService.editarDoacao(idDoacao, dto));
    }

    @DeleteMapping("/{idDoacao}")
    @Operation(summary = "Deletar uma doação",
               security = @SecurityRequirement(name = "ADM"))
    public ResponseEntity<Boolean> deletar(
            @PathVariable("idDoacao") String idDoacao) {
        return ResponseEntity.ok()
                             .body(doacaoService.deleteDoacao(idDoacao));
    }

    @PostMapping
    @Operation(summary = "Doar para a comunidade")
    public ResponseEntity<Doacao> doar(
            @Valid @RequestBody DoarReq dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(doacaoService.doar(dto));
    }

    @PostMapping("/objeto")
    @Operation(summary = "Doar um objeto para a comunidade")
    public ResponseEntity<Doacao> doarObjeto(@Valid @RequestBody
                                             DoarObjetoReq dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(doacaoService.doarObjeto(dto));
    }

    @PostMapping("/membro")
    @Operation(summary = "Doar como membro para a comunidade",
               security = @SecurityRequirement(name = "USER"))

    public ResponseEntity<Doacao> doarMembro(@Valid @RequestBody
                                             DoarMembroReq dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(doacaoService.doarMembro(dto));
    }

    @PostMapping("/membro/objeto")
    @Operation(
            summary = "Doar um objeto como membro para a comunidade",
            security = @SecurityRequirement(name = "USER"))
    public ResponseEntity<Doacao> doarObjetoMembro(@Valid @RequestBody
                                                   DoarObjetoMembroReq dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(doacaoService.doarObjetoMembro(dto));
    }

    @PostMapping("/evento")
    @Operation(
            summary = "Doar para um evento da comunidade",
            security = @SecurityRequirement(name = "USER"))
    public ResponseEntity<Doacao> doarEvento(@Valid @RequestBody
                                             DoarEventoReq dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(doacaoService.doarEvento(dto));
    }

    @PostMapping("/evento/objeto")
    @Operation(
            summary = "Doar um objeto para um evento da comunidade",
            security = @SecurityRequirement(name = "USER"))
    public ResponseEntity<Doacao> doarObjetoEvento(@Valid @RequestBody
                                                   DoarObjetoEventoReq dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(doacaoService.doarObjetoEvento(dto));
    }

    @PostMapping("/retiro")
    @Operation(
            summary = "Doar para um retiro da comunidade",
            security = @SecurityRequirement(name = "ASSOCIADO"))
    public ResponseEntity<Doacao> doarRetiro(@Valid @RequestBody
                                             DoarRetiroReq dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(doacaoService.doarRetiro(dto));
    }

    @PostMapping("/retiro/objeto")
    @Operation(
            summary = "Doar um objeto para um retiro da comunidade",
            security = @SecurityRequirement(name = "ASSOCIADO"))
    public ResponseEntity<Doacao> doarObjetoRetiro(@Valid @RequestBody
                                                   DoarObjetoRetiroReq dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(doacaoService.doarObjetoRetiro(dto));
    }

    @PatchMapping("/{idDoacao}/finalizar")
    @Operation(
            summary = "Atualizar a doação como finalizado ao pagamento")
    public ResponseEntity<Doacao> finalizarDoacao(
            @PathVariable("idDoacao") String idDoacao) {
        return ResponseEntity.status(HttpStatus.OK)
                             .body(doacaoService.finalizarDoacao(idDoacao));
    }

    @PatchMapping("/{idDoacao}/finalizar/objeto")
    @Operation(
            summary = "Atualizar a doação de objeto, como recebido",
            description = "Ao receber o objeto na comunidade, marcar como entregue",
            security = @SecurityRequirement(name = "ADM"))
    public ResponseEntity<Doacao> finalizarDoacaoObjeto(
            @PathVariable("idDoacao") String idDoacao) {
        return ResponseEntity.status(HttpStatus.OK)
                             .body(doacaoService.finalizarDoacaoObjeto(idDoacao));
    }

    @GetMapping
    @Operation(summary = "Listar todas as doações",
               security = @SecurityRequirement(name = "ADM"))
    public ResponseEntity<List<Doacao>> listarDoacoes() {
        return ResponseEntity.status(HttpStatus.OK)
                             .body(doacaoService.listarDoacao());
    }

    @GetMapping("/{idDoacao}")
    @Operation(summary = "Listar doação por id",
               security = @SecurityRequirement(name = "ADM"))
    public ResponseEntity<Doacao> listarDoacoesPorId(
            @PathVariable("idDoacao") String idDoacao) {
        return ResponseEntity.status(HttpStatus.OK)
                             .body(doacaoService.listarDoacaoPorId(idDoacao));
    }

    @GetMapping("/anonimas")
    @Operation(summary = "Listar todas as doações anonimas",
               security = @SecurityRequirement(name = "ADM"))
    public ResponseEntity<List<Doacao>> listarDoacoesAnonimas() {
        return ResponseEntity.status(HttpStatus.OK)
                             .body(doacaoService.listarDoacaoAnonima());
    }

    @GetMapping("/membros")
    @Operation(summary = "Listar todas as doações de membros",
               security = @SecurityRequirement(name = "ADM"))
    public ResponseEntity<List<Doacao>> listarDoacoesMembros() {
        return ResponseEntity.status(HttpStatus.OK)
                             .body(doacaoService.listarDoacaoMembros());
    }

    @GetMapping("/{idMembro}/membro")
    @Operation(summary = "Listar todas as doações de um membro",
               tags = {"Membro", "Administrador"})
    public ResponseEntity<List<Doacao>> listarDoacoesDoMembroPorID(
            @PathVariable("idMembro") String idMembro) {
        return ResponseEntity.status(HttpStatus.OK)
                             .body(doacaoService.listarDoacaoMembroPorId(idMembro));
    }

    @GetMapping("/objeto")
    @Operation(summary = "Listar todos as doações de objetos",
               security = @SecurityRequirement(name = "ADM"))
    public ResponseEntity<List<Doacao>> listarDoacoesObjeto() {
        return ResponseEntity.status(HttpStatus.OK)
                             .body(doacaoService.listarDoacaoObjeto());
    }

    @GetMapping("/{idEvento}/evento")
    @Operation(summary = "Listar todos as doações de um evento",
               security = @SecurityRequirement(name = "ADM"))
    public ResponseEntity<List<Doacao>> listarDoacoesEvento(
            @PathVariable("idEvento") String idEvento) {
        return ResponseEntity.status(HttpStatus.OK)
                             .body(doacaoService.listarDoacaoEventoPorId(idEvento));
    }

    @GetMapping("/{idRetiro}/retiro")
    @Operation(summary = "Listar todos as doações de um retiro",
               security = @SecurityRequirement(name = "ADM"))
    public ResponseEntity<List<Doacao>> listarDoacoesRetiro(
            @PathVariable("idRetiro") String idRetiro) {
        return ResponseEntity.status(HttpStatus.OK)
                             .body(doacaoService.listarDoacaoRetiroPorId(idRetiro));
    }

    @GetMapping("/error")
    @Operation(
            summary = "Listar todos as doações com status com error",
            security = @SecurityRequirement(name = "ADM"))
    public ResponseEntity<List<Doacao>> listarDoacoesError() {
        return ResponseEntity.status(HttpStatus.OK)
                             .body(doacaoService.listarDoacaoError());
    }

    @GetMapping("/pendente")
    @Operation(
            summary = "Listar todos as doações com status pendentes",
            security = @SecurityRequirement(name = "ADM"))
    public ResponseEntity<List<Doacao>> listarDoacoesPendente() {
        return ResponseEntity.status(HttpStatus.OK)
                             .body(doacaoService.listarDoacaoPendentes());
    }

    @GetMapping("/finalizados")
    @Operation(
            summary = "Listar todos as doações com status finalizados",
            security = @SecurityRequirement(name = "ADM"))
    public ResponseEntity<List<Doacao>> listarDoacoesFinalizados() {
        return ResponseEntity.status(HttpStatus.OK)
                             .body(doacaoService.listarDoacaoFinalizadas());
    }

    @GetMapping("/entregues")
    @Operation(
            summary = "Listar todos as doações com status entregue",
            security = @SecurityRequirement(name = "ADM"))
    public ResponseEntity<List<Doacao>> listarDoacoesEntregue() {
        return ResponseEntity.status(HttpStatus.OK)
                             .body(doacaoService.listarDoacaoEntregues());
    }

}
