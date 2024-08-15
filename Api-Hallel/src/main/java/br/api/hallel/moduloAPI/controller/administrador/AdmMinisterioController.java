package br.api.hallel.moduloAPI.controller.administrador;

import br.api.hallel.moduloAPI.dto.v1.EditCoordMinisterioDTO;
import br.api.hallel.moduloAPI.dto.v1.MinisterioDTO;
import br.api.hallel.moduloAPI.dto.v1.MinisterioResponse;
import br.api.hallel.moduloAPI.dto.v1.MinisterioWithCoordsResponse;
import br.api.hallel.moduloAPI.service.ministerio.MinisterioService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/administrador/v1/ministerio")
public class AdmMinisterioController {

    @Autowired
    public MinisterioService ministerioService;

    @PostMapping
    @Operation(summary = "Criar ministerio para a comunidade")
    public ResponseEntity<MinisterioResponse> createMinisterio(
            @RequestBody MinisterioDTO ministerioDTO) {
        return ResponseEntity.ok()
                             .body(ministerioService.createMinisterio(ministerioDTO));
    }

    @GetMapping
    @Operation(summary = "Listar os ministerios da comunidade")
    public ResponseEntity<List<MinisterioWithCoordsResponse>> listMinisterio() {
        return ResponseEntity.ok(ministerioService.listMinisteriosWithCoords());
    }

    @PutMapping("/{idMinisterio}")
    @Operation(summary = "Editar ministerio")
    public ResponseEntity<MinisterioResponse> updateMinisterio(
            @PathVariable("idMinisterio") String idMinisterio,
            @RequestBody MinisterioDTO ministerioDTO) {
        return ResponseEntity.ok()
                             .body(ministerioService.editMinisterio(idMinisterio, ministerioDTO));
    }

    @DeleteMapping("/{idMinisterio}")
    @Operation(summary = "Deletar ministerio")
    public void deleteMinisterio(
            @PathVariable("idMinisterio") String idMinisterio) {
        ministerioService.deleteMinisterio(idMinisterio);
    }

    @PatchMapping("/{idMinisterio}/edit/coordenadores")
    @Operation(summary = "Alterar os coordenadores do ministerio")
    public ResponseEntity<MinisterioResponse> alterarCoordenadoresDoMinisterios(
            @PathVariable("idMinisterio") String idMinisterio,
            @RequestBody
            EditCoordMinisterioDTO editCoordMinisterioDTO) {
        return ResponseEntity.ok()
                             .body(this.ministerioService.alterarCoordenadoresInMinisterio(idMinisterio, editCoordMinisterioDTO));
    }

}
