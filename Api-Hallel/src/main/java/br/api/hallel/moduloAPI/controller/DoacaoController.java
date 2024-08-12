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

    @PatchMapping("/{idDoacao}/finalizar")
    @Operation(
            summary = "Atualizar a doação como finalizado ao pagamento (TODOS)")
    public ResponseEntity<Doacao> finalizarDoacao(
            @PathVariable("idDoacao") String idDoacao) {
        return ResponseEntity.status(HttpStatus.OK)
                             .body(doacaoService.finalizarDoacao(idDoacao));
    }



}
