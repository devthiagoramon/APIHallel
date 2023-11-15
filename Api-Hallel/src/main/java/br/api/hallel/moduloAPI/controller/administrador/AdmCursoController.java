package br.api.hallel.moduloAPI.controller.administrador;

import br.api.hallel.moduloAPI.model.Associado;
import br.api.hallel.moduloAPI.model.AtividadesCurso;
import br.api.hallel.moduloAPI.model.Curso;
import br.api.hallel.moduloAPI.model.ModulosCurso;
import br.api.hallel.moduloAPI.payload.requerimento.AddCursoReq;
import br.api.hallel.moduloAPI.service.cursos.CursoService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/administrador/cursos")
@Slf4j
public class AdmCursoController {

    @Autowired
    private CursoService service;

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

    @GetMapping("/getModulo/{id}")
    public ResponseEntity<List<ModulosCurso>> listModuloByIdCurso(@PathVariable String id){
        return ResponseEntity.status(201).body(this.service.listModuloByIdCurso(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Curso> updateCurso(@PathVariable String id, @RequestBody AddCursoReq curso) {
        return ResponseEntity.status(200).body(this.service.updateCurso(id, curso.toCurso()));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCurso(@PathVariable String id) {
        this.service.deleteCurso(id);
        return ResponseEntity.status(204).build();
    }

    @GetMapping("/getParticipantes/{id}")
    public ResponseEntity<List<Associado>> listAssociadosCurso(@PathVariable String id) {
        return ResponseEntity.status(200).body(this.service.listUserContainsCurso(id));
    }

    @GetMapping("/getAtividades/{id}")
    public ResponseEntity<List<AtividadesCurso>> listAtividadeByCurso(@PathVariable String id) {
        return ResponseEntity.status(200).body(this.service.listAllAtividadesByCurso(id));
    }

    @GetMapping("/pdf")
    public ResponseEntity<?> generatePDF(HttpServletResponse response,Associado associado, Curso curso) {

        response.setContentType("application/pdf");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
        String currentDate = dateFormat.format(new Date());

        String header = "Content-Disposition";
        String headerValue = "attachment; filename=pdf_" + currentDate + ".pdf";
        response.setHeader(header, headerValue);

        try {
            this.service.generatePDF(response,associado,curso);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok().build();
    }
}
