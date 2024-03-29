package br.api.hallel.moduloAPI.controller.membro;

import br.api.hallel.moduloAPI.exceptions.associado.AssociadoNotFoundException;
import br.api.hallel.moduloAPI.payload.requerimento.VirarAssociadoRequest;
import br.api.hallel.moduloAPI.payload.resposta.PerfilResponse;
import br.api.hallel.moduloAPI.service.financeiro.AssociadoService;
import br.api.hallel.moduloAPI.service.main.MembroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/membros")
@CrossOrigin("*")
public class MembroController {

    @Autowired
    private MembroService service;

    @Autowired
    private AssociadoService associadoService;

    @GetMapping("/perfil/{id}")
    public ResponseEntity<PerfilResponse> visualizarPerfil(@PathVariable String id) throws IllegalAccessException {
        PerfilResponse perfil = this.service.visualizarPerfil(id);
        return ResponseEntity.status(200).body(perfil);
    }

    @PostMapping("/virarAssociado")
    public ResponseEntity<Boolean> createAssociado(@RequestBody VirarAssociadoRequest virarAssociadoRequest) {
        Boolean booleanResposta = null;
        try {
            booleanResposta = this.associadoService.criarAssociado(virarAssociadoRequest);

            if (booleanResposta) {
                return ResponseEntity.status(200).body(true);
            } else {
                return ResponseEntity.status(402).body(false);
            }
        } catch (AssociadoNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}