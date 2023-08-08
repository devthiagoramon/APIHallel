package br.api.hallel.moduloAPI.controller.membro;

import br.api.hallel.moduloAPI.payload.resposta.PerfilResponse;
import br.api.hallel.moduloAPI.service.MembroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/membros")
@CrossOrigin("*")
public class MembroController {

    @Autowired
    private MembroService service;

    @GetMapping("/perfil/{id}")
    public ResponseEntity<PerfilResponse> visualizarPerfil(@PathVariable String id) throws IllegalAccessException {
        PerfilResponse perfil = this.service.visualizarPerfil(id);
        return ResponseEntity.status(200).body(perfil);
    }

}