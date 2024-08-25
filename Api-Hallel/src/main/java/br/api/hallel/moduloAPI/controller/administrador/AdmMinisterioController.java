package br.api.hallel.moduloAPI.controller.administrador;

import br.api.hallel.moduloAPI.dto.v1.ministerio.EditCoordMinisterioDTO;
import br.api.hallel.moduloAPI.dto.v1.ministerio.MinisterioDTO;
import br.api.hallel.moduloAPI.dto.v1.ministerio.MinisterioResponse;
import br.api.hallel.moduloAPI.dto.v1.ministerio.MinisterioWithCoordsResponse;
import br.api.hallel.moduloAPI.service.ministerio.MinisterioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/administrador/v1/ministerio")
@Tag(name = "Administrador ministerio",
     description = "Endpoints para o administrador em relação a ministério")
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

    @GetMapping("/{idMinisterio}")
    @Operation(summary = "Listar um ministerio pelo seu id")
    public ResponseEntity<MinisterioResponse> listMinisterioById(
            @PathVariable("idMinisterio") String idMinisterio) {
        return ResponseEntity.ok(ministerioService.listMinisterioById(idMinisterio));
    }

    @PutMapping("/{idMinisterio}/edit")
    @Operation(summary = "Editar ministerio")
    public ResponseEntity<MinisterioResponse> updateMinisterio(
            @PathVariable("idMinisterio") String idMinisterio,
            @RequestBody MinisterioDTO ministerioDTO) {
        return ResponseEntity.ok()
                             .body(ministerioService.editMinisterio(idMinisterio, ministerioDTO));
    }

    @DeleteMapping("/{idMinisterio}")
    @Operation(summary = "Deletar ministerio")
    public ResponseEntity<?> deleteMinisterio(
            @PathVariable("idMinisterio") String idMinisterio) {
        ministerioService.deleteMinisterio(idMinisterio);
        return ResponseEntity.noContent().build();
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
