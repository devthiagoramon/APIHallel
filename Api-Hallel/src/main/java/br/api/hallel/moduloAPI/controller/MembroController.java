package br.api.hallel.moduloAPI.controller;

import br.api.hallel.moduloAPI.model.Membro;
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

    @PostMapping("/create")
    public ResponseEntity<Membro> createMembro(@RequestBody Membro membro) {
        return ResponseEntity.status(201).body(service.createMembro(membro));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Membro> listMembroId(@PathVariable String id) {
        return ResponseEntity.status(201).body(service.listMembroId(id));
    }

    @GetMapping("/email")
    public ResponseEntity<Membro> listMembroEmail(@RequestParam("email") String email){
        return ResponseEntity.status(201).body(this.service.findByEmail(email));
    }

    @GetMapping("/edit/{id}")
    public ResponseEntity<Membro> updateMembrobyId(@PathVariable String id, @RequestBody Membro membroModel) {
        return ResponseEntity.status(200).body(service.updatePerfilMembro(id, membroModel));

    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<?> deleteMembroById(@PathVariable String id) {
        this.service.deleteMembroById(id);
        return ResponseEntity.status(204).build();
    }

    @GetMapping("/perfil/{id}")
    public ResponseEntity<PerfilResponse> visualizarPerfil(@PathVariable String id) throws IllegalAccessException {
        PerfilResponse perfil = this.service.visualizarPerfil(id);
        return ResponseEntity.status(200).body(perfil);
    }

}