package br.api.hallel.controller;

import br.api.hallel.model.Curso;
import br.api.hallel.payload.requerimento.AddCursoReq;
import br.api.hallel.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cursos")
@CrossOrigin("*")
public class CursoController {

    @Autowired
    CursoService service;

    @PostMapping("/create")
    public ResponseEntity<Curso> createCurso(@RequestBody AddCursoReq cursoReq) {
        System.out.println(cursoReq.toString());
        return ResponseEntity.status(201).body(this.service.createCurso(cursoReq));
    }

    @GetMapping("")
    public ResponseEntity<List<Curso>> listAllCursos() {
        return ResponseEntity.status(201).body(this.service.listAllCursos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Curso> listCursoById(@PathVariable String id) {
        return ResponseEntity.status(201).body(this.service.listCursoById(id));
    }

    @PostMapping("/update")
    public ResponseEntity<Curso> updateCurso(@RequestBody Curso curso) {
        return ResponseEntity.status(200).body(this.service.updateCurso(curso));
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<?> deleteCurso(@PathVariable String id){
        this.service.deleteCurso(id);
        return ResponseEntity.status(204).build();
    }

}
