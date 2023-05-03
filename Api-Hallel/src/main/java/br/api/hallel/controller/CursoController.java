package br.api.hallel.controller;

import br.api.hallel.model.Associado;
import br.api.hallel.model.Curso;
import br.api.hallel.model.ModulosCurso;
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
    @GetMapping("/usuario/cursosCadastrado/{id}")
    public ResponseEntity<List<Curso>> listCursoCadastradoByUsuario(@PathVariable String idUsuario){
        return ResponseEntity.status(201).body(this.service.listCursoByUser(idUsuario));
    }

    @GetMapping("/getModulo/{id}")
    public ResponseEntity<List<ModulosCurso>> listModuloByIdCurso(@PathVariable String id){
        return ResponseEntity.status(201).body(this.service.listModuloByIdCurso(id));
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<Curso> updateCurso(@PathVariable String id, @RequestBody Curso curso) {
        return ResponseEntity.status(200).body(this.service.updateCurso(id, curso));
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<?> deleteCurso(@PathVariable String id){
        this.service.deleteCurso(id);
        return ResponseEntity.status(204).build();
    }

    @PostMapping("/addParticipante/{idCurso}")
    public ResponseEntity<?> addParticipante(@PathVariable String idCurso, @RequestBody Associado associado){
        this.service.addAssociadoCurso(associado,idCurso);
        return ResponseEntity.status(204).build();
    }

    @GetMapping("/getParticipantes/{id}")
    public ResponseEntity<List<Associado>> listAssociadosCurso(@PathVariable String id){
        return ResponseEntity.status(200).body(this.service.listUserContainsCurso(id));
    }
}
