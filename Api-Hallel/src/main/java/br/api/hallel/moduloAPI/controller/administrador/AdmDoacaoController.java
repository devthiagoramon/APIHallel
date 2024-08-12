package br.api.hallel.moduloAPI.controller.administrador;

import br.api.hallel.moduloAPI.model.Doacao;
import br.api.hallel.moduloAPI.payload.requerimento.CriarEditarDoacaoReq;
import br.api.hallel.moduloAPI.service.financeiro.DoacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/administrador/doacao")
public class AdmDoacaoController {

    @Autowired
    private DoacaoService doacaoService;
    @PostMapping("/criar")
    @Operation(summary = "Criar uma doação (ADM)",
               security = @SecurityRequirement(name = "ADM"))
    public ResponseEntity<Doacao> criar(
            @RequestBody CriarEditarDoacaoReq dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(doacaoService.criarDoacao(dto));
    }
    @PutMapping("/{idDoacao}/editar")
    @Operation(summary = "Editar uma doação(ADM)",
               security = @SecurityRequirement(name = "ADM"))
    public ResponseEntity<Doacao> editar(
            @PathVariable("idDoacao") String idDoacao,
            @RequestBody CriarEditarDoacaoReq dto) {
        return ResponseEntity.ok()
                             .body(doacaoService.editarDoacao(idDoacao, dto));
    }
    @DeleteMapping("/{idDoacao}")
    @Operation(summary = "Deletar uma doação (ADM)",
               security = @SecurityRequirement(name = "ADM"))
    public ResponseEntity<Boolean> deletar(
            @PathVariable("idDoacao") String idDoacao) {
        return ResponseEntity.ok()
                             .body(doacaoService.deleteDoacao(idDoacao));
    }

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

    @GetMapping
    @Operation(summary = "Listar todas as doações (ADM)",
               security = @SecurityRequirement(name = "ADM"))
    public ResponseEntity<List<Doacao>> listarDoacoes() {
        return ResponseEntity.status(HttpStatus.OK)
                             .body(doacaoService.listarDoacao());
    }
    @GetMapping("/{idDoacao}")
    @Operation(summary = "Listar doação por id (ADM)",
               security = @SecurityRequirement(name = "ADM"))
    public ResponseEntity<Doacao> listarDoacoesPorId(
            @PathVariable("idDoacao") String idDoacao) {
        return ResponseEntity.status(HttpStatus.OK)
                             .body(doacaoService.listarDoacaoPorId(idDoacao));
    }
    @GetMapping("/anonimas")
    @Operation(summary = "Listar todas as doações anonimas (ADM)",
               security = @SecurityRequirement(name = "ADM"))
    public ResponseEntity<List<Doacao>> listarDoacoesAnonimas() {
        return ResponseEntity.status(HttpStatus.OK)
                             .body(doacaoService.listarDoacaoAnonima());
    }
    @GetMapping("/membros")
    @Operation(summary = "Listar todas as doações de membros (ADM)",
               security = @SecurityRequirement(name = "ADM"))
    public ResponseEntity<List<Doacao>> listarDoacoesMembros() {
        return ResponseEntity.status(HttpStatus.OK)
                             .body(doacaoService.listarDoacaoMembros());
    }
    @GetMapping("/objeto")
    @Operation(summary = "Listar todos as doações de objetos (ADM)",
               security = @SecurityRequirement(name = "ADM"))
    public ResponseEntity<List<Doacao>> listarDoacoesObjeto() {
        return ResponseEntity.status(HttpStatus.OK)
                             .body(doacaoService.listarDoacaoObjeto());
    }
    @GetMapping("/{idEvento}/evento")
    @Operation(summary = "Listar todos as doações de um evento (ADM)",
               security = @SecurityRequirement(name = "ADM"))
    public ResponseEntity<List<Doacao>> listarDoacoesEvento(
            @PathVariable("idEvento") String idEvento) {
        return ResponseEntity.status(HttpStatus.OK)
                             .body(doacaoService.listarDoacaoEventoPorId(idEvento));
    }
    @GetMapping("/{idRetiro}/retiro")
    @Operation(summary = "Listar todos as doações de um retiro (ADM)",
               security = @SecurityRequirement(name = "ADM"))
    public ResponseEntity<List<Doacao>> listarDoacoesRetiro(
            @PathVariable("idRetiro") String idRetiro) {
        return ResponseEntity.status(HttpStatus.OK)
                             .body(doacaoService.listarDoacaoRetiroPorId(idRetiro));
    }
    @GetMapping("/error")
    @Operation(
            summary = "Listar todos as doações com status com error (ADM)",
            security = @SecurityRequirement(name = "ADM"))
    public ResponseEntity<List<Doacao>> listarDoacoesError() {
        return ResponseEntity.status(HttpStatus.OK)
                             .body(doacaoService.listarDoacaoError());
    }
    @GetMapping("/pendente")
    @Operation(
            summary = "Listar todos as doações com status pendentes (ADM)",
            security = @SecurityRequirement(name = "ADM"))
    public ResponseEntity<List<Doacao>> listarDoacoesPendente() {
        return ResponseEntity.status(HttpStatus.OK)
                             .body(doacaoService.listarDoacaoPendentes());
    }
    @GetMapping("/finalizados")
    @Operation(
            summary = "Listar todos as doações com status finalizados (ADM)",
            security = @SecurityRequirement(name = "ADM"))
    public ResponseEntity<List<Doacao>> listarDoacoesFinalizados() {
        return ResponseEntity.status(HttpStatus.OK)
                             .body(doacaoService.listarDoacaoFinalizadas());
    }
    @GetMapping("/entregues")
    @Operation(
            summary = "Listar todos as doações com status entregue (ADM)",
            security = @SecurityRequirement(name = "ADM"))
    public ResponseEntity<List<Doacao>> listarDoacoesEntregue() {
        return ResponseEntity.status(HttpStatus.OK)
                             .body(doacaoService.listarDoacaoEntregues());
    }
}
