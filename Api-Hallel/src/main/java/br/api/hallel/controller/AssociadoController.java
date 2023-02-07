package br.api.hallel.controller;

import br.api.hallel.model.Associado;
import br.api.hallel.service.AssociadoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api")
@RestController
@CrossOrigin("*")
public class AssociadoController {


    private AssociadoService service;


    @GetMapping("/associado")
    public List<Associado> listAllAssociados(){
        return this.service.listAllAssociado();
    }

    @GetMapping("/associado/{id}")
    public Associado listAssociadoById(@PathVariable String id){
        return this.service.listAssociadoById(id);
    }

    @GetMapping("/associado/delete/{id}")
    public void deleteAssociadById(@PathVariable String id){
        this.service.deleteAssociado(id);
    }

    @GetMapping("/associado/update/{id}")
    public Associado updateAssociado(@PathVariable String id){
        return this.service.updateAssociadoById(id);
    }
}
