package br.api.hallel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.api.hallel.model.Membro;
import br.api.hallel.service.MembroService;

@RestController
@RequestMapping(value = "/membros")
public class MembroController {

    @Autowired
    private MembroService service;

    @PostMapping("/create")
    public Membro createMembro(@RequestBody Membro membro) {
        return this.service.createMembro(membro);
    }

    @GetMapping("")
    public List<Membro> listAllMembros() {
        return this.service.listAllMembros();
    }

    @GetMapping("/{id}")
    public Membro listMembroId(@PathVariable String id) {
        return this.service.listMembroId(id);
    }

    @GetMapping("/edit/{id}")
    public Membro updateMembrobyId(@PathVariable String id, @RequestBody Membro membroModel) {
        return this.service.updatePerfilMembro(id, membroModel);
    }

    @GetMapping("/delete/{id}")
    public void deleteMembroById(@PathVariable String id) {
        this.service.deleteMembroById(id);
    }

}