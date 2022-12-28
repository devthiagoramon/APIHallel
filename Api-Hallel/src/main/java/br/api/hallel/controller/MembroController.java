package br.api.hallel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.api.hallel.model.Membro;
import br.api.hallel.service.MembroService;

@RestController
@RequestMapping(value = "/membros")
@CrossOrigin("*")
public class MembroController {

    @Autowired
    private MembroService service;

    @PostMapping("/create")
    public ResponseEntity<Membro> createMembro(@RequestBody Membro membro) {
        return ResponseEntity.status(201).body(service.createMembro(membro));
    }

    @GetMapping("")
    public ResponseEntity<List<Membro>> listAllMembros() {
        return ResponseEntity.status(200).body(service.listAllMembros());

    }

    @GetMapping("/{id}")
    public ResponseEntity<Membro> listMembroId(@PathVariable String id) {
        return ResponseEntity.status(201).body(service.listMembroId(id));
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

    @PostMapping("/login")
    public ResponseEntity<Membro> validatePass(@RequestBody Membro membro) {
        Boolean valid = this.service.validatePass(membro);

        if (!valid) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } else {
            return ResponseEntity.status(200).build();

        }

    }

}