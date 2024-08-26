package br.api.hallel.moduloAPI.controller.membro;

import br.api.hallel.moduloAPI.dto.v1.ministerio.DefineFunctionsDTO;
import br.api.hallel.moduloAPI.dto.v1.ministerio.FuncaoMinisterioDTO;
import br.api.hallel.moduloAPI.dto.v1.ministerio.MembroMinisterioWithInfosResponse;
import br.api.hallel.moduloAPI.model.FuncaoMinisterio;
import br.api.hallel.moduloAPI.service.ministerio.MinisterioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/membros/ministerio/coordenador")
@RestController
@Tag(name = "Coordenador ministerio",
     description = "Endpoints para os coordenadores de um ministério")
public class MembroCoordenadorMinisterioController {

    @Autowired
    private MinisterioService ministerioService;


    /**
     * Parte de funções de um ministerio
     *
     * @return FuncaoMinisterio
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
