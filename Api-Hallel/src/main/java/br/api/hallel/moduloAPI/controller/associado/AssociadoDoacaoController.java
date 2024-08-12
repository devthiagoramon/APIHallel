package br.api.hallel.moduloAPI.controller.associado;

import br.api.hallel.moduloAPI.model.Doacao;
import br.api.hallel.moduloAPI.payload.requerimento.DoarObjetoRetiroReq;
import br.api.hallel.moduloAPI.payload.requerimento.DoarRetiroReq;
import br.api.hallel.moduloAPI.service.financeiro.DoacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/associado/doacao")
@RestController
public class AssociadoDoacaoController {

    @Autowired
    private DoacaoService doacaoService;

    @PostMapping("/retiro")
    @Operation(
            summary = "Doar para um retiro da comunidade (ASSOCIADO)",
            security = @SecurityRequirement(name = "ASSOCIADO"))
    public ResponseEntity<Doacao> doarRetiro(@Valid @RequestBody
                                             DoarRetiroReq dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(doacaoService.doarRetiro(dto));
    }

    @PostMapping("/retiro/objeto")
    @Operation(
            summary = "Doar um objeto para um retiro da comunidade (ASSOCIADO)",
            security = @SecurityRequirement(name = "ASSOCIADO"))
    public ResponseEntity<Doacao> doarObjetoRetiro(@Valid @RequestBody
                                                   DoarObjetoRetiroReq dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(doacaoService.doarObjetoRetiro(dto));
    }

}
